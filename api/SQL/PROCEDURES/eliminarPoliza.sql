USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   PROCEDURE [dbo].[eliminarPoliza](
	@idPoliza INT
)
AS 
	DECLARE
         @affectedRows INT
        ,@empleado_id INT
        ,@sku VARCHAR(5)
        ,@cantidad INT
        ,@success BIT
        ,@message VARCHAR(1024)


    BEGIN TRANSACTION 
	BEGIN TRY
        SELECT TOP 1
            @sku = SKU,
            @cantidad = Cantidad
        FROM
            MovPolizas
        WHERE   
            Activo = 1
        AND
            idPoliza = @idPoliza

        --INHABILITANDO POLIZA ACTUAL
        UPDATE MovPolizas
        SET
            Activo = 0
        WHERE
            idPoliza = @idPoliza

        --RESTAURANDO INVENTARIO
        UPDATE MovInventario
        SET
            Cantidad = Cantidad + @cantidad
        WHERE
            SKU = @sku

		COMMIT

		SET @success = 1
		SET @message = 'POLIZA ELIMINADA CORRECTAMENTE'
	END TRY
	BEGIN CATCH
		ROLLBACK
		SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
	END CATCH
	
    SELECT @success as Estatus, @message as Mensaje
GO
