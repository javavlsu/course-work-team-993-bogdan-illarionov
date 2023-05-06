BEGIN;

/* Орел-решка */
INSERT INTO lots(id, name, description)
VALUES(
	2,
	'Подбрось монетку',
	'Угадай, как упадет монетка и забери приз.');

INSERT INTO outcomes(id, value, koef, lot_id)
VALUES 

(45, 'Орел', 1.75, 2), (46, 'Решка', 1.75, 2);

INSERT INTO game_outcomes(id, lot_id, view)
VALUES 

(39, 2, '🦅 Орел'),(40, 2, '💰 Решка');

INSERT INTO outcomes_game_outcomes(outcome_id, game_outcome_id)
VALUES 
(45, 39),(46, 40);

/* Скачки */
INSERT INTO lots(id, name, description)
VALUES(
	3,
	'Скачки с LuckyDay',
	'Помоги скакуну LuckyDay добраться до финиша.');

INSERT INTO outcomes(id, value, koef, lot_id)
VALUES 

(47, 'LuckyDay будет первым', 6, 3), 
(48, 'LuckyDay будет вторым', 6, 3),
(49, 'LuckyDay будет третьим', 6, 3),
(50, 'LuckyDay будет четвертым', 6, 3),
(51, 'LuckyDay будет пятым', 6, 3),
(52, 'LuckyDay будет шестым', 6, 3),
(53, 'LuckyDay будет седьмым', 6, 3),

(54, 'LuckyDay обгонит одного', 1.05, 3),
(55, 'LuckyDay обгонит двоих', 1.2, 3),
(56, 'LuckyDay обгонит троих', 1.5, 3),

(57, 'LuckyDay займет призовое место', 2, 3);

INSERT INTO game_outcomes(id, lot_id, view)
VALUES 

(41, 3, '1 место 🥇'),(42, 3, '2 место 🥈'), (43, 3, '3 место 🥉'),
(44, 3, '4 место'),(45, 3, '5 место'), (46, 3, '6 место'),
(47, 3, '7 место');

INSERT INTO outcomes_game_outcomes(outcome_id, game_outcome_id)
VALUES 
/*Конкретное место*/
(47, 41), (48, 42), (49, 43), (50, 44),
(51, 45), (52, 46), (53, 47), 

/*Обгонит одного*/
(54, 41), (54, 42), (54, 43), (54, 44), 
(54, 45), (54, 46),

/*Обгонит двоих*/
(55, 41), (55, 42), (55, 43), (55, 44), 
(55, 45),

/*Обгонит троих*/
(56, 41), (56, 42), (56, 43), (56, 44), 

/*Займет призовое место*/
(57, 41), (57, 42), (57, 43);

COMMIT;