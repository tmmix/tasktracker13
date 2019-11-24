DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS users (id BIGSERIAL, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO users (id, name) VALUES (1, 'Bob');

DROP TABLE items IF EXISTS;
CREATE TABLE IF NOT EXISTS items (id INTEGER, title VARCHAR(255), PRIMARY KEY (id));

DROP sequence if exists item_seq;
CREATE sequence item_seq START WITH 1 MINVALUE 1 INCREMENT BY 50;

INSERT INTO items (id, title) VALUES (item_seq.NEXTVAL, 'Box');