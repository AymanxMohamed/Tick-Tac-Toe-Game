CREATE DATABASE TicTacToe;

GO

USE TicTacToe;

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
	game_id VARCHAR(50) CONSTRAINT pk_game_id_single_mode_game PRIMARY KEY,
	user_name VARCHAR(50),
	player_type VARCHAR(1) CONSTRAINT CK_player_type_single_mode_game CHECK (player_type IN ('X', 'O')),
	difficulty VARCHAR(10) NOT NULL CONSTRAINT CK_difficulty_single_mode_game CHECK (difficulty IN ('easy', 'medium', 'hard')),
	player_case VARCHAR(MAX) CONSTRAINT CK_player_case_single_mode_game CHECK (player_case IN ('winner', 'loser', 'draw')),
	game_record VARCHAR(MAX) NULL,
	game_date DATETIME CONSTRAINT DK_register_date_single_mode_game DEFAULT GETDATE(),
	CONSTRAINT FK_user_name_single_mode_game_player FOREIGN KEY (user_name) REFERENCES player(user_name)
);

GO

CREATE TABLE multi_mode_game (
	game_id INT CONSTRAINT pk_game_id_multi_mode_game PRIMARY KEY,
	player_x_user_name VARCHAR(50) CONSTRAINT FK_player_x_user_multi_mode_player REFERENCES player(user_name),
	player_o_user_name VARCHAR(50) CONSTRAINT FK_player_y_user_multi_mode_player REFERENCES player(user_name),
	winner VARCHAR(MAX),
	game_record VARCHAR(MAX) NULL,
	game_date DATETIME CONSTRAINT DK_register_date_multi_mode_game DEFAULT GETDATE(),
);

GO
