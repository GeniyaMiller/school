package ru.hogwarts2025.school.services;


import org.springframework.stereotype.Service;
import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService{

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {

        return this.studentRepository.save(student);
    }

    public Student findById(long id) {

        return this.studentRepository.findById(id).get();
    }

    public Collection<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student updateStudent(Student student) {

        return this.studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        this.studentRepository.deleteById(id);
    }
    public Collection<Student> getStudentsByAge(int age) {
        return this.studentRepository.findByAge(age);
    }

    public Collection<Student> getStudentsBetweenAge(int min, int max) {
        return this.studentRepository.findByAgeBetween(min,max);
    }
}
