--liquibase formatted sql

--changeset kuzneczov:1
CREATE TABLE IF NOT EXISTS revision
(
    id SERIAL PRIMARY KEY ,
    timestamp BIGINT NOT NULL
);

--changeset kuzneczov:2
CREATE TABLE IF NOT EXISTS users_aud
(
    id BIGINT,
    rev INT REFERENCES revision (id),
    revtype SMALLINT ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    birth_date DATE,
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    role VARCHAR(32),
    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    created_by VARCHAR(64),
    modified_by VARCHAR(64),
    company_id INT
);