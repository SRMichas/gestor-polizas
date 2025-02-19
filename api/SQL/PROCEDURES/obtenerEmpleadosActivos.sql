USE polizas
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   PROCEDURE [dbo].[obtenerEmpleadosActivos]
AS 
    BEGIN TRY
			
        SELECT
            E.IdEmpleado
            ,E.Nombre
            ,E.Apellido
            ,E.IdPuesto
            ,P.Nombre AS Puesto
            ,1 as Estatus
        FROM
            CatEmpleado (NOLOCK) E
        INNER JOIN
            CatPuesto (NOLOCK) P ON P.IdPuesto = E.IdPuesto AND P.Activo = 1
        WHERE
            E.Activo = 1
	END TRY
	BEGIN CATCH
        SELECT 0 as Estatus, CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE()) as Mensaje
	END CATCH
GO
