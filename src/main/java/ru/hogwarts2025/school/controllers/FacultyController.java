package ru.hogwarts2025.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts2025.school.models.Faculty;

import ru.hogwarts2025.school.services.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return this.facultyService.saveFaculty(faculty);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Faculty> findStudentById(@PathVariable long id){
        Faculty faculty = this.facultyService.findById(id);
        if(faculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculties(@RequestParam(required = false) String color){
        if(color != null) {
            return ResponseEntity.ok(facultyService.getFacultyByColor(color));
        }
        return ResponseEntity.ok(this.facultyService.getAllFaculties());
    }
    @PutMapping()
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
        Faculty foundFaculty = this.facultyService.updateFaculty(faculty);
        if(foundFaculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id){
        this.facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

}
