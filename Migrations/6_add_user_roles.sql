BEGIN;

ALTER TABLE IF EXISTS role
RENAME TO roles;

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles(
    user_id BIGINT NOT NULL,
    role_id SMALLINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id) 
);

UPDATE db_version SET 
version = 6,
update_date = NOW();

COMMIT;