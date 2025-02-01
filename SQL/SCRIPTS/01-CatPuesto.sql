USE [polizas]
GO
-- ===================================================================================================
-- Author:      Elias Soria
-- Create date: 2025-01-29
-- Description: CREACION TABLA CatPuesto
-- ===================================================================================================
BEGIN TRY
    BEGIN TRAN TR_CREATE_TABLE

	IF OBJECT_ID('dbo.CatPuesto') is null
	BEGIN	
		CREATE TABLE [dbo].[CatPuesto](
			Id int NOT NULL IDENTITY PRIMARY KEY,
			Nombre VARCHAR(250) NOT NULL,
			Activo BIT NOT NULL,
			FechaRegistro DATETIME NOT NULL
		)
	END

    COMMIT TRAN TR_CREATE_TABLE
END TRY
BEGIN CATCH
    ROLLBACK TRAN TR_CREATE_TABLE

    SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage, ERROR_LINE() AS ErrorLine;
END CATCH
GO
SET IDENTITY_INSERT dbo.CatPuesto ON;
BEGIN TRY
    BEGIN TRAN TR_INSERT_IN_TABLE
	
	DECLARE @id INT = 1
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatPuesto where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatPuesto (Id, Nombre, Activo, FechaRegistro) values (@id, 'Supervisor Regional',1, getdate())
	END

	SET @id = 2
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatPuesto where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatPuesto (Id, Nombre, Activo, FechaRegistro) values (@id, 'Gerente',1, getdate())
	END

	SET @id = 3
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatPuesto where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatPuesto (Id, Nombre, Activo, FechaRegistro) values (@id, 'Inventarista',1, getdate())
	END

	SET @id = 4
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatPuesto where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatPuesto (Id, Nombre, Activo, FechaRegistro) values (@id, 'Vendedor',1, getdate())
	END

	SET @id = 5
	IF ISNULL( (SELECT COUNT(0) FROM dbo.CatPuesto where Id = @id),0) = 0
	BEGIN	
		insert into dbo.CatPuesto (Id, Nombre, Activo, FechaRegistro) values (@id, 'Cajero',1, getdate())
	END

    COMMIT TRAN TR_INSERT_IN_TABLE
END TRY
BEGIN CATCH
    ROLLBACK TRAN TR_INSERT_IN_TABLE

        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage, ERROR_LINE() AS ErrorLine;
END CATCH
SET IDENTITY_INSERT dbo.CatPuesto OFF;