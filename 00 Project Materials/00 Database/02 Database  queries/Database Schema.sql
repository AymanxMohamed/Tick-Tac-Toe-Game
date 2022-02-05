USE TicTacToe;

GO

CREATE FUNCTION getMultiModeGameWinner(@firstPlayerScore INT, @noOfRounds INT, @firstPlayerName VARCHAR(50), @secondPlayerName VARCHAR(50))
RETURNS VARCHAR(50)
	BEGIN 
		DECLARE @result VARCHAR(50)
		IF @firstPlayerScore = (@noOfRounds / 2)
			SET @result = 'draw'
		ELSE IF @firstPlayerScore > (@noOfRounds / 2)
			SET @result = @firstPlayerName + ' won'
		ELSE
			SET @result = @secondPlayerName + ' won'
		RETURN @result
	END;

GO

CREATE FUNCTION getSingleModeWinner(@playerScore INT, @noOfRounds INT)
RETURNS VARCHAR(4)
	BEGIN
		DECLARE @result VARCHAR(4)
		IF @playerScore = (@noOfRounds / 2)
			SET @result = 'draw'
		ELSE IF @playerScore > (@noOfRounds / 2)
			SET @result = 'win'
		ELSE
			SET @result = 'lose'
		RETURN @result
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
	password INT NOT NULL,
	bonus_points INT NULL CONSTRAINT DK_bonus_points_player DEFAULT 0,
	player_rank AS dbo.getPlayerRank(bonus_points),
	register_date DATETIME CONSTRAINT DK_register_date_player DEFAULT GETDATE()
);

GO

CREATE TABLE single_mode_game (
	game_number INT IDENTITY(1,1),
	user_name VARCHAR(50),
	no_of_rounds INT NOT NULL,
	player_score INT NOT NULL,
	difficulty VARCHAR(10) NOT NULL CONSTRAINT CK_difficulty_single_mode_game CHECK (difficulty IN ('easy', 'medium', 'hard')),
	game_result AS(dbo.getSingleModeWinner(player_score, no_of_rounds)),
	game_record VARBINARY(MAX) NULL,
	game_date DATETIME CONSTRAINT DK_register_date_single_mode_game DEFAULT GETDATE(),
	CONSTRAINT PK_user_name_game_number_single_mode_game PRIMARY KEY (game_number, user_name),
	CONSTRAINT FK_user_name_single_mode_game_player FOREIGN KEY (user_name) REFERENCES player(user_name)
);

GO

CREATE TABLE multi_mode_game (
	game_number INT IDENTITY(1,1),
	first_player_user_name VARCHAR(50) CONSTRAINT FK_first_player_user_multi_mode_player REFERENCES player(user_name),
	second_player_user_name VARCHAR(50) CONSTRAINT FK_second_player_user_multi_mode_player REFERENCES player(user_name),
	game_type VARCHAR(5) CONSTRAINT CK_game_type_multi_mode_game CHECK (game_type IN('local', 'lan')),
	no_of_rounds INT NOT NULL,
	first_player_score INT NOT NULL,
	second_player_score AS (no_of_rounds - first_player_Score) PERSISTED,
	game_result AS (dbo.getMultiModeGameWinner(first_player_score, no_of_rounds, first_player_user_name, second_player_user_name)),
	game_record VARBINARY(MAX) NULL,
	game_date DATETIME CONSTRAINT DK_register_date_multi_mode_game DEFAULT GETDATE(),
	CONSTRAINT PK_first_player_user_second_player_game_date_user_multi_mode PRIMARY KEY(game_number, first_player_user_name, second_player_user_name)
);

GO

/**
	DROP TABLE multi_mode_game;
	DROP TABLE single_mode_game;
	DROP TABLE player;
*/