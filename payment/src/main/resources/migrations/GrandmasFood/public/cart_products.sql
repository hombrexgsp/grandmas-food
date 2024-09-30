create table cart_products
(
    order_id uuid    not null
        constraint cart_products_orders_uuid_fk
            references orders (uuid),
    combo_id uuid,
    quantity integer not null,
    id       bigint  not null
        constraint cart_products_pk
            primary key
);

alter table cart_products
    owner to postgres;

