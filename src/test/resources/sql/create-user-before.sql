
delete from  messenger.users;
insert into  messenger.users(user_id, user_name)
VALUES (1, 'David'),
       (2, 'Mary'),
       (3, 'Mike'),
       (4, 'Nadya');

alter sequence  messenger.users_user_id_seq restart with 10;