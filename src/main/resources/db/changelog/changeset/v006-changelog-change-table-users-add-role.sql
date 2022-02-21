--liquibase formatted sql
--changeset Volha:4
/* create table users */

ALTER TABLE MESSENGER.users
    ADD COLUMN if not exists role_id bigint
    REFERENCES MESSENGER.roles default 1;


