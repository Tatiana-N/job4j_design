create table type
(
    id   serial primary key,
    name varchar(50)
);
create table product
(
    id   serial primary key,
    name varchar(50),
    type_id int references type (id),
    expired_date date ,
    price int
);
