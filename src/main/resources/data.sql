delete from rule;

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 10, 'https://ya.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 10, 'https://mail.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 10, 'https://madbrains.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 10, 'https://ozon.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 10, 'https://wildberries.ru/');

