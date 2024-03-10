delete from rule;

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 60, 'https://ya.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 30, 'https://mail.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 20, 'https://madbrains.ru/');

--
-- insert into rule(expected_status_code, is_activated, period_in_seconds, url)
-- values (200, true, 40, 'https://ozon.ru/');
--
-- insert into rule(expected_status_code, is_activated, period_in_seconds, url)
-- values (200, true, 100, 'https://wildberries.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (201, true, 20, 'https://mock.codes/200');


insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 20, 'https://mock.codes/404');


delete from users;

insert into users(login, password, roles) values ('user_login', '$2a$10$CnzaM9DEe0cGqf5YFewK9eS9ixMFWUN1BrvYRv.MIOGNY/PZYb3NG', array[0])


