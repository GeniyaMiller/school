package ru.hogwarts2025.school.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.services.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.saveStudent(student);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable long id){
        Student student = this.studentService.findById(id);
        if(student == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents(@RequestParam(required = false) int age){
        if(age != 0){
            return ResponseEntity.ok(studentService.getStudentsByAge(age));
        }
        return ResponseEntity.ok(this.studentService.getAllStudents());
    }
    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student foundStudent = this.studentService.updateStudent(student);
        if(foundStudent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable long id){
        this.studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }


}
