--liquibase formatted sql
--changeset Volha:4
/* create table users */

ALTER TABLE MESSENGER.users
    ADD COLUMN if not exists avatar_id varchar(30);

--rollback    DROP TABLE MESSENGER.users;
