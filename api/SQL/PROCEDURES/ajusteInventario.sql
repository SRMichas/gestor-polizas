USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER     PROCEDURE [dbo].[ajusteInventario](
    @sku VARCHAR(5)
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

        DECLARE @cantidad_en_inventario INT

        SET @cantidad_en_inventario = ISNULL((SELECT TOP 1 Cantidad FROM MovInventario(NOLOCK)
        WHERE SKU = @sku AND Activo = 1), 0)

        IF @cantidad_en_inventario >= @cantidad
        BEGIN
            UPDATE polizas..MovInventario
            SET
                Cantidad = @cantidad_en_inventario - @cantidad
            WHERE
                SKU = @sku
                
            SET @success = 1
            SET @message = 'Poliza Registrada correctamente'
        END
        ELSE
        BEGIN
            SET @message = 'ERR|La cantidad en inventario es menor a la solicitada'
        END
                
        COMMIT

    END TRY 
	BEGIN CATCH
	    ROLLBACK
	    SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
	END CATCH

    SELECT @success as Estatus, @message as Mensaje
GO
