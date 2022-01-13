--liquibase formatted sql
--changeset Volha:2
/* create table messages */

CREATE TABLE IF NOT EXISTS MESSENGER.messages
(
    message_id serial
        primary key,
    user_id    serial,
    FOREIGN KEY (user_id)
        REFERENCES users (user_id),
    message    varchar not null
)
--rollback DROP TABLE MESSENGER.messages;
