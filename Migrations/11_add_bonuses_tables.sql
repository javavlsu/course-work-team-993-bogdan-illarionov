BEGIN;

DROP TRIGGER IF EXISTS trg_add_user_bonus ON users_bonuses;
DROP TRIGGER IF EXISTS trg_add_new_bonus ON bonuses;

DROP TABLE IF EXISTS user_bonuses_config;
DROP TABLE IF EXISTS users_bonuses;
DROP TABLE IF EXISTS bonus_config;
DROP TABLE IF EXISTS bonuses;
DROP TABLE IF EXISTS bonus_trigger_action; 
DROP TABLE IF EXISTS bonus_expires_type; 

CREATE TABLE bonus_trigger_action (
	Id SMALLSERIAL NOT NULL PRIMARY KEY,
	Name VARCHAR(40) NOT NULL
);

CREATE TABLE bonus_expires_type (
	Id SMALLSERIAL NOT NULL PRIMARY KEY,
	Name VARCHAR(40) NOT NULL
);

INSERT INTO bonus_trigger_action (Id, Name) VALUES 
    (1, 'balance-add'),
    (2, 'lot-win'), 
    (3, 'lot-play');

INSERT INTO bonus_expires_type (Id, Name) VALUES 
    (1, 'Count'),
    (2, 'Term'), 
    (3, 'Unlimited');

CREATE TABLE bonuses (
	Id BIGSERIAL NOT NULL PRIMARY KEY,
	Name VARCHAR(50) NOT NULL,
	expire_type_id SMALLINT NOT NULL,
	trigger_action_id SMALLINT NOT NULL,
    is_enabled BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (expire_type_id) REFERENCES bonus_expires_type(id) ON DELETE RESTRICT,
    FOREIGN KEY (trigger_action_id) REFERENCES bonus_trigger_action(id) ON DELETE RESTRICT
);

