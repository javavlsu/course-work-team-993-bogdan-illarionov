BEGIN;

ALTER TABLE users
DROP COLUMN IF EXISTS balance;

ALTER TABLE users
ADD balance DECIMAL(10,2) DEFAULT 0;

ALTER TABLE users
ADD CONSTRAINT balance_positive CHECK ( balance >= 0);

UPDATE db_version SET 
version = 9,
update_date = NOW();

COMMIT;