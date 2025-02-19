USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   PROCEDURE [dbo].[obtenerPolizasPaginadas](
	@pagina INT
    ,@paginado INT
    ,@busqueda VARCHAR(1024)
)
AS 
    BEGIN TRY

        SELECT
            P.idPoliza
            ,P.SKU
            ,P.Cantidad
            ,P.FechaRegistro
            ,E.idEmpleado
            ,CONCAT(E.Nombre,' ',E.Apellido) as Empleado
            ,I.Nombre as Inventario
            ,CEILING((COUNT(0) OVER()) / convert(decimal(10,2),@paginado)) as TotalPaginas
            ,1 as Estatus
        FROM
            polizas..MovPolizas (NOLOCK) P
        INNER JOIN
            polizas..CatEmpleado (NOLOCK) E ON E.idEmpleado = P.idEmpleado AND E.Activo = 1
        INNER JOIN
            polizas..MovInventario (NOLOCK) I ON P.SKU = I.sku AND I.Activo = 1
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
	END TRY
	BEGIN CATCH
        SELECT TOP 1 0 as Estatus, CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE()) as Mensaje
	END CATCH
GO
