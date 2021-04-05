select * from new_bd.public.people;
select * from new_bd.public.animal;
select * from people as p join animal as a on p.id = a.people_id;
select p.name as host_name, a.name as pet_name   from people as p join animal as a on p.id = a.people_id;
select p.name as host_name, a.name as pet_name, t.name as pets from people  as p  inner join animal as a join type_animal as t on a.type_animal_id = t.id on p.id = a.people_id ;
select p.name as host_name, a.name as pet_name, t.name as pets from animal as a inner join people as p on p.id = a.people_id inner join type_animal as t on t.id = a.type_animal_id;