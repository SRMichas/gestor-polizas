USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER     PROCEDURE [dbo].[crearPoliza](
    @empleado_id INT
    ,@sku VARCHAR(5)
    ,@cantidad INT
)
AS 
	DECLARE
         @id INT
        ,@affectedRows INT
        ,@success BIT = 0
        ,@message VARCHAR(1024)
    
    BEGIN TRANSACTION
	BEGIN TRY

        INSERT INTO MovPolizas
        (
            idEmpleado
            ,SKU
            ,Cantidad
            ,Activo
            ,FechaRegistro
        )
        VALUES
        (
            @empleado_id
            ,@sku
            ,@cantidad
            ,1
            ,getdate()
        )

        SET @affectedRows = @@ROWCOUNT
        
        IF @affectedRows > 0
        BEGIN    
            SET @id = IDENT_CURRENT('MovPolizas')
            SET @success = 1
            SET @message = 'Poliza Registrada correctamente'
        END
        ELSE
        BEGIN
            SET @message = 'ERR|NO se registro la Poliza'
        END
                
        COMMIT

    END TRY 
	BEGIN CATCH
	    ROLLBACK
	    SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
	END CATCH

    SELECT ISNULL(@id,0) as Id, @success as Estatus, @message as Mensaje
GO
