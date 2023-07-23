CREATE SEQUENCE IF NOT EXISTS community_seq START 1 INCREMENT 50;
CREATE SEQUENCE IF NOT EXISTS reservation_seq START 1 INCREMENT 50;
CREATE SEQUENCE IF NOT EXISTS resident_seq START 1 INCREMENT 50;

CREATE TABLE IF NOT EXISTS community
(
    id               BIGINT  DEFAULT NEXTVAL('community_seq'),
    name             VARCHAR(64)       NOT NULL,
    type             VARCHAR(32)       NOT NULL,
    max_count        INTEGER DEFAULT 0 NOT NULL,
    current_count    INTEGER DEFAULT 0 NOT NULL,
    available        BOOLEAN GENERATED ALWAYS AS (max_count > current_count) STORED,
    created_at       TIMESTAMP         NOT NULL,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS reservation
(
    id               BIGINT DEFAULT NEXTVAL('reservation_seq'),
    community_id     BIGINT    NOT NULL,
    resident_id      BIGINT    NOT NULL,
    start_date       TIMESTAMP NOT NULL,
    end_date         TIMESTAMP NOT NULL,
    created_at       TIMESTAMP NOT NULL,
    slot_info        VARCHAR(1000),
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS resident
(
    id               BIGINT DEFAULT NEXTVAL('resident_seq'),
    community_id     BIGINT      NOT NULL,
    username         VARCHAR(32) NOT NULL,
    identifier       VARCHAR(32) NOT NULL,
    mail             VARCHAR(32) NOT NULL,
    created_at       TIMESTAMP   NOT NULL,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE community
    ADD CONSTRAINT UQ_community_name_type UNIQUE (name, type);

ALTER TABLE resident
    ADD CONSTRAINT UQ_resident_username_community_id UNIQUE (username, community_id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_reservation_community_id FOREIGN KEY (community_id) REFERENCES community;
ALTER TABLE reservation
    ADD CONSTRAINT FK_reservation_resident_id FOREIGN KEY (resident_id) REFERENCES resident;

CREATE INDEX IDX_reservation_community_id ON reservation (community_id);
CREATE INDEX IDX_reservation_resident_id ON reservation (resident_id);

--list

INSERT INTO community (id, name, type, max_count, created_at)
VALUES (1, 'GOOGLE', 'GYM', 10, NOW());

INSERT INTO community (id, name, type, max_count, created_at)
VALUES (2, 'GOOGLE', 'PARKING', 10, NOW());

ALTER SEQUENCE community_seq restart with 4;

INSERT INTO resident (id, community_id, username, mail, identifier, created_at)
VALUES (1, 1, 'user', 'example@gmial.com', 'random', NOW());

INSERT INTO resident (id, community_id, username, mail, identifier, created_at)
VALUES (2, 1, 'user1', 'example@gmial.com', 'random', NOW());

INSERT INTO resident (id, community_id, username, mail, identifier, created_at)
VALUES (3, 2, 'user', 'example@gmial.com', 'random', NOW());

ALTER SEQUENCE resident_seq restart with 4;

INSERT INTO reservation (id, community_id, resident_id, start_date, end_date, created_at)
VALUES (1, 1, 1, NOW(),NOW(),NOW());

INSERT INTO reservation (id, community_id, resident_id, start_date, end_date, created_at)
VALUES (2, 1, 2, NOW(),NOW(),NOW());

ALTER SEQUENCE reservation_seq restart with 3;