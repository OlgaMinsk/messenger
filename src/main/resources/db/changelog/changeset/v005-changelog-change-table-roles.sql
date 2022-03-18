--liquibase formatted sql
--changeset Volha:4
/* create table users */

CREATE TABLE IF NOT EXISTS MESSENGER.roles
(
    role_id   bigserial
        primary key,
    role varchar(50) not null
        unique
);
insert into  MESSENGER.roles (role) VALUES ('ROLE_USER'), ('ROLE_ADMIN');
--rollback    DROP TABLE MESSENGER.users;
