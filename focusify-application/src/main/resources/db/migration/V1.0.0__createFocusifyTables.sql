create table todo
(
    id bigint not null
        constraint todo_pkey
            primary key,
    description varchar(255),
    status integer,
    title varchar(255)
);

create table users
(
    id bigint not null
        constraint users_pkey
            primary key,
    email varchar(255),
    username varchar(255)
);

create sequence hibernate_sequence;
