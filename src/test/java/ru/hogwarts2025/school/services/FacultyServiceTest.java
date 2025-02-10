package ru.hogwarts2025.school.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts2025.school.models.Faculty;
import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.repositories.FacultyRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    @Mock
    private FacultyRepository mock;
    @InjectMocks
    private FacultyService out;
    private final Faculty expected = new Faculty(1, "Первый", "Белый");
    @Test
    public void shouldReturnFacultyWhenSaveFaculty(){
        when(mock.save(any())).thenReturn(expected);
        assertEquals(expected,out.saveFaculty(expected));
    }

    @Test
    public void shouldReturnFacultyWhenFindFacultyById(){
        when(mock.findById(anyLong())).thenReturn(Optional.of(expected));
        assertEquals(expected,out.findById(1));
    }
    @Test
    public void shouldReturnAllFaculties(){
        List<Faculty> expectedList = List.of(expected);
        when(mock.findAll()).thenReturn(expectedList);
        assertEquals(expectedList,out.getAllFaculties());
    }
    @Test
    public void shouldReturnFacultiesByColor(){
        List<Faculty> expectedList = List.of(expected);
        when(mock.findFacultyByColorIgnoreCase(anyString())).thenReturn(expectedList);
        assertEquals(expectedList,out.getFacultyByColor("белый"));
    }
    @Test
    public void shouldReturnStudentsWhoStudyOnFaculty(){
        Student st = new Student("Bdfyjd Bdfy", 23);
        List<Student> expectedList = List.of(st);
        when(mock.findById(anyLong())).thenReturn(Optional.of(expected));
        expected.setStudents(expectedList);
        List<Student> actual =  out.getStudents(1);
        assertEquals(expectedList, actual);
    }

}
