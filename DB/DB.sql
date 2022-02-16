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



CREATE SCHEMA MESSENGER_TEST;
create table MESSENGER_TEST.users
(
    user_id   bigserial
        primary key,
    user_name varchar(50) not null
        unique
);
create table MESSENGER_TEST.messages
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
);








<?xml version = "1.0" encoding = "UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
      http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.6.xsd">

    <include file="db/changelog/changeset/v002-changelog-create-table-users.sql"/>
    <include file="db/changelog/changeset/v003-changelog-create-table-messages.sql"/>

</databaseChangeLog >

<?xml version = "1.0" encoding = "UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
      http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.6.xsd">

    <changeSet id="1" author="Volha">

        <comment>create table users</comment>

        <sql>
CREATE TABLE IF NOT EXISTS MESSENGER.users
(
    user_id serial
        primary key,
    user_name varchar(50) not null
        unique
)
    </sql>

        <rollback>
DROP TABLE MESSENGER.users;
</rollback>

    </changeSet>

    <changeSet id="2" author="Volha">

        <comment>create table messages</comment>

        <sql>
CREATE TABLE IF NOT EXISTS MESSENGER.messages
(
    message_id serial
        primary key,
    user_id serial,
    FOREIGN KEY (user_id)
        REFERENCES users (user_id),
    message varchar not null
)
    </sql>

        <rollback>
DROP TABLE MESSENGER.messages;
</rollback>

    </changeSet>


</databaseChangeLog>