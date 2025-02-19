USE [polizas]
GO
-- ===================================================================================================
-- Author:      Elias Soria
-- Create date: 2025-01-29
-- Description: CREACION TABLA MovInventario
-- ===================================================================================================
BEGIN TRY
    BEGIN TRAN TR_CREATE_TABLE

	IF OBJECT_ID('dbo.MovInventario') is null
	BEGIN	
		CREATE TABLE [dbo].[MovInventario](
			SKU VARCHAR(5) NOT NULL PRIMARY KEY,
			Nombre VARCHAR(250) NOT NULL,
			Cantidad INT NOT NULL,
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

BEGIN TRY
    BEGIN TRAN TR_INSERT_IN_TABLE
	
	DECLARE @sku VARCHAR(5) = 'TVS55'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'TV Samsung 55”', 100, 1, getdate())
	END


	SET @sku = 'IPH15'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'iPhone 15 Pro', 100, 1, getdate())
	END


	SET @sku = 'LTXPS'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Laptop Dell XPS', 100, 1, getdate())
	END


	SET @sku = 'ADBSE'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Audífonos Bose', 100, 1, getdate())
	END


	SET @sku = 'SWGRM'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Smartwatch Garmin', 100, 1, getdate())
	END


	SET @sku = 'PL501'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Pantalón Levi’s 501', 100, 1, getdate())
	END


	SET @sku = 'ZPNAI'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Zapatillas Nike Air', 100, 1, getdate())
	END


	SET @sku = 'BLMKC'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Bolso Michael Kors', 100, 1, getdate())
	END


	SET @sku = 'CHNTF'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Chaqueta North Face', 100, 1, getdate())
	END


	SET @sku = 'GRADS'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Gorra Adidas', 100, 1, getdate())
	END


	SET @sku = 'SFSCG'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Sofá Seccional Gris', 100, 1, getdate())
	END


	SET @sku = 'CLKSG'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Colchón King Size', 100, 1, getdate())
	END


	SET @sku = 'LQOST'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Licuadora Oster', 100, 1, getdate())
	END


	SET @sku = 'CFNPS'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Cafetera Nespresso', 100, 1, getdate())
	END


	SET @sku = 'ASDYS'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Aspiradora Dyson', 100, 1, getdate())
	END


	SET @sku = 'BKTMT'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Bicicleta Trek MTB', 100, 1, getdate())
	END


	SET @sku = 'BLADF'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Balón Adidas Fútbol', 100, 1, getdate())
	END


	SET @sku = 'RQTWL'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Raqueta Wilson', 100, 1, getdate())
	END


	SET @sku = 'MCN10'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Mancuernas 10kg', 100, 1, getdate())
	END


	SET @sku = 'CSPSZ'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Casco Specialized', 100, 1, getdate())
	END


	SET @sku = 'LGSTW'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Lego Star Wars', 100, 1, getdate())
	END


	SET @sku = 'MNBRB'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Muñeca Barbie', 100, 1, getdate())
	END


	SET @sku = 'CHWLS'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Carro Hot Wheels', 100, 1, getdate())
	END


	SET @sku = 'PLNRF'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Pelota Nerf', 100, 1, getdate())
	END


	SET @sku = 'PZRVB'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Puzzle Ravensburger', 100, 1, getdate())
	END


	SET @sku = 'RFLGE'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Refrigerador LG', 100, 1, getdate())
	END


	SET @sku = 'HRMOC'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Horno de Microondas', 100, 1, getdate())
	END


	SET @sku = 'LVSMS'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Lavadora Samsung', 100, 1, getdate())
	END


	SET @sku = 'PLPHP'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Plancha Philips', 100, 1, getdate())
	END


	SET @sku = 'VNHWL'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Ventilador Honeywell', 100, 1, getdate())
	END


	SET @sku = 'PRDHM'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Perfume Dior Homme', 100, 1, getdate())
	END


	SET @sku = 'CRNVL'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Crema Nivea', 100, 1, getdate())
	END


	SET @sku = 'MKMAC'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Maquillaje MAC', 100, 1, getdate())
	END


	SET @sku = 'CPDYS'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Cepillo Dyson', 100, 1, getdate())
	END


	SET @sku = 'AFBRN'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Afeitadora Braun', 100, 1, getdate())
	END


	SET @sku = 'LLM17'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Llantas Michelin 17”', 100, 1, getdate())
	END


	SET @sku = 'ACM53'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Aceite Mobil 5W30', 100, 1, getdate())
	END


	SET @sku = 'BTBSC'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Batería Bosch', 100, 1, getdate())
	END


	SET @sku = 'LPRNX'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Limpiaparabrisas RainX', 100, 1, getdate())
	END


	SET @sku = 'CCUSB'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Cargador Coche USB', 100, 1, getdate())
	END


	SET @sku = 'CFSBK'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Café Starbucks', 100, 1, getdate())
	END


	SET @sku = 'VTRJA'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Vino Tinto Rioja', 100, 1, getdate())
	END


	SET @sku = 'CHLND'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Chocolate Lindt', 100, 1, getdate())
	END


	SET @sku = 'CRKLG'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Cereal Kellogg’s', 100, 1, getdate())
	END


	SET @sku = 'GLORO'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Galletas Oreo', 100, 1, getdate())
	END


	SET @sku = 'TLBDK'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Taladro Black+Decker', 100, 1, getdate())
	END


	SET @sku = 'MRTSY'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Martillo Stanley', 100, 1, getdate())
	END


	SET @sku = 'DTPHP'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Destornillador Philips', 100, 1, getdate())
	END


	SET @sku = 'LVAJT'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Llave Ajustable', 100, 1, getdate())
	END


	SET @sku = 'CXHRM'
	IF ISNULL( (SELECT COUNT(0) FROM dbo.MovInventario where SKU = @sku),0) = 0
	BEGIN 
	insert into dbo.MovInventario (SKU, Nombre, Cantidad, Activo, FechaRegistro) values (@sku, 'Caja de Herramientas', 100, 1, getdate())
	END

    COMMIT TRAN TR_INSERT_IN_TABLE
END TRY
BEGIN CATCH
    ROLLBACK TRAN TR_INSERT_IN_TABLE

        SELECT ERROR_NUMBER() AS ErrorNumber, ERROR_MESSAGE() AS ErrorMessage, ERROR_LINE() AS ErrorLine;
END CATCH