USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE [dbo].[ssp_empleado](
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

            INSERT INTO polizas..CatEmpleado
            (
                Nombre
                ,Apellido
                ,PuestoID
                ,Activo
                ,FechaRegistro
            )
            VALUES(
                 JSON_VALUE(@json,'$.nombre')
                ,JSON_VALUE(@json,'$.apellido')
                ,JSON_VALUE(@json,'$.puestoID')
                ,1
                ,GETDATE()
            )

            SET @affectedRows = @@ROWCOUNT
            SET @id = IDENT_CURRENT('CatEmpleado')
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
                 E.Id
                ,E.Nombre
                ,E.Apellido
                ,E.PuestoID
                ,P.Nombre AS Puesto
                ,1 as Estatus
            FROM
                polizas..CatEmpleado (NOLOCK) E
            INNER JOIN
                polizas..CatPuesto (NOLOCK) P ON P.Id = E.PuestoID AND P.Activo = 1
            WHERE
                E.Activo = 1
            AND
                E.Id = ISNULL(JSON_VALUE(@json,'$.id'),E.Id)
			
			SET @success = 1
			SET @message = 'REGISTRO(S) CONSULTADO(S) CON EXITO'
		END TRY
		BEGIN CATCH
			SET @exception = 1
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
            
            SELECT @success as Estatus, @message as Mensaje
		END CATCH

	END 
	-- ACTUALIZAR
	ELSE IF @operation = 3 
	BEGIN
		
		BEGIN TRANSACTION 
		BEGIN TRY

            UPDATE CatEmpleado
            SET
                Nombre = ISNULL(JSON_VALUE(@json,'$.nombre'),'')
                ,Apellido = ISNULL(JSON_VALUE(@json,'$.apellido'),'')
                ,PuestoID = ISNULL(JSON_VALUE(@json,'$.puestoID'),0)
            WHERE
                Id = JSON_VALUE(@json,'$.id')
			
            SET @affectedRows = @@ROWCOUNT
			COMMIT

			IF @affectedRows > 0 BEGIN 
				SET @success = 1
				SET @message = 'REGISTRO ACTUALIZADO'
			END ELSE
				SET @message = 'EMPLEADO INEXISTENTE'

		END TRY
		BEGIN CATCH
			ROLLBACK
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH

        SELECT @success as Estatus, @message as Mensaje

	END
	-- ELIMINAR
	ELSE IF @operation = 4 
	BEGIN
        BEGIN TRANSACTION 
		BEGIN TRY

            UPDATE CatEmpleado
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
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH

        SELECT @success as Estatus, @message as Mensaje

    END 
    /* OBTENER EMPLEADOS PAGINADO */
	ELSE IF @operation = 5 
	BEGIN
        BEGIN TRY

            SET @busqueda = JSON_VALUE(@json, '$.busqueda')
            SET @pagina = JSON_VALUE(@json, '$.pagina')
            SET @paginado = JSON_VALUE(@json, '$.paginado')

            SELECT
                E.Id
                ,E.Nombre
                ,E.Apellido
                ,P.Nombre as Puesto
                ,CEILING((COUNT(0) OVER()) / convert(decimal(10,2),@paginado)) as TotalPaginas
                ,1 as Estatus
            FROM
                polizas..CatEmpleado (NOLOCK) E
            INNER JOIN
                polizas..CatPuesto (NOLOCK) P ON P.Id = E.PuestoID AND P.Activo = 1
            WHERE
                E.Activo = 1
            AND
                (
                    CONCAT(e.nombre, ' ', e.apellido) LIKE COALESCE('%'+@busqueda+'%',CONCAT(e.nombre, ' ', e.apellido)) 
                OR
                    p.nombre  LIKE COALESCE('%'+@busqueda+'%',p.nombre) 
                )
            ORDER BY E.Id
            offset(@pagina-1)*@paginado ROWS 
            FETCH NEXT @paginado ROWS only
			
			SET @success = 1
			SET @message = 'REGISTROS CONSULTADOS CON EXITO'
		END TRY
		BEGIN CATCH
            SET @success = 1
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
            SELECT TOP 1 0 as Estatus, @message as Mensaje
		END CATCH
	END
	

    exec dbo.spp_guardarBitacora @@PROCID, @operation,@json,@success,@message,@exception
GO
