select * from departments;
select * from employees;
-- inner join
select e.name as name, d.name as department from departments as d  join employees as e on d.id = e.department_id;
-- left join
select e.name as name, d.name as department from departments as d left join employees as e on e.department_id = d.id;
-- right join
select e.name as name, d.name as department from departments as d right join employees as e on e.department_id = d.id;
-- full join
select e.name as name, d.name as department from departments as d full join employees as e on e.department_id = d.id;
--  найти департаменты, у которых нет работников
select d.name as department_without_man from departments as d left join employees as e on e.department_id = d.id where e.name is null ;
-- написать запросы, которые давали бы одинаковый результат.
select e.name as name, d.name as department from departments as d left join employees e on d.id = e.department_id where e.name is not null;
select e.name as name, d.name as department from employees e right join departments as d on d.id = e.department_id where d.name is not null;
-- Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
select * from teens as t1 cross join teens as t2 where t1.gender not like t2.gender and t1.gender like 'м';