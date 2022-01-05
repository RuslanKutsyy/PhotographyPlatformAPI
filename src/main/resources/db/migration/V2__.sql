ALTER TABLE passwords
    ADD faile_attempts_count INTEGER;

ALTER TABLE passwords
    ADD is_locked BOOLEAN;

ALTER TABLE user_roles
    ADD CONSTRAINT pk_user_roles PRIMARY KEY (role_id, user_id);

ALTER TABLE users
    ALTER COLUMN email TYPE VARCHAR(255) USING (email::VARCHAR(255));

ALTER TABLE users
    ALTER COLUMN first_name TYPE VARCHAR(255) USING (first_name::VARCHAR(255));

ALTER TABLE users
    ALTER COLUMN last_name TYPE VARCHAR(255) USING (last_name::VARCHAR(255));