USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   PROCEDURE [dbo].[actualizarEmpleadoPoliza](
	@idPoliza INT
    ,@idEmpleado INT
)
AS 
	DECLARE
         @affectedRows INT
        ,@success BIT
        ,@message VARCHAR(1024)


    BEGIN TRANSACTION 
	BEGIN TRY

        UPDATE MovPolizas
        SET
            idEmpleado = ISNULL(@idEmpleado,idEmpleado)
        WHERE
            idPoliza = @idPoliza
			
        SET @affectedRows = @@ROWCOUNT
		COMMIT

	    IF @affectedRows > 0 BEGIN 
	    	SET @success = 1
	    	SET @message = 'REGISTRO ACTUALIZADO'
	    END ELSE
	    	SET @message = 'POLIZA INEXISTENTE'
	END TRY
	BEGIN CATCH
		ROLLBACK
		SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
	END CATCH
	
    SELECT @success as Estatus, @message as Mensaje
GO
