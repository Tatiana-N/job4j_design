insert into students(name) values ('Аня');
insert into students(name) values ('Ваня');
insert into students(name) values ('Боря');

insert into subjects(name) values ('Математика');
insert into subjects(name) values ('Русский');
insert into subjects(name) values ('Информатика');

insert into students_subjects(student_id, subject_id, mark) values (1, 1, 5);
insert into students_subjects(student_id, subject_id, mark) values (1, 2, 5);
insert into students_subjects(student_id, subject_id, mark) values (1, 3, 5);

insert into students_subjects(student_id, subject_id, mark) values (2, 1, 5);
insert into students_subjects(student_id, subject_id, mark) values (2, 2, 4);
insert into students_subjects(student_id, subject_id, mark) values (2, 3, 4);

insert into students_subjects(student_id, subject_id, mark) values (3, 1, 3);
insert into students_subjects(student_id, subject_id, mark) values (3, 2, 5);
insert into students_subjects(student_id, subject_id, mark) values (3, 3, 3);