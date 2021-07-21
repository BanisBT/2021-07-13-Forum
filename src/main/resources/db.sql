DROP TABLE IF EXISTS user_table;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS topic;

CREATE TABLE user_table
(
    id       BIGSERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255)          NOT NULL,
    password VARCHAR(255)          NOT NULL,
    name     VARCHAR(255),
    surname  VARCHAR(255),
    email    VARCHAR(255),
    age      INTEGER
--     role       VARCHAR(15)           NOT NULL,
--     comment_id BIGINT REFERENCES comment(id),
--     created_at timestamp             NOT NULL DEFAULT current_timestamp,
--     updated_at timestamp             NOT NULL DEFAULT current_timestamp
-- TODO insert created and updated time with db not JAVA
--        ON UPDATE current_timestamp
);

CREATE TABLE comment
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    text TEXT
--     user_table_id BIGINT REFERENCES user_table(id),
--     created_at timestamp NOT NULL DEFAULT current_timestamp,
--     updated_at timestamp NOT NULL DEFAULT current_timestamp
--         ON UPDATE current_timestamp
);

CREATE TABLE topic
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    topic_tittle VARCHAR(255) NOT NULL,
    topic TEXT
);
