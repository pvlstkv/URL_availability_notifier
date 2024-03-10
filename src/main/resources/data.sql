delete from rule;

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 60, 'https://ya.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 30, 'https://mail.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 20, 'https://madbrains.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 40, 'https://ozon.ru/');

insert into rule(expected_status_code, is_activated, period_in_seconds, url)
values (200, true, 100, 'https://wildberries.ru/');