CREATE TABLE bonus_config(
    bonus_id BIGINT NOT NULL PRIMARY KEY,
    trigger_count INT NULL,
    to_term TIMESTAMP NULL,
    lots_ids VARCHAR(80) NULL,
    bonus_koef DECIMAL(10,2) NULL,
    FOREIGN KEY (bonus_id) REFERENCES bonuses(id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION add_bonus_config()
RETURNS TRIGGER
AS
$trg$
DECLARE
    new_trigger_count INT := NULL;
    new_to_term TIMESTAMP := NULL;
    new_lots_ids VARCHAR := NULL;
    new_bonus_koef DECIMAL(10,2) := NULL;
BEGIN
	IF (NEW.trigger_action_id = 1) THEN -- добавление
        new_bonus_koef := 0;
    ELSIF (NEW.trigger_action_id = 2) THEN -- бонус к выигрушу
        new_bonus_koef := 0;
        new_lots_ids := '';
    ELSIF (NEW.trigger_action_id = 3) THEN -- повышенный шанс на выигрыш
        new_lots_ids := '';
    END IF;

    IF (NEW.expire_type_id = 1) THEN -- если истекающий тип по колличеству
        new_trigger_count := 0;
    ELSIF (NEW.expire_type_id = 2) THEN -- если истекает по сроку
        new_to_term := NOW() - interval '1 day';
    END IF;

    INSERT INTO bonus_config(bonus_id, trigger_count, to_term, lots_ids, bonus_koef)
    VALUES (NEW.id, new_trigger_count, new_to_term, new_lots_ids, new_bonus_koef);

	RETURN NEW;
END
$trg$ LANGUAGE plpgsql;


CREATE TRIGGER trg_add_new_bonus AFTER INSERT ON bonuses
    FOR EACH ROW
    EXECUTE PROCEDURE add_bonus_config();

CREATE TABLE users_bonuses (
    Id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    bonus_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (bonus_id) REFERENCES bonuses(id) ON DELETE CASCADE,
    UNIQUE (user_id, bonus_id)
);

DROP TYPE IF EXISTS user_bonuses_config_param_type;
CREATE TYPE user_bonuses_config_param_type AS ENUM ('decimal', 'int', 'timestamp', 'varchar', 'boolean', 'str-array');

CREATE TABLE user_bonuses_config(
    map_id BIGINT NOT NULL,
    param_name VARCHAR(50),
    param_value VARCHAR(100),
    param_type user_bonuses_config_param_type,
    FOREIGN KEY (map_id) REFERENCES users_bonuses(id) ON DELETE cascade,
    PRIMARY KEY(map_id, param_name)
);

CREATE OR REPLACE FUNCTION add_user_bonus_config()
RETURNS TRIGGER
AS
$trg$
DECLARE
    cur_is_enabled BOOLEAN;
    cur_expire_type SMALLINT;
    cur_trigger_type SMALLINT;
    cur_trigger_count INT;
    cur_to_term TIMESTAMP;
    cur_lots_ids VARCHAR ;
    cur_bonus_koef DECIMAL(10,2);
BEGIN
    SELECT b.is_enabled INTO cur_is_enabled
    FROM  bonuses b
    WHERE b.id = NEW.bonus_id;

    IF NOT cur_is_enabled THEN
        RAISE EXCEPTION 'bonus not enabled';
    END IF; 

    SELECT b.expire_type_id, b.trigger_action_id
    	INTO cur_expire_type, cur_trigger_type
    FROM  bonuses b
    WHERE  b.Id = NEW.bonus_id;

    SELECT bc.trigger_count, bc.to_term, bc.lots_ids, bc.bonus_koef
       INTO cur_trigger_count, cur_to_term, cur_lots_ids, cur_bonus_koef
    FROM  bonus_config bc
    WHERE  bc.bonus_id = NEW.bonus_id;

    -- ('decimal', 'int', 'timestamp', 'varchar', 'boolean', 'str-array') -- enum values
    INSERT INTO user_bonuses_config(map_id, param_name, param_value, param_type) VALUES
    (NEW.Id, 'is_enabled', 'true', 'boolean');

	-- IF (cur_trigger_type = 1) THEN -- добавление
    --     INSERT INTO user_bonuses_config(map_id, param_name, param_value, param_type) VALUES
    --         (NEW.Id, 'bonus_koef', CAST (cur_bonus_koef AS VARCHAR(100)), 'decimal');
    -- ELSIF (cur_trigger_type = 2) THEN -- бонус к выигрушу
    --     INSERT INTO user_bonuses_config(map_id, param_name, param_value, param_type) VALUES
    --         (NEW.Id, 'bonus_koef', CAST (cur_bonus_koef AS VARCHAR(100)), 'decimal');
    --     INSERT INTO user_bonuses_config(map_id, param_name, param_value, param_type) VALUES
    --         (NEW.Id, 'lots_ids', CAST (cur_lots_ids AS VARCHAR(100)), 'str-array');
    -- ELSIF (cur_trigger_type = 3) THEN -- повышенный шанс на выигрыш
    --     INSERT INTO user_bonuses_config(map_id, param_name, param_value, param_type) VALUES
    --         (NEW.Id, 'lots_ids', CAST (cur_lots_ids AS VARCHAR(100)), 'str-array');
    -- END IF;

    IF (cur_expire_type = 1) THEN -- если истекающий тип по колличеству
        INSERT INTO user_bonuses_config(map_id, param_name, param_value, param_type) VALUES
            (NEW.Id, 'trigger_count', CAST (cur_trigger_count AS VARCHAR(100)), 'int');
    ELSIF (cur_expire_type = 2) THEN -- если истекает по сроку
        INSERT INTO user_bonuses_config(map_id, param_name, param_value, param_type) VALUES
            (NEW.Id, 'to_term', CAST (cur_to_term AS VARCHAR(100)), 'timestamp');
    END IF;

	RETURN NEW;
END
$trg$ LANGUAGE plpgsql;


CREATE TRIGGER trg_add_user_bonus AFTER INSERT ON users_bonuses
    FOR EACH ROW
    EXECUTE PROCEDURE add_user_bonus_config();

CALL sp_update_db_version(11);

COMMIT;