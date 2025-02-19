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
    
	-- SELECCIONAR
	IF @operation = 2 
	BEGIN
        BEGIN TRY
			
            SELECT
                 E.IdEmpleado
                ,E.Nombre
                ,E.Apellido
                ,E.IdPuesto
                ,P.Nombre AS Puesto
                ,1 as Estatus
            FROM
                polizas..CatEmpleado (NOLOCK) E
            INNER JOIN
                polizas..CatPuesto (NOLOCK) P ON P.IdPuesto = E.IdPuesto AND P.Activo = 1
            WHERE
                E.Activo = 1
            AND
                E.IdEmpleado = ISNULL(JSON_VALUE(@json,'$.idEmpleado'),E.IdEmpleado)
			
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
                -- ,IdPuesto = ISNULL(JSON_VALUE(@json,'$.idPuesto'),0)
            WHERE
                idEmpleado = JSON_VALUE(@json,'$.idEmpleado')
			
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

    exec dbo.spp_guardarBitacora @@PROCID, @operation,@json,@success,@message,@exception
GO
