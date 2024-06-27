CREATE TABLE todos
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    description VARCHAR(200) NOT NULL,
    completed   BOOLEAN      NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    user_id     UUID         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);