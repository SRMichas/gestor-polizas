USE [polizas]
GO
-- ===================================================================================================
-- Author:      Elias Soria
-- Create date: 2025-01-29
-- Description: CREACION TABLA MovPolizas
-- ===================================================================================================
BEGIN TRY
    BEGIN TRAN TR_CREATE_TABLE

	IF OBJECT_ID('dbo.MovPolizas') is null
	BEGIN	
		CREATE TABLE [dbo].[MovPolizas](
			IdPoliza int NOT NULL IDENTITY PRIMARY KEY,
			IdEmpleado INT NOT NULL,
			SKU VARCHAR(5) NOT NULL,
			Cantidad INT NOT NULL,
			Activo BIT NOT NULL,
			FechaRegistro DATETIME NOT NULL
			CONSTRAINT FK_poliza_empleado FOREIGN KEY (IdEmpleado) REFERENCES CatEmpleado(IdEmpleado),
			CONSTRAINT FK_poliza_inventario FOREIGN KEY (SKU) REFERENCES MovInventario(SKU)
		)
	END

    COMMIT TRAN TR_CREATE_TABLE
END TRY
BEGIN CATCH
    ROLLBACK TRAN TR_CREATE_TABLE

    SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage, ERROR_LINE() AS ErrorLine;
END CATCH