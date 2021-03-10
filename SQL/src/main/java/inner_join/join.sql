select * from new_bd.public.people;
select * from new_bd.public.animal;
select * from people as p join animal as a on p.id = a.people_id;
select p.name as host_name, a.name as pet_name   from people as p join animal as a on p.id = a.people_id;