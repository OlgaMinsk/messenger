CREATE SCHEMA MESSENGER;
create table MESSENGER.users
(
    user_id   serial
        primary key,
    user_name varchar(50) not null
        unique
);
create table MESSENGER.messages
(
    message_id serial
        primary key,
    user_id    serial,
    FOREIGN KEY (user_id)
        REFERENCES users (user_id),
    message    varchar not null

);
select user_name as name, message from MESSENGER.users INNER JOIN  MESSENGER.messages ON  messages.user_id=users.user_id;
select user_name as name, message from MESSENGER.users, MESSENGER.messages where messages.user_id=users.user_id;

