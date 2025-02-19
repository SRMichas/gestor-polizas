use polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   PROCEDURE [dbo].[obtenerInvetarioPorSKU](
	@sku VARCHAR(5)
)
AS 
    BEGIN TRY
			
        SELECT
             SKU
            ,Nombre
            ,Cantidad
            ,1 as Estatus
        FROM
            MovInventario (NOLOCK)
        WHERE
            Activo = 1
        AND
            SKU = ISNULL(@sku,SKU)
	END TRY
	BEGIN CATCH
        SELECT 0 as Estatus, CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE()) as Mensaje
	END CATCH
GO
