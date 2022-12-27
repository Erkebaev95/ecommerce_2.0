-- auto-generated definition
create table values
(
    id         bigserial
        constraint values_pk
            primary key,
    product_id bigint
        constraint values_products_id_fk
            references products,
    option_id  bigint
        constraint values_options_id_fk
            references options,
    value      varchar(200) not null
);

alter table values
    owner to postgres;

create unique index values_id_uindex
    on values (id);

-- auto-generated definition
create table categories
(
    id   bigserial
        constraint categories_pk
            primary key,
    name varchar(100) not null
);

alter table categories
    owner to postgres;

create unique index categories_id_uindex
    on categories (id);

-- auto-generated definition
create table options
(
    id          bigserial
        constraint options_pk
            primary key,
    category_id bigint
        constraint options_categories_id_fk
            references categories,
    name        varchar(200) not null
);

alter table options
    owner to postgres;

create unique index options_id_uindex
    on options (id);

-- auto-generated definition
create table products
(
    id          bigserial
        constraint products_pk
            primary key,
    name        varchar(150) not null,
    price       bigint       not null,
    category_id bigint
        constraint products_categories_id_fk
            references categories
);

alter table products
    owner to postgres;

create unique index products_id_uindex
    on products (id);

--
create table roles
(
    id           serial8 primary key,
    service_name varchar(20) unique,
    display_name varchar(30) unique
);

create table users
(
    id       serial8 primary key,
    login    varchar unique,
    password varchar not null,
    email    varchar unique,
    name     varchar not null,
    surname  varchar not null,
    role_id  int4    not null,
    foreign key (role_id) references roles (id)
);

create table orders
(
    id        serial8 primary key,
    status_id int8    not null,
    street    varchar not null,
    house     varchar not null,
    apartment int4    not null,
    user_id   int8    not null,
    count_id  int8    not null,
    foreign key (status_id) references status (id),
    foreign key (user_id) references users (id),
    foreign key (count_id) references order_count (id)
);

create table order_count
(
    id         serial8 primary key,
    count      int4 not null,
    order_id   int4 not null,
    product_id int8 not null,
    foreign key (order_id) references orders (id),
    foreign key (product_id) references products (id)
);

create table status
(
    id   serial8 primary key,
    name varchar not null
);

