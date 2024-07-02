create table task
(
    id           bigserial PRIMARY KEY,
    description  text not null,
    is_completed boolean      not null
);

create table users
(
    id         bigserial primary key,
    username      varchar(100) UNIQUE NOT NULL,
    password   varchar(60)        NOT NULL,
    is_active boolean not null default true
);

insert into users (username, password)
values ('admin','$2a$04$1DIGVjbRelOas4McvldMreAA2VJOLyx9X08Uii.b03Vl5BAPcWKzm');