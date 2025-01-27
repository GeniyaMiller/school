SELECT*FROM Student;
SELECT*FROM Faculty;
SELECT*FROM Student where age between 20 and 21;
SELECT name FROM Student;
SELECT*FROM Student WHERE name LIKE'%Д%' OR name LIKE'%д%';
SELECT*FROM Student WHERE age<21;
SELECT*FROM Student ORDER BY age;
select s.* from student as s,faculty as f where s.faculty_id = f.id;