alter table student
add constraint age_constraint check (age >= 16);

alter table student
ALTER COLUMN  age SET DEFAULT '20';

ALTER TABLE student
ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE (name);

ALTER TABLE faculty
ADD CONSTRAINT name_color_unique UNIQUE (name,color);
SELECT student.name,student.age,faculty.name FROM student INNER JOIN faculty ON student.faculty_id = faculty.id;
SELECT student.* FROM student INNER JOIN avatar ON student.id = avatar.student_id;