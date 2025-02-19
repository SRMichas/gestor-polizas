USE polizas
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER PROCEDURE [dbo].[ssp_puestos](
	@operation INT,
	@json VARCHAR(MAX)
	-- @success BIT OUTPUT,
	-- @exception BIT OUTPUT, 
	-- @message VARCHAR(1024) OUTPUT
    )
AS 
	DECLARE
         @id INT
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
    
	-- SELECCIONAR
	IF @operation = 2 
	BEGIN
        BEGIN TRY
			
            SELECT
                 IdPuesto
                ,Nombre
                ,1 as Estatus
            FROM
                polizas..CatPuesto (NOLOCK)
            WHERE
                Activo = 1
            AND
                IdPuesto = ISNULL(JSON_VALUE(@json,'$.idPuesto'),IdPuesto)
			
			SET @success = 1
			SET @message = 'REGISTRO(S) CONSULTADO(S) CON EXITO'
		END TRY
		BEGIN CATCH
			SET @success = 0
			SET @exception = 1
			SET @message = CONCAT('ERROR|[',ERROR_PROCEDURE(),':',ERROR_LINE(),']|',ERROR_MESSAGE())
            SELECT @success as Estatus, @message as Mensaje
		END CATCH

	END 
	
GO
