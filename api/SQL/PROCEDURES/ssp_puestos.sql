USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE [dbo].[ssp_puestos](
	@operation INT,
	@json VARCHAR(MAX)
	-- @success BIT OUTPUT,
	-- @exception BIT OUTPUT, 
	-- @message VARCHAR(1024) OUTPUT
    )
AS 
	DECLARE
         @id INT
        --
        ,@affectedRows INT
        ,@busqueda VARCHAR(512)
        ,@pagina int
        ,@paginado int
        ,@success BIT
        ,@exception BIT
        ,@message VARCHAR(1024)


	SET @message = ''
	SET @success = 0
    
	-- AGREGAR
	IF @operation = 1 
    BEGIN
		BEGIN TRANSACTION
		BEGIN TRY

            INSERT INTO polizas..CatPuesto
            (
                Nombre
                ,Activo
                ,FechaRegistro
            )
            VALUES(
                 JSON_VALUE(@json,'$.nombre')
                ,1
                ,GETDATE()
            )

            SET @affectedRows = @@ROWCOUNT
            SET @id = IDENT_CURRENT('CatPuesto')
            COMMIT
            
            IF @affectedRows > 0
            BEGIN
                SET @success = 1
                SET @message = 'REGISTRO INSERTADO'
            END
            ELSE
            BEGIN
                SET @message = 'NO SE COMPLETO REGISTRO'
            END

		END TRY 
		BEGIN CATCH
			ROLLBACK 
            SET @success = 0
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH

        SELECT ISNULL(@id,0) as Id, @success as Estatus, @message as Mensaje
	END 
	-- SELECCIONAR
	ELSE IF @operation = 2 
	BEGIN
        BEGIN TRY
			
            SELECT
                 Id
                ,Nombre
                ,1 as Estatus
            FROM
                polizas..CatPuesto (NOLOCK)
            WHERE
                Activo = 1
            AND
                Id = ISNULL(JSON_VALUE(@json,'$.id'),Id)
			
			SET @success = 1
			SET @message = 'REGISTRO(S) CONSULTADO(S) CON EXITO'
		END TRY
		BEGIN CATCH
			SET @success = 0
			SET @exception = 1
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
            SELECT @success as Estatus, @message as Mensaje
		END CATCH

	END 
	-- ACTUALIZAR
	ELSE IF @operation = 3 
	BEGIN
		-- SET @n_cod_escuela  = JSON_VALUE(@json, '$.n_cod_escuela')		

		BEGIN TRANSACTION 
		BEGIN TRY

           UPDATE CatPuesto
            SET
                Nombre = ISNULL(JSON_VALUE(@json,'$.nombre'),'')
            WHERE
                Id = JSON_VALUE(@json,'$.id')
			
            SET @affectedRows = @@ROWCOUNT
			COMMIT

			IF @affectedRows > 0 BEGIN 
				SET @success = 1
				SET @message = 'REGISTRO ACTUALIZADO'
			END ELSE
				SET @message = 'PUESTO INEXISTENTE'
		END TRY
		BEGIN CATCH
			ROLLBACK
            SET @success = 0
			SET @exception = 1
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())

		END CATCH
        SELECT @success as Estatus, @message as Mensaje
	END
	-- ELIMINAR
	ELSE IF @operation = 4 
	BEGIN
        -- SET @n_cod_escuela  = JSON_VALUE(@json, '$.n_cod_escuela')
		
        BEGIN TRANSACTION 
		BEGIN TRY

        UPDATE CatPuesto
        SET
            Activo = 0
        WHERE
            Id = JSON_VALUE(@json,'$.id')

        SET @affectedRows = @@ROWCOUNT
		COMMIT

		IF @affectedRows > 0 BEGIN 
		    SET @success = 1
		    SET @message = 'REGISTRO ELIMINADO'
		END ELSE
		    SET @message = 'EMPLEADO INEXISTENTE'

			
		END TRY
		BEGIN CATCH
			ROLLBACK
			SET @success = 0
			SET @exception = 1
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH

        SELECT @success as Estatus, @message as Mensaje
    END 
    /* OBTENER DETALLE ABONO PAGINADO */
	ELSE IF @operation = 5 
	BEGIN
        BEGIN TRY

            SET @busqueda = JSON_VALUE(@json, '$.busqueda')
            SET @pagina = JSON_VALUE(@json, '$.pagina')
            SET @paginado = JSON_VALUE(@json, '$.paginado')

            SELECT
                Id
                ,Nombre
                ,CEILING((COUNT(0) OVER()) / convert(decimal(10,2),@paginado)) as TotalPaginas
                ,1 as Estatus
            FROM
                polizas..CatPuesto (NOLOCK)
            WHERE
                Activo = 1
            AND
                nombre LIKE COALESCE('%'+@busqueda+'%',nombre) 
            ORDER BY Id
            offset(@pagina-1)*@paginado ROWS 
            FETCH NEXT @paginado ROWS only
			
			SET @success = 1
			SET @message = 'REGISTROS CONSULTADOS CON EXITO'
		END TRY
		BEGIN CATCH
            SET @success = 0
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
            SELECT TOP 1 @success as Estatus, @message as Mensaje
		END CATCH
	END
    exec dbo.spp_guardarBitacora @@PROCID, @operation,@json,@success,@message,null
	
GO
