package ru.hogwarts2025.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts2025.school.models.Faculty;
import ru.hogwarts2025.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty saveFaculty(Faculty faculty) {
        return this.facultyRepository.save(faculty);
    }

    public Faculty findById(long id) {
        return facultyRepository.findById(id).get();
    }

    public Collection<Faculty> getAllFaculties() {
        return this.facultyRepository.findAll();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return this.facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        this.facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findFacultyByColor(color);
    }
}
