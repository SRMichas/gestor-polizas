USE polizas
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   PROCEDURE [dbo].[actualizarEmpleado](
	@idEmpleado int,
    @Nombre VARCHAR(250)
	,@Apellido VARCHAR(250)
)
AS 

    DECLARE 
        @affectedRows INT
        ,@success BIT
        ,@message VARCHAR(1024)

    BEGIN TRANSACTION 
	BEGIN TRY

        UPDATE CatEmpleado
        SET
            Nombre = ISNULL(@Nombre,'')
            ,Apellido = ISNULL(@Apellido,'')
        WHERE
            idEmpleado = @idEmpleado
			
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
GO
