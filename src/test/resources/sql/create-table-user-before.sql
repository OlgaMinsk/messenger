CREATE TABLE IF NOT EXISTS messenger.users
(
    user_id   bigserial
    primary key,
    user_name varchar(50) not null
    unique
    )

