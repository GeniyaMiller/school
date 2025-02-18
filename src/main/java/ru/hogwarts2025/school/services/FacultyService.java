package ru.hogwarts2025.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ru.hogwarts2025.school.models.Faculty;
import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty saveFaculty(Faculty faculty) {
        logger.info("Saving faculty: {}", faculty.getId());
        return this.facultyRepository.save(faculty);
    }

    public Faculty findById(long id) {
        logger.info("Finding faculty by id: {}",  id);
        return facultyRepository.findById(id).get();
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Getting all faculties");
        return this.facultyRepository.findAll();
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Updating faculty: {}", faculty.getId());
        return this.facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Deleting faculty by id: {}", id);
        this.facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getFacultyByColor(String color) {
        logger.info("Getting faculties by color");
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    public List<Student> getStudents(long id) {
        logger.info("Getting students by faculty id: {}", id);
        return findById(id).getStudents();
    }

    public String getLongName() {
        Collection<Faculty> allFaculty = facultyRepository.findAll();
        String longName = allFaculty.stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse(null).toString();
        return longName;
    }
}
