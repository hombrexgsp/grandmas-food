create table orders
(
    id                integer          not null
        constraint orders_pk
            primary key,
    uuid              uuid             not null
        constraint orders_pk_2
            unique,
    creation_time     timestamp        not null,
    extra_information varchar(255)     not null,
    subtotal          double precision not null,
    user_id           bigint           not null
        constraint orders_user_document_number_fk
            references "user" (document_number)
);

alter table orders
    owner to postgres;

