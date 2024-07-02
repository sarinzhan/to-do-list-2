create table task
(
    id           bigserial PRIMARY KEY,
    description  text not null,
    is_completed boolean      not null
);
