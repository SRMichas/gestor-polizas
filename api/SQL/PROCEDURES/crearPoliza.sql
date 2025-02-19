USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   PROCEDURE [dbo].[crearPoliza](
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

        DECLARE @cantidad_en_inventario INT

        SET @cantidad_en_inventario = ISNULL((SELECT TOP 1 Cantidad FROM MovInventario(NOLOCK)
        WHERE SKU = @sku AND Activo = 1), 0)

        IF @cantidad_en_inventario >= @cantidad
        BEGIN
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
                SET @message = 'ERR|NO se registro la Poliza'
            END
        END
        ELSE
        BEGIN
            SET @message = 'ERR|La cantidad en invetario es menor a la solicitada'
        END
                
        COMMIT

    END TRY 
	BEGIN CATCH
	    ROLLBACK
	    SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
	END CATCH

    SELECT ISNULL(@id,0) as Id, @success as Estatus, @message as Mensaje
GO
