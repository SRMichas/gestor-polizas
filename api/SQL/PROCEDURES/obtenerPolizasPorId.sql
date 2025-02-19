USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   PROCEDURE [dbo].[obtenerPolizasPorId](
    @idPoliza INT
)
AS 
	BEGIN TRY
			
        SELECT
             P.idPoliza
            ,P.Cantidad
            ,P.idEmpleado
            ,P.SKU
            ,CONCAT(E.Nombre,' ',E.Apellido) as Empleado
            ,1 as Estatus
        FROM
            MovPolizas (NOLOCK) P
        INNER JOIN
            CatEmpleado (NOLOCK) E ON E.idEmpleado = P.idEmpleado AND E.Activo = 1
        WHERE
            P.Activo = 1
        AND
            P.idPoliza = ISNULL(@idPoliza,P.idPoliza)
        ORDER BY
            P.FechaRegistro DESC

	END TRY
	BEGIN CATCH
        SELECT 0 as Estatus, CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE()) as Mensaje
    END CATCH
    
	
	
GO
