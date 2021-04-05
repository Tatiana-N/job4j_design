-- цена всех устройств
select avg(price) as  average_cost_of_devices from devices ;
--объединенная таблица для наглядности
select p.name as name ,d.name as device ,d.price as price from devices_people as dp inner join people as p on dp.people_id = p.id inner join devices d on d.id = dp.device_id;
-- средняя цена всех устройств человека
select p.name as name, avg(d.price) as average_cost_of_man_devices  from people as p join devices_people as dp on dp.people_id = p.id join devices as d on d.id = dp.device_id
group by p.name;
-- цена всех устройств человека
select p.name as name , sum(d.price) as cost_of_devices  from people as p join devices_people as dp on dp.people_id = p.id join devices as d on d.id = dp.device_id
group by p.name;
-- цена всех устройств человека > 4000
select p.name as name , avg(d.price) as cost_of_devices  from people as p join devices_people as dp on dp.people_id = p.id join devices as d on d.id = dp.device_id
group by p.name
having avg(d.price) > 4000;