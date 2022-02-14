
USE TicTacToe;

GO
INSERT INTO PLAYER (user_name, password)
VALUES('ayman1', 'eff140be3f27b5012192fce5242dfe2ce6fb9379dd0b24635c6b0fb1e3c894f697467e604284fa151a760362a5f25ed84fa4b37547e935e57251b18c3dda491f');
GO

GO
INSERT INTO 
single_mode_game(user_name, no_of_rounds, player_score, difficulty, game_record)
VALUES('AYMAN', 10, 5, 'easy', null);
GO
select * from player;	
go
select * from single_mode_game;
go
select * from multi_mode_game;

SELECT * FROM player WHERE player_status = 'Online';

SELECT * FROM player WHERE user_name = 'shopaky';

UPDATE PLAYER SET player_status = 'Offline' WHERE user_name IN('ayman', 'amr', 'shopaky');
UPDATE PLAYER SET player_status = 'Offline';

SELECT * FROM multi_mode_game WHERE first_player_user_name = 'ahmed' OR second_player_user_name = 'ahmed';
SELECT * FROM single_mode_game WHERE  user_name = 'ayman';
select password from player where user_name = 'ahmed';



GO
--DROP TABLE multi_mode_game;
--GO
--DROP TABLE single_mode_game;
--GO
--DROP TABLE player;
GO
--DELETE FROM multi_mode_game;
--GO
--DELETE FROM single_mode_game;
--GO
--DELETE FROM PLAYER;