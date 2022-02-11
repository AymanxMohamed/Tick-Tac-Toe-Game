CREATE DATABASE TicTacToe;

GO

USE TicTacToe;

GO

CREATE FUNCTION getMultiModeGameWinner(@firstPlayerScore INT, @noOfRounds INT, @firstPlayerName VARCHAR(50), @secondPlayerName VARCHAR(50))
RETURNS VARCHAR(50)
	BEGIN 
		DECLARE @winner VARCHAR(50)
		DECLARE @secondPLayerScore INT
		SET @secondPLayerScore = @noOfRounds - @firstPlayerScore
		IF @firstPlayerScore > @secondPLayerScore
			SET @winner = @firstPlayerName
		ELSE IF @firstPlayerScore < @secondPLayerScore
			SET @winner = @secondPlayerName
		ELSE 
			SET @winner = 'tied'
		RETURN @winner
	END;

GO

CREATE FUNCTION getSingleModeWinner(@playerScore INT, @noOfRounds INT)
RETURNS VARCHAR(10)
	BEGIN
		DECLARE @playerCase VARCHAR(10)
		DECLARE @pcScore INT
		SET @pcScore = @noOfRounds - @playerScore
		IF @playerScore > @pcScore
			SET @playerCase = 'winner'
		ELSE IF @playerScore < @pcScore
			SET @playerCase = 'loser'
		ELSE
			SET @playerCase = 'tied'
			
		RETURN @playerCase
	END;
GO

CREATE FUNCTION getPlayerRank(@playerPoints INT)
RETURNS VARCHAR(15)
	BEGIN 
		DECLARE @rank VARCHAR(15)
			IF @playerPoints >= 0 AND @playerPoints < 10
				SET @rank = 'Bronze'
			ELSE IF @playerPoints >= 10 AND @playerPoints < 20
				SET @rank = 'Silver'
			ELSE IF @playerPoints >= 20 AND @playerPoints < 30
				SET @rank = 'Gold'
			ELSE IF @playerPoints >= 30 AND @playerPoints < 40
				SET @rank = 'Platinum'
			ELSE IF @playerPoints >= 40 AND @playerPoints < 50
				SET @rank = 'Master'
			ELSE IF @playerPoints >= 50 AND @playerPoints < 60
				SET @rank = 'Grand Master'
			ELSE IF @playerPoints >= 60
				SET @rank = 'Challenger'
			ELSE  
				SET  @rank ='Bronze'
		RETURN @rank
	END

GO

CREATE TABLE player (
	user_name VARCHAR(50) CONSTRAINT PK_user_name_player PRIMARY KEY ,
	password VARCHAR(MAX) NOT NULL,
	bonus_points INT NULL CONSTRAINT DK_bonus_points_player DEFAULT 0,
	player_rank AS dbo.getPlayerRank(bonus_points),
	register_date DATETIME CONSTRAINT DK_register_date_player DEFAULT GETDATE(),
	player_status VARCHAR(MAX) NOT NULL CONSTRAINT CK_player_status_player CHECK (player_status IN ('Online', 'Offline'))
	CONSTRAINT DK_player_status_player DEFAULT 'Offline'
);

GO

CREATE TABLE single_mode_game (
	game_id INT PRIMARY KEY IDENTITY(1,1),
	user_name VARCHAR(50),
	no_of_rounds INT NOT NULL,
	player_score INT NOT NULL,
	difficulty VARCHAR(10) NOT NULL CONSTRAINT CK_difficulty_single_mode_game CHECK (difficulty IN ('easy', 'medium', 'hard')),
	player_case AS(dbo.getSingleModeWinner(player_score, no_of_rounds)),
	game_record VARCHAR(MAX) NULL,
	game_date DATETIME CONSTRAINT DK_register_date_single_mode_game DEFAULT GETDATE(),
	CONSTRAINT FK_user_name_single_mode_game_player FOREIGN KEY (user_name) REFERENCES player(user_name)
);

GO

CREATE TABLE multi_mode_game (
	game_id INT CONSTRAINT pk_game_number_multi_mode_game PRIMARY KEY,
	first_player_user_name VARCHAR(50) CONSTRAINT FK_first_player_user_multi_mode_player REFERENCES player(user_name),
	second_player_user_name VARCHAR(50) CONSTRAINT FK_second_player_user_multi_mode_player REFERENCES player(user_name),
	game_type VARCHAR(5) CONSTRAINT CK_game_type_multi_mode_game CHECK (game_type IN('local', 'lan')),
	no_of_rounds INT NOT NULL,
	first_player_score INT NOT NULL,
	second_player_score AS (no_of_rounds - first_player_Score) PERSISTED,
	winner AS (dbo.getMultiModeGameWinner(first_player_score, no_of_rounds, first_player_user_name, second_player_user_name)),
	game_record VARCHAR(MAX) NULL,
	game_date DATETIME CONSTRAINT DK_register_date_multi_mode_game DEFAULT GETDATE(),
);

GO

/**
	DROP TABLE multi_mode_game;
	go
	DROP TABLE single_mode_game;
	go
	DROP TABLE player;
	
	DELETE FROM multi_mode_game;
	GO
	DELETE FROM single_mode_game;
	GO
	DELETE FROM PLAYER;
*/

select * from player;	
go
select * from single_mode_game;
go
select * from multi_mode_game;

SELECT * FROM multi_mode_game WHERE first_player_user_name = 'ahmed' OR second_player_user_name = 'ahmed';
SELECT * FROM single_mode_game WHERE  user_name = 'ayman';
select password from player where user_name = 'ahmed';