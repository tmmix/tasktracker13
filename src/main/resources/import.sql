DROP TABLE tasks IF EXISTS;
CREATE TABLE IF NOT EXISTS tasks (id BIGSERIAL, title VARCHAR(255), owner_name VARCHAR(255), executor_name VARCHAR(255), description VARCHAR(4000), status_name VARCHAR(20), status VARCHAR(20), PRIMARY KEY (id));
INSERT INTO tasks (id, title, owner_name, executor_name, description, status_name) VALUES (1L, 'Задание Один', 'Белов А.А.', 'Голубикин В.В.', 'Выполнить задание Один', 'Создана');
INSERT INTO tasks (id, title, owner_name, executor_name, description, status_name) VALUES (7L, 'Задание Семь', 'Рогов П.П.', 'Белов А.А.', 'Выполнить задание Семь', 'Создана');
INSERT INTO tasks (id, title, owner_name, executor_name, description, status_name) VALUES (4L, 'Еще одно задание', 'Камский Е.К.', 'Журов К.В.', 'Организовать поставку', 'Создана');
INSERT INTO tasks (id, title, owner_name, executor_name, description, status_name) VALUES (6L, 'Купить молоко', 'Камский Е.К.', 'Журов К.В.', 'Организовать поставку', 'Создана');
INSERT INTO tasks (id, title, owner_name, executor_name, description, status_name) VALUES (5L, 'Собрать самолет', 'Камский Е.К.', 'Журов К.В.', 'Организовать поставку', 'Создана');
INSERT INTO tasks (id, title, owner_name, executor_name, description, status_name) VALUES (8L, 'Научиться новому', 'Камский Е.К.', 'Журов К.В.', 'Организовать поставку', 'Создана');

--DROP TABLE items IF EXISTS;
--CREATE TABLE IF NOT EXISTS items (id INTEGER, title VARCHAR(255), PRIMARY KEY (id));
--
--DROP sequence if exists item_seq;
--CREATE sequence item_seq START WITH 1 MINVALUE 1 INCREMENT BY 50;
--
--INSERT INTO items (id, title) VALUES (item_seq.NEXTVAL, 'Box');