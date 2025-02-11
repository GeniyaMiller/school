-- liquibase formatted sql

-- changeset emiller:1
CREATE INDEX student_index ON student (name);

-- changeset emiller:2
CREATE INDEX faculty_color_name_index ON faculty (name,color);

