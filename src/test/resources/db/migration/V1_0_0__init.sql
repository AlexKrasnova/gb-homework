create table customer
(
    id      bigserial primary key,
    name    text,
    version integer
);

create table product
(
    id      bigserial primary key,
    name    text,
    company text,
    price   numeric,
    version integer
);

create table purchase (
    id          bigserial primary key,
    date        date,
    customer_id bigserial,
    version     integer
);

create table purchase_detailing (
    purchase_id bigserial,
    product_id  bigserial,
    price       numeric,
    number      integer,
    version     integer,
    primary key (purchase_id, product_id)
);

