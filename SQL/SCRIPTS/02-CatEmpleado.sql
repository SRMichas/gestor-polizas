USE [polizas]
GO
-- ===================================================================================================
-- Author:      Elias Soria
-- Create date: 2025-01-29
-- Description: CREACION TABLA CatEmpleado
-- ===================================================================================================
BEGIN TRY
    BEGIN TRAN TR_CREATE_TABLE

	IF OBJECT_ID('dbo.CatEmpleado') is null
	BEGIN	
		CREATE TABLE [dbo].[CatEmpleado](
			Id int NOT NULL IDENTITY PRIMARY KEY,
			Nombre VARCHAR(250) NOT NULL,
			Apellido VARCHAR(250) NOT NULL,
			PuestoID INT NOT NULL,
			Activo BIT NOT NULL,
			FechaRegistro DATETIME NOT NULL
			CONSTRAINT FK_empleado_puesto FOREIGN KEY (PuestoID) REFERENCES CatPuesto(Id)
		)
	END

    COMMIT TRAN TR_CREATE_TABLE
END TRY
BEGIN CATCH
    ROLLBACK TRAN TR_CREATE_TABLE

    SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage, ERROR_LINE() AS ErrorLine;
END CATCH
GO
SET IDENTITY_INSERT dbo.CatEmpleado ON;
BEGIN TRY
    BEGIN TRAN TR_INSERT_IN_TABLE
	
	DECLARE @id INT = 1
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatEmpleado where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatEmpleado (Id, Nombre, Apellido, PuestoID, Activo, FechaRegistro) values (@id, 'Elias', 'Soria',@id ,1, getdate())
	END

	SET @id = 2
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatEmpleado where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatEmpleado (Id, Nombre, Apellido, PuestoID, Activo, FechaRegistro) values (@id, 'Misael', 'Ramirez',@id ,1, getdate())
	END

	SET @id = 3
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatEmpleado where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatEmpleado (Id, Nombre, Apellido, PuestoID, Activo, FechaRegistro) values (@id, 'Juan', 'Soto',@id ,1, getdate())
	END

	SET @id = 4
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatEmpleado where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatEmpleado (Id, Nombre, Apellido, PuestoID, Activo, FechaRegistro) values (@id, 'Emilia', 'Perez',@id ,1, getdate())
	END


	SET @id = 5
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatEmpleado where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatEmpleado (Id, Nombre, Apellido, PuestoID, Activo, FechaRegistro) values (@id, 'Omar', 'Echeverria',@id ,1, getdate())
	END

    COMMIT TRAN TR_INSERT_IN_TABLE
END TRY
BEGIN CATCH
    ROLLBACK TRAN TR_INSERT_IN_TABLE

        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage, ERROR_LINE() AS ErrorLine;
END CATCH
SET IDENTITY_INSERT dbo.CatEmpleado OFF;