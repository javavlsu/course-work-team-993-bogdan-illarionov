BEGIN;

DROP TABLE IF EXISTS game_outcomes;

CREATE TABLE game_outcomes(
	id BIGSERIAL PRIMARY KEY,
	lot_id BIGINT NOT NULL,
	FOREIGN KEY (lot_id) REFERENCES lots (id) ON DELETE CASCADE
);

CREATE TABLE outcomes_game_outcomes(
	outcome_id BIGINT NOT NULL,
	FOREIGN KEY (outcome_id) REFERENCES outcomes (id) ON DELETE CASCADE,
	game_outcome_id BIGINT NOT NULL,
	FOREIGN KEY (game_outcome_id) REFERENCES game_outcomes (id) ON DELETE CASCADE
);

UPDATE db_version SET 
version = 4,
update_date = NOW();

COMMIT;