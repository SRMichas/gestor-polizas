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
    
	-- AGREGAR
	IF @operation = 1 
    BEGIN
		BEGIN TRANSACTION
		BEGIN TRY

            SET @sku = JSON_VALUE(@json,'$.sku');

            IF NOT EXISTS(SELECT TOP 1 1 FROM MovInventario(NOLOCK) WHERE SKU = @sku)
            BEGIN
                INSERT INTO polizas..MovInventario
                (
                    SKU
                    ,Nombre
                    ,Cantidad
                    ,Activo
                    ,FechaRegistro
                )
                VALUES(
                    JSON_VALUE(@json,'$.sku')
                    ,JSON_VALUE(@json,'$.nombre')
                    ,JSON_VALUE(@json,'$.cantidad')
                    ,1
                    ,GETDATE()
                )

                SET @affectedRows = @@ROWCOUNT
            END
            ELSE
            BEGIN
                SET @success = 0
                SET @message = CONCAT('ERR|NO se pudo registrar el producto con SKU [',@sku,'], ya se encuentra registrado')
            END
            
            COMMIT
            IF @affectedRows > 0
            BEGIN
                SET @success = 1
                SET @message = 'REGISTRO INSERTADO'
            END

		END TRY 
		BEGIN CATCH
			ROLLBACK 
            SET @success = 0
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH

        SELECT ISNULL(@sku,'') as Id, @success as Estatus, @message as Mensaje
	END 
	-- SELECCIONAR
	ELSE IF @operation = 2 
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
	-- ACTUALIZAR
	ELSE IF @operation = 3 
	BEGIN
		
		BEGIN TRANSACTION 
		BEGIN TRY

            UPDATE MovInventario
            SET
                Nombre = ISNULL(JSON_VALUE(@json,'$.nombre'),'')
                ,Cantidad = ISNULL(JSON_VALUE(@json,'$.cantidad'),0)
            WHERE
                SKU = JSON_VALUE(@json,'$.sku')
			
            SET @affectedRows = @@ROWCOUNT
			COMMIT

			IF @affectedRows > 0 BEGIN 
				SET @success = 1
				SET @message = 'REGISTRO ACTUALIZADO'
			END 
            ELSE
				SET @message = 'INVENTARIO INEXISTENTE'

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

			UPDATE MovInventario
            SET
                Activo = 0
            WHERE
                SKU = JSON_VALUE(@json,'$.sku')

            SET @affectedRows = @@ROWCOUNT
            COMMIT

			IF @affectedRows > 0 
            BEGIN
				SET @success = 1
				SET @message = 'EL REGISTRO HA SIDO ELIMINADO'
			END ELSE 
				SET @message = 'NO EXISTE EL INVENTARIO'
			
		END TRY
		BEGIN CATCH
			
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
			ROLLBACK
		END CATCH

        SELECT @success as Estatus, @message as Mensaje

    END 
    /* OBTENER DETALLE ABONO PAGINADO */
	ELSE IF @operation = 5 
	BEGIN
        BEGIN TRY

            SET @busqueda = JSON_VALUE(@json, '$.busqueda')
            SET @pagina = JSON_VALUE(@json, '$.pagina')
            SET @paginado = JSON_VALUE(@json, '$.paginado')

            SELECT
                 SKU
                ,Nombre
                ,Cantidad
                ,CEILING((COUNT(0) OVER()) / convert(decimal(10,2),@paginado)) as TotalPaginas
                ,1 as Estatus
            FROM
                polizas..MovInventario(NOLOCK)
            WHERE
                Activo = 1

			
			SET @success = 1
			SET @message = 'REGISTROS OBTENIDOS'
		END TRY
		BEGIN CATCH
			
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH
	END
	
    exec dbo.spp_guardarBitacora @@PROCID, @operation,@json,@success,@message,null
GO
