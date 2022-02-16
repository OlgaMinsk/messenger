--liquibase formatted sql
--changeset Volha:1
/* create SCHEMA MESSENGER */

CREATE SCHEMA IF NOT EXISTS  MESSENGER;

--rollback DROP SCHEMA MESSENGER;