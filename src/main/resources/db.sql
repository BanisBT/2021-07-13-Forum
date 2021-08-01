DROP TABLE IF EXISTS user_table;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user_role;

CREATE TABLE user_table
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    username   VARCHAR(255) UNIQUE   NOT NULL,
    password   VARCHAR(255)          NOT NULL,
    name       VARCHAR(255),
    surname    VARCHAR(255),
    email      VARCHAR(255),
    age        INTEGER,
    created_at timestamp             NOT NULL DEFAULT current_timestamp,
    updated_at timestamp             NOT NULL DEFAULT current_timestamp
);

CREATE TABLE topic
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    topic_tittle VARCHAR(255)          NOT NULL,
    topic_text   TEXT,
    author       VARCHAR(255),
    created_at   timestamp             NOT NULL DEFAULT current_timestamp,
    updated_at   timestamp             NOT NULL DEFAULT current_timestamp
);

CREATE TABLE comment
(
    id            BIGSERIAL PRIMARY KEY        NOT NULL,
    user_table_id BIGINT REFERENCES user_table (id),
    topic_id      BIGINT REFERENCES topic (id) NOT NULL,
    text          TEXT,
    created_at    timestamp                    NOT NULL DEFAULT current_timestamp,
    updated_at    timestamp                    NOT NULL DEFAULT current_timestamp
);

CREATE TABLE role
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(50) UNIQUE    NOT NULL,
    created_at timestamp             NOT NULL DEFAULT current_timestamp,
    updated_at timestamp             NOT NULL DEFAULT current_timestamp
);

CREATE TABLE user_role
(
    user_id BIGINT REFERENCES user_table (id),
    role_id BIGINT REFERENCES role (id)
);

INSERT INTO user_table (username, password, name, surname, age)
VALUES ('admin', '{bcrypt}$2a$10$WJvAKW5R1VM2SSaAWf0WYO/FBcovz6X3BpulRoS2FWdUbcCZPo8V2', 'John', 'Admin', 48),
       ('user', '{bcrypt}$2a$10$WJvAKW5R1VM2SSaAWf0WYO/FBcovz6X3BpulRoS2FWdUbcCZPo8V2', 'Michael', 'User', 24);

INSERT INTO role (name)
VALUES ('USER'),
       ('ADMIN');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1);

INSERT INTO topic (topic_tittle, topic_text, author)
VALUES ('Didžiosios Britanijos GP: įdomioji statistika', 'Ka apie tai manote', 'unknown'),
       ('Didžiosios Britanijos GP: įdomioji statistika', 'Ka apie tai manote', 'unknown'),
       ('Didžiosios Britanijos GP: įdomioji statistika', 'Ka apie tai manote', 'unknown'),
       ('Didžiosios Britanijos GP: įdomioji statistika', 'Ka apie tai manote', 'unknown'),
       ('A. Albonas: Verstappenas galėjo elgtis agresyviau', 'Ka apie tai manote', 'A. Albonas'),
       ('A. Albonas: Verstappenas galėjo elgtis agresyviau', 'Ka apie tai manote', 'A. Albonas'),
       ('A. Albonas: Verstappenas galėjo elgtis agresyviau', 'Ka apie tai manote', 'A. Albonas'),
       ('M. Hakkinenas: tai buvo grynai lenktyninis incidentas', 'Ka apie tai manote', 'M. Hakkinenas'),
       ('M. Hakkinenas: tai buvo grynai lenktyninis incidentas', 'Ka apie tai manote', 'M. Hakkinenas'),
       ('D. Hillas: tai buvo pareiškimas apie ketinimus', 'Ka apie tai manote', 'D. Hilla'),
       ('D. Hillas: tai buvo pareiškimas apie ketinimus', 'Ka apie tai manote', 'D. Hilla'),
       ('D. Hillas: tai buvo pareiškimas apie ketinimus', 'Ka apie tai manote', 'D. Hilla');

INSERT INTO comment (user_table_id, topic_id, text)
VALUES (2, 1, 'Gerai viskas'),
       (2, 1, 'Gerai viskas bus'),
       (2, 1, 'Gerai viskas bus eina'),
       (2, 1, 'Gerai viskas bus eina'),
       (2, 1, 'Gerai viskas bus eina'),
       (2, 1, 'Gerai viskas bus eina'),
       (1,1,'Admin komentaras'),
       (2,2,'User 2-tro komentaras')
