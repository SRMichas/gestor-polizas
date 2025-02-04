USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE [dbo].[ssp_mov_polizas](
	@operation INT,
	@json VARCHAR(MAX)
	-- @success BIT OUTPUT,
	-- @exception BIT OUTPUT, 
	-- @message VARCHAR(1024) OUTPUT
    )
AS 
	DECLARE
         @id INT
        ,@empleado_id INT
        ,@sku VARCHAR(5)
        ,@cantidad INT
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

            SET @empleado_id = JSON_VALUE(@json, '$.empleadoID');
            SET @sku = JSON_VALUE(@json, '$.sku');
            SET @cantidad = JSON_VALUE(@json, '$.cantidad');

            DECLARE @cantidad_en_inventario INT

            SET @cantidad_en_inventario = ISNULL((SELECT TOP 1 Cantidad FROM polizas..MovInventario(NOLOCK)
            WHERE SKU = @sku AND Activo = 1), 0)

            IF @cantidad_en_inventario >= @cantidad
            BEGIN
                INSERT INTO polizas..MovPolizas
                (
                    EmpleadoID
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

	END 
	-- SELECCIONAR
	ELSE IF @operation = 2 
	BEGIN
        BEGIN TRY
			
            SELECT
                 P.Id
                ,P.Cantidad
                ,P.EmpleadoID
                ,P.SKU
                ,CONCAT(E.Nombre,' ',E.Apellido) as Empleado
                ,1 as Estatus
            FROM
                polizas..MovPolizas (NOLOCK) P
            INNER JOIN
                polizas..CatEmpleado (NOLOCK) E ON E.Id = P.EmpleadoID AND E.Activo = 1
            WHERE
                P.Activo = 1
            AND
                P.Id = ISNULL(JSON_VALUE(@json,'$.id'),P.Id)
            ORDER BY
                P.FechaRegistro DESC
			
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
		-- SET @n_cod_escuela  = JSON_VALUE(@json, '$.n_cod_escuela')		

		BEGIN TRANSACTION 
		BEGIN TRY

            UPDATE MovPolizas
            SET
                EmpleadoID = ISNULL(JSON_VALUE(@json,'$.empleadoID'),EmpleadoID)
                ,SKU = ISNULL(JSON_VALUE(@json,'$.sku'),SKU)
                ,Cantidad = ISNULL(JSON_VALUE(@json,'$.cantidad'),Cantidad)
            WHERE
                Id = JSON_VALUE(@json,'$.id')
			
            SET @affectedRows = @@ROWCOUNT
			COMMIT

			IF @affectedRows > 0 BEGIN 
				SET @success = 1
				SET @message = 'REGISTRO ACTUALIZADO'
			END ELSE
				SET @message = 'EMPLEADO INEXISTENTE'

            SELECT @success as Estatus, @message as Mensaje
		END TRY
		BEGIN CATCH
			ROLLBACK
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())

            SELECT @success as Estatus, @message as Mensaje
		END CATCH
	END
	-- ELIMINAR
	ELSE IF @operation = 4 
	BEGIN
        BEGIN TRANSACTION 
		BEGIN TRY

            SET @id  = JSON_VALUE(@json, '$.id')

            SELECT TOP 1
                @sku = SKU,
                @cantidad = Cantidad
            FROM
                MovPolizas
            WHERE   
                Activo = 1
            AND
                Id = @id

            --INHABILITANDO POLIZA ACTUAL
            UPDATE MovPolizas
            SET
                Activo = 0
            WHERE
                Id = @id

            --RESTAURANDO INVENTARIO
            UPDATE MovInventario
            SET
                Cantidad = Cantidad + @cantidad
            WHERE
                SKU = @sku

			COMMIT

			SET @success = 1
			SET @message = 'POLIZA ELIMINADO CORRECTAMENTE'
			
		END TRY
		BEGIN CATCH
			ROLLBACK
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH

        SELECT @success AS Estatus, @message as Mensaje
    END 
    /* OBTENER DETALLE ABONO PAGINADO */
	ELSE IF @operation = 5 
	BEGIN
        BEGIN TRY

            SET @busqueda = JSON_VALUE(@json, '$.busqueda')
            SET @pagina = JSON_VALUE(@json, '$.pagina')
            SET @paginado = JSON_VALUE(@json, '$.paginado')

            SELECT
                P.Id
                ,P.SKU
                ,P.Cantidad
                ,P.FechaRegistro
                ,E.Id as EmpleadoID
                ,CONCAT(E.Nombre,' ',E.Apellido) as Empleado
                ,CEILING((COUNT(0) OVER()) / convert(decimal(10,2),@paginado)) as TotalPaginas
                ,1 as Estatus
            FROM
                polizas..MovPolizas (NOLOCK) P
            INNER JOIN
                polizas..CatEmpleado (NOLOCK) E ON E.Id = P.EmpleadoID AND E.Activo = 1
            WHERE
                P.Activo = 1
            AND
                (
                    p.SKU LIKE COALESCE('%'+@busqueda+'%',p.SKU) 
                OR
                    e.nombre LIKE COALESCE('%'+@busqueda+'%',e.nombre) 
                OR
                    e.apellido  LIKE COALESCE('%'+@busqueda+'%',e.apellido) 
                )
            ORDER BY p.FechaRegistro DESC
            offset(@pagina-1)*@paginado ROWS 
            FETCH NEXT @paginado ROWS only
			
			SET @success = 1
			SET @message = 'EL REGISTRO HA SIDO ELIMINADO'
		END TRY
		BEGIN CATCH
            SET @success = 1
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
            SELECT TOP 1 0 as Estatus, @message as Mensaje
		END CATCH
	END
	
GO
