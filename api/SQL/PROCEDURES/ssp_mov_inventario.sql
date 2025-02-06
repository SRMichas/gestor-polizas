USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE [dbo].[ssp_mov_inventario](
	@operation INT,
	@json VARCHAR(MAX)
	-- @success BIT OUTPUT,
	-- @exception BIT OUTPUT, 
	-- @message VARCHAR(1024) OUTPUT
    )
AS 
	DECLARE
         @sku VARCHAR(5)
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
                 SKU
                ,Nombre
                ,Cantidad
                ,1 as Estatus
            FROM
                polizas..MovInventario (NOLOCK) 
            WHERE
                Activo = 1
            AND
                SKU = ISNULL(JSON_VALUE(@json,'$.sku'),SKU)
			
			SET @success = 1
			SET @message = 'TODO CORRECTO'
		END TRY
		BEGIN CATCH
			
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH

	END 
GO
