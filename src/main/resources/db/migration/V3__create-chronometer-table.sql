CREATE TABLE chronometers
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    description VARCHAR(100) NOT NULL UNIQUE,
    created_at  TIMESTAMP    NOT NULL,
    duration    FLOAT        NOT NULL,
    interrupted BOOLEAN      NOT NULL,
    user_id     UUID         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
