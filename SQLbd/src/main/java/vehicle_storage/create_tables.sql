create table Body
(
    id serial primary key,
    name varchar (50)
);
create table Engine
(
    id serial primary key,
    name varchar (50)
);
create table Gearbox
(
    id serial primary key,
    name varchar (50)
);
create table Avto
(
    id serial primary key,
    name varchar (50),
    body_id int references Body(id) not null,
    engine_id int references Engine(id) not null,
    gearbox_id int references Gearbox(id) not null
);