--  список всех машин и все привязанные к ним детали.
select a.name as avto, b.name as body, e.name as engine, g.name as gearbox
from Avto as a
         join Body as b on a.body_id = b.id
         join Gearbox as g on a.gearbox_id = g.id
         join Engine as e on a.engine_id = e.id;
-- Вывести отдельно детали (1 деталь - 1 запрос), которые не используются в машине, кузова, двигатели, коробки передач.
-- Body
select  b.name as body from Avto as a right join Body as b on a.body_id = b.id where a.name is null;
-- Gearbox
select g.name as Gearbox from Avto as a right join gearbox as g on g.id = a.gearbox_id where a.name is null;
-- Engine
select e.name as Engine from  Engine as e left join Avto as a on a.engine_id = e.id where a.name is null;
