BEGIN;

INSERT INTO lots(id, name, description)
VALUES(
	1,
	'Рулетка (golden green)',
	'Классическая рулетка с 36 числами, зеро и двойным зеро.
	Присутсвуют бонусы.');

INSERT INTO outcomes(id, value, koef, lot_id)
VALUES 

(1, '1', 35, 1),(2, '2', 35, 1),(3, '3', 35, 1),
(4, '4', 35, 1),(5, '5', 35, 1),(6, '6', 35, 1),
(7, '7', 35, 1),(8, '8', 35, 1),(9, '9', 35, 1),
(10, '10', 35, 1),(11, '11', 35, 1),(12, '12', 35, 1),
(13, '13', 35, 1),(14, '14', 35, 1),(15, '15', 35, 1),
(16, '16', 35, 1),(17, '17', 35, 1),(18, '18', 35, 1),
(19, '19', 35, 1),(20, '20', 35, 1),(21, '21', 35, 1),
(22, '22', 35, 1),(23, '23', 35, 1),(24, '24', 35, 1),
(25, '25', 35, 1),(26, '26', 35, 1),(27, '27', 35, 1),
(28, '28', 35, 1),(29, '29', 35, 1),(30, '30', 35, 1),
(31, '31', 35, 1),(32, '32', 35, 1),(33, '33', 35, 1),
(34, '34', 35, 1),(35, '35', 35, 1),(36, '36', 35, 1),
(37, '0', 35.5, 1),(38, '00', 35.5, 1),
(39, 'Четные', 1.2, 1),(40, 'Нечетные', 1.2, 1),
(41, '1-12', 2, 1),(42, '13-24', 2, 1),(43, '25-36', 2, 1),
(44, '0-00', 16, 1);

INSERT INTO game_outcomes(id, lot_id, view)
VALUES 

(1, 1, '1'),(2, 1, '2'),(3, 1, '3'),(4, 1, '4'),(5, 1, '5'),
(6, 1, '6'),(7, 1, '7'),(8, 1, '8'),(9, 1, '9'),(10, 1, '10'),
(11, 1, '11'),(12, 1, '12'),(13, 1, '13'),(14, 1, '14'),
(15, 1, '15'),(16, 1, '16'),(17, 1, '17'),(18, 1, '18'),
(19, 1, '19'),(20, 1, '20'),(21, 1, '21'),(22, 1, '22'),
(23, 1, '23'),(24, 1, '24'),(25, 1, '25'),(26, 1, '26'),
(27, 1, '27'),(28, 1, '28'),(29, 1, '29'),(30, 1, '30'),
(31, 1, '31'),(32, 1, '32'),(33, 1, '33'),(34, 1, '34'),
(35, 1, '35'),(36, 1, '36'),(37, 1, '0'),(38, 1, '00');

INSERT INTO outcomes_game_outcomes(outcome_id, game_outcome_id)
VALUES 
/* Число - число */
(1, 1),(2, 2),(3, 3),(4, 4),(5, 5),(6, 6),
(7, 7),(8, 8),(9, 9),(10, 10),(11, 11),(12, 12),
(13, 13),(14, 14),(15, 15),(16, 16),(17, 17),(18, 18),
(19, 19),(20, 20),(21, 21),(22, 22),(23, 23),(24, 24),
(25, 25),(26, 26),(27, 27),(28, 28),(29, 29),(30, 30),
(31, 31),(32, 32),(33, 33),(34, 34),(35, 35),(36, 36),
(37, 37),(38, 38),

/* Четные */
(39, 2), (39, 4), (39, 6), (39, 8), (39, 10),
(39, 12), (39, 14), (39, 16), (39, 18), (39, 20),
(39, 22), (39, 24), (39, 26), (39, 28), (39, 30),
(39, 32), (39, 34), (39, 36),

/* Нечетные */
(40, 1), (40, 3), (40, 5), (40, 7), (40, 9),
(40, 11), (40, 13), (40, 15), (40, 17), (40, 19),
(40, 21), (40, 23), (40, 25), (40, 27), (40, 29),
(40, 31), (40, 33), (40, 35),

/* 1-12 */
(41, 1), (41, 2), (41, 3), (41, 4), (41, 5),
(41, 6), (41, 7), (41, 8), (41, 9), (41, 10),
(41, 11), (41, 12),

/* 13-24 */
(42, 13), (42, 14), (42, 15), (42, 16), (42, 17),
(42, 18), (42, 19), (42, 20), (42, 21), (42, 22),
(42, 23), (42, 24),

/* 25-36 */
(43, 25), (43, 26), (43, 27), (43, 28), (43, 29),
(43, 30), (43, 31), (43, 32), (43, 33), (43, 34),
(43, 35), (43, 36),

/* 0-00 */
(44, 37), (44, 38);

COMMIT;