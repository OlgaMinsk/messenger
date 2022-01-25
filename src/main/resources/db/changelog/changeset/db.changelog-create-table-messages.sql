--liquibase formatted sql
--changeset Volha:2
/* create table messages */

CREATE TABLE IF NOT EXISTS MESSENGER.messages
(
    message_id bigserial
        primary key,
    message    varchar not null,
    sender    bigint,
    receiver   bigint,
    FOREIGN KEY (sender)
        REFERENCES MESSENGER.users (user_id),
    FOREIGN KEY (receiver)
        REFERENCES MESSENGER.users (user_id)
)
--rollback DROP TABLE MESSENGER.messages;
