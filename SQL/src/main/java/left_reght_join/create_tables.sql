create table departments
(
    id   serial primary key,
    name varchar(50)
);
create table employees
(
    id   serial primary key,
    name varchar(50),
    department_id int references departments(id)
);