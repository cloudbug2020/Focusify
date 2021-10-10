create table todo
(
    id bigint not null
        constraint todo_pkey
            primary key,
    description varchar(255),
    status integer,
    title varchar(255)
);

create sequence hibernate_sequence;
