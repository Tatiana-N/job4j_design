create table people
(
    id   serial primary key,
    name varchar(50)
);
create table animal
(
    id        serial primary key,
    name      varchar(50),
    people_id int references people (id)
);