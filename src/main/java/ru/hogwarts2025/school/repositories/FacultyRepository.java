package ru.hogwarts2025.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import ru.hogwarts2025.school.models.Faculty;
import ru.hogwarts2025.school.models.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    List<Faculty> findFacultyByColorIgnoreCase(String color);


}
