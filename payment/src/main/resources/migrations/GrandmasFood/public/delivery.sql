create table delivery
(
    order_id      uuid      not null
        constraint delivery_pk
            unique
        constraint delivery_orders_uuid_fk
            references orders (uuid),
    delivery_time timestamp not null,
    id            integer   not null
        constraint delivery_pk_2
            primary key
);

alter table delivery
    owner to postgres;

