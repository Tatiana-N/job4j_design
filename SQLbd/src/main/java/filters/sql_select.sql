CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);
CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);
insert into company (id, name) VALUES (1, 'MTS');
insert into company (id, name) VALUES (2, 'Beeline');
insert into company (id, name) VALUES (3, 'Tele2');
insert into company (id, name) VALUES (4, 'Yota');
insert into company (id, name) VALUES (5, 'Megafon');
insert  into person (id, name, company_id) values (1, 'Таня', 1);
insert  into person (id, name, company_id) values (2, 'Саша', 2);
insert  into person (id, name, company_id) values (3, 'Коля', 3);
insert  into person (id, name, company_id) values (4, 'Лёша', 1);
insert  into person (id, name, company_id) values (5, 'Юля', 2);
insert  into person (id, name) values (6, 'Света');
insert  into person (id, name) values (7, 'Сережа');
insert  into person (id, name, company_id) values (8, 'Tom', 5);
insert  into person (id, name, company_id) values (9, 'Tom', 1);
-- 1. В одном запросе получить
-- - имена всех person, которые не состоят в компании с id = 5;
select p.name from person as p where company_id != 5 or company_id is null ;
-- - название компании для каждого человека.
select p.name, c.name from person as p left join company as c on p.company_id = c.id;
-- два условия вместе
select p.name, c.name from person as p left join company as c on p.company_id = c.id where company_id != 5 or company_id is null;
-- 2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
select company.name, count(*) as count from company join person on company.id = person.company_id group by company.name having
count(*) =  (select max(count) from(select (count(*)) as count from company as c join person as p on p.company_id = c.id group by c.name) as new);
