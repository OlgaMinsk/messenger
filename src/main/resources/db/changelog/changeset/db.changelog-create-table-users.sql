--liquibase formatted sql
--changeset Volha:3
/* create table users */

 CREATE TABLE IF NOT EXISTS MESSENGER.users
      (
        user_id   bigserial
        primary key,
        user_name varchar(50) not null
        unique
      )

--rollback    DROP TABLE MESSENGER.users;
