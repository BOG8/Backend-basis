CREATE TABLE public.users
(
    id SERIAL PRIMARY KEY NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX users_login_uindex ON public.users (login);
CREATE UNIQUE INDEX users_password_uindex ON public.users (password);
CREATE UNIQUE INDEX users_email_uindex ON public.users (email);