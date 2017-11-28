DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO public.meals (id, user_id, date_time, description, calories)
VALUES (2, 100000, '2017-11-28 15:18:51.487000', 'test', 500);
INSERT INTO public.meals (id, user_id, date_time, description, calories)
VALUES (3, 100000, '2017-11-28 15:18:51.487000', 'test1', 1000);
INSERT INTO public.meals (id, user_id, date_time, description, calories)
VALUES (4, 100000, '2017-11-28 15:18:51.487000', 'test2', 222);
INSERT INTO public.meals (id, user_id, date_time, description, calories)
VALUES (5, 100001, '2017-11-28 15:18:51.487000', 'test', 500);
INSERT INTO public.meals (id, user_id, date_time, description, calories)
VALUES (6, 100001, '2017-11-28 15:18:51.487000', 'test1', 2000);
INSERT INTO public.meals (id, user_id, date_time, description, calories)
VALUES (7, 100001, '2017-11-28 15:18:51.487000', 'test2', 500);