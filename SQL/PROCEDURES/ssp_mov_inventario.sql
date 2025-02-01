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
         @id INT
        ,@pagina int
        ,@paginado int
        ,@success BIT
        ,@message VARCHAR(1024)


	SET @message = ''
	SET @success = 0
    
	-- AGREGAR
	IF @operation = 1 
    BEGIN
		BEGIN TRANSACTION
		BEGIN TRY

            SET @success = 1
			SET @message = 'Registros correctos'
			COMMIT
		END TRY 
		BEGIN CATCH
			
            SET @success = 0
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
			ROLLBACK 
		END CATCH
	END 
	-- SELECCIONAR
	ELSE IF @operation = 2 
	BEGIN
        BEGIN TRY
			
            SELECT
                 SKU
                ,Nombre
                ,Cantidad
            FROM
                polizas..MovInventario (NOLOCK) 
            WHERE
                Activo = 0
            AND
                SKU = ISNULL(JSON_VALUE(@json,'$.SKU'),SKU)
			
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

            UPDATE CatEmpleado
            SET
                Nombre = ISNULL(JSON_VALUE(@json,'$.Nombre'),'')
                ,Apellido = ISNULL(JSON_VALUE(@json,'$.Apellido'),'')
                ,PuestoID = ISNULL(JSON_VALUE(@json,'$.PuestoID'),0)
            WHERE
                Id = JSON_VALUE(@json,'$.Id')
			

			COMMIT

			IF @@ROWCOUNT > 0 BEGIN 
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
        -- SET @n_cod_escuela  = JSON_VALUE(@json, '$.n_cod_escuela')
		
        BEGIN TRANSACTION 
		BEGIN TRY

			

			IF @@ROWCOUNT > 0 BEGIN
				SET @success = 1
				SET @message = 'EL REGISTRO HA SIDO ELIMINADO'
			END ELSE 
				SET @message = 'NO EXISTE LA ESCUELA'
			COMMIT
		END TRY
		BEGIN CATCH
			
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
			ROLLBACK
		END CATCH

    END 
    /* OBTENER DETALLE ABONO PAGINADO */
	ELSE IF @operation = 5 
	BEGIN
        BEGIN TRY

            /*SET @c_cod_cliente = JSON_VALUE(@json,'$.c_cod_cliente')
            SET @pagina = ISNULL(JSON_VALUE(@json,'$.pagina'),1)
            SET @paginado = ISNULL(JSON_VALUE(@json,'$.paginado'),20)
			
            select 
                *
            from (
                select 
                n_abono*-1 as total_venta
                ,n_abono*-1 as n_abono
                ,d_fecha_abono
                ,v_comentario
                ,-1 as n_cantidad
                ,(select n_abono as n_prod_venta for json path, WITHOUT_array_wrapper) as v_json_det
                ,@empGUID as c_cod_coleccion
                ,'' as v_desc_coll
				,@empGUID AS c_cod_generico
            from vn_abono where c_cod_cliente='2fb03233-d2be-4c73-aebe-d82796306e08'
            --and b_activo_opc = 1
            union all
            select 
                v.n_importe as total_venta
                ,VD.n_cantidad * CONVERT(decimal(18,2),JSON_VALUE(v_json_det,'$.n_prod_venta'))
                ,v.d_fecha_venta as d_fecha_abono
                ,v.Comentario as v_comentario
                ,VD.n_cantidad
                ,VD.v_json_det
                ,C.c_cod_coleccion
                ,C.v_desc_coll
				,V.c_cod_venta AS c_cod_generico
            from vn_venta V
            inner join vn_venta_detalle VD ON VD.c_cod_venta = V.c_cod_venta
            inner join vn_producto_cat P ON VD.c_cod_prod = P.c_cod_prod AND P.b_activo_opc = 1
            inner join vn_coleccion_cat C ON C.c_cod_coleccion = P.c_cod_coleccion AND C.b_activo_opc=1
            where 
                v.c_cod_cliente = '2fb03233-d2be-4c73-aebe-d82796306e08'
            and 
                v.b_activo_opc=1
            ) as x
            order by d_fecha_abono desc
            OFFSET ((@pagina - 1) * @paginado) ROWS
            FETCH NEXT @paginado ROWS ONLY;*/

            SELECT
                 E.Id
                ,E.Nombre
                ,E.Apellido
                ,E.PuestoID
                ,P.Nombre AS Puesto
            FROM
                polizas..CatEmpleado(NOLOCK) E
            INNER JOIN
                polizas..CatPuesto (NOLOCK) P ON P.Id = E.PuestoID AND P.Activo = 1
            WHERE
                E.Activo = 0

			
			SET @success = 1
			SET @message = 'EL REGISTRO HA SIDO ELIMINADO'
		END TRY
		BEGIN CATCH
			
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
		END CATCH
	END
	
GO
