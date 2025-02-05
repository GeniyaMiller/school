package ru.hogwarts2025.school.services;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ru.hogwarts2025.school.models.Faculty;
import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService{

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student findById(long id) {

        return studentRepository.findById(id).get();
    }

    public Collection<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student updateStudent(Student student) {

        return this.studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
    public Collection<Student> getStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getStudentsBetweenAge(int min, int max) {
        return studentRepository.findByAgeBetween(min,max);
    }

    public Faculty getFaculty(long id) {
        return findById(id).getFaculty();
    }
}
