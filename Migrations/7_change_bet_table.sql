BEGIN;

ALTER TABLE bets
ADD id BIGSERIAL NOT NULL;

UPDATE db_version SET 
version = 7,
update_date = NOW();

COMMIT;