-- вывести все продукты типа сыр
select p.name as product
from product as p
         join type as t on t.id = p.type_id
where t.name like 'сыр';
-- в названии присутстсвует часть
select name
from product
where name like '%ина%';

select t.name, count(*)
from type as t
         join product as p on p.type_id = t.id
group by t.name;
-- продукты пропадут в следующем месяце

SELECT name, expired_date
FROM product
WHERE expired_date >= DATE_TRUNC('month', CURRENT_DATE) + INTERVAL '1 MONTH'
  AND expired_date < DATE_TRUNC('month', CURRENT_DATE) + INTERVAL '2 MONTH';

--самый дорогой продукт
select name
from product
where price = (select max(price) from product);
--  количество всех продуктов определенного типа.
select t.name as name, count(*) as quantity
from type as t
         join product as p on t.id = p.type_id
group by t.name;
--  получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name
from product as p
         join type as t on t.id = p.type_id
where t.name like 'сыр'
   or t.name like 'молочные продукты';
-- тип продуктов, которых осталось меньше 3 штук.
select t.name
from type as t
         join product as p on p.type_id = t.id
group by t.name
having count(t.name) < 3;
-- Вывести все продукты и их тип.
select p.name, t.name
from product p
         join type t on t.id = p.type_id;
-- удаление дублирующих строк
delete
from product
where id not in (
    select min(id)
    from product
    group by name, expired_date, type_id, price);

select *
from product;