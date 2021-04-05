-- таблица студентов
create table students(
                         id serial primary key,
                         name varchar(255)
);
-- таблица предметов
create table subjects(
                         id serial primary key,
                         name varchar(255)
);
-- связывающая таблица
create table students_subjects(
                                  id serial primary key,
                                  student_id int references students(id),
                                  subject_id int references subjects(id),
    -- оценка студента по предмету
                                  mark int
);