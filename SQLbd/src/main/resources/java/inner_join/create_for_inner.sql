create table people
(
    id   serial primary key,
    name varchar(50)
);
create table type_animal
(
    id   serial primary key,
    name varchar(10)
);
create table animal
(
    id             serial primary key,
    name           varchar(50),
    people_id      int references people (id),
    type_animal_id int references type_animal (id)
);
