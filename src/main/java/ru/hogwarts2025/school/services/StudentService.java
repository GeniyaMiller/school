package ru.hogwarts2025.school.services;


import com.sun.tools.javac.Main;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts2025.school.models.Faculty;
import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.repositories.StudentRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Spliterators.iterator;

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

    public Collection<Student> getAllStudents(Integer pageNumber,Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1,pageSize);
        return this.studentRepository.findAll(pageRequest).getContent();
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


    public Collection<String> getStudentsSortedByName(String string) {
        Collection<Student> allStudents = studentRepository.findAll();
        List<String> sortedName = allStudents.stream()
                .map(Student::getName)
                .sorted()
                .filter(e -> e.startsWith(string))
                .map(e->e.toUpperCase())
                .collect(Collectors.toList());
        return sortedName;
    }

    public String getAverageAge() {
        Collection<Student> allStudents = studentRepository.findAll();
        Double averageAge = allStudents.stream()
                .mapToInt(Student::getAge)
                .average().getAsDouble();
        DecimalFormat fmt = new DecimalFormat("#.##");
        return fmt.format(averageAge);
    }

    public void getStudentsForParallelPrint() {
        List<Student> allStudents = studentRepository.findAll();

        print(allStudents.get(0));
        print(allStudents.get(1));
        new Thread(()->{
            print(allStudents.get(2));
            print(allStudents.get(3));
        }).start();
        new Thread (()->{
            print(allStudents.get(4));
            print(allStudents.get(5));
        }).start();
        print(allStudents.get(6));
        print(allStudents.get(7));
        print(allStudents.get(8));
    }
    private void  print(Student s){
        System.out.println(s);
    }
}
