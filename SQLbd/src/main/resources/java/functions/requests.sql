select avg(mark) from students_subjects;
select min(mark) from students_subjects;
select max(mark) from students_subjects;
select s.name, avg(ss.mark) from students_subjects as ss join subjects s on ss.subject_id = s.id
group by s.name;
select s.name, avg(ss.mark) from students as s join students_subjects as ss on ss.subject_id = s.id
group by s.name
having avg(ss.mark)>4.5 ;