BEGIN;

ALTER TABLE game_outcomes
ADD view CHARACTER VARYING(20) NOT NULL;

UPDATE db_version SET 
version = 5,
update_date = NOW();

COMMIT;