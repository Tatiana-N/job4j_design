insert into meetings (name)
values ('Конференция № 1');
insert into meetings (name)
values ('Конференция № 2');
insert into meetings (name)
values ('Конференция № 3');
insert into meetings (name)
values ('Конференция № 4');
insert into meetings (name)
values ('Конференция № 5');
insert into meetings (name)
values ('Конференция № 6');
insert into meetings (name)
values ('Конференция № 7');

insert into users (name)
values ('Екатерина Иванова');
insert into users (name)
values ('Иван Абрамович');
insert into users (name)
values ('Татьяна Александрова');
insert into users (name)
values ('Иван Грозный');
insert into users (name)
values ('Олег Вещий');
insert into users (name)
values ('Ярослав Мудрый');
insert into users (name)
values ('Владимир Святой');
insert into users (name)
values ('Рюрик Романов');

insert into participation_status (name)
values ('Неопределён');
insert into participation_status (name)
values ('Отказ');
insert into participation_status (name)
values ('Согласие');

insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (1, 1, 2);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (1, 2, 1);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (1, 4, 2);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (1, 5, 3);

insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (2, 5, 3);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (2, 4, 1);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (2, 3, 2);

insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (3, 2, 3);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (3, 4, 2);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (3, 5, 2);

insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (4, 1, 1);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (4, 2, 2);

insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (5, 3, 3);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (5, 2, 2);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (5, 5, 1);


insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (6, 2, 2);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (7, 2, 1);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (7, 5, 3);
insert into user_meeting (user_id, meeting_id, participation_status_id)
VALUES (8, 3, 3);
-- всего заявок во всех встречах
select nt.name, case when count(nt.name) is null then 0 else count(nt.status) end
from (select m.name as name, um.participation_status_id as status
      from meetings m
               left join user_meeting um on m.id = um.meeting_id) as nt
group by nt.name
order by nt.name;

-- заявок с согласиями во всех всчтречах
select meetings.name as name,
       case
           when sum(newTable.countAgreed) is null then count(newTable.countAgreed)
           else sum(newTable.countAgreed) end as agreed
from meetings
         left join (
    select m.name, count(ps.id) as countAgreed
    from meetings m
             left join user_meeting um on m.id = um.meeting_id
             left join participation_status ps on ps.id = um.participation_status_id
    where ps.id = 3
       or ps.id is null
    group by m.name
)
    as newTable on meetings.name = newTable.name
group by meetings.name, newTable.countAgreed
order by meetings.name;
-- Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
select nt.name as meeting_name,
       case when count(nt.status) is null then 0 else count(nt.status) end as number_of_applications,
       sum(nt.agreed) as number_of_agreed
from (select m.name as name,
             um.participation_status_id as status,
             case when um.participation_status_id = 3 then 1 else 0 end as agreed
      from meetings m
               left join user_meeting um on m.id = um.meeting_id) as nt
group by nt.name
order by nt.name;

SELECT m.name, count(um.participation_status_id) from meetings m LEFT JOIN user_meeting um on m.id = um.meeting_id
    WHERE um.participation_status_id = 3

GROUP BY m.name;


-- Нужно получить все совещания, где не было ни одной заявки на посещения
select m.name
from meetings m
         left join user_meeting um on m.id = um.meeting_id
where um is null