insert into bonuses(Id, Name, expire_type_id, trigger_action_id)

values (1, 'Test', 2, 2);

update bonuses set is_enabled = true where id = 1;

insert into users_bonuses (user_id, bonus_id) values (1,1);
