package ru.hogwarts2025.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.repositories.StudentRepository;
import ru.hogwarts2025.school.services.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepositoryMock;
    @InjectMocks
    private StudentService out;
    private final Student expected = new Student(1,"Иванов Иван", 20);

    @Test
    public void shouldReturnStudentWhenSaveStudent(){
        when(studentRepositoryMock.save(any())).thenReturn(expected);
        assertEquals(expected,out.saveStudent(expected));
        verify(studentRepositoryMock,times(1)).save(any());
    }
    @Test
    public void shouldReturnStudentWhenFindStudentByID(){
        when(studentRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expected));
        assertEquals(expected,out.findById(1));
        verify(studentRepositoryMock,times(1)).findById(anyLong());
    }
    @Test
    public void shouldReturnStudentsWhenFindAllStudents(){
        List<Student> expectedStudents = List.of(expected);
        when(studentRepositoryMock.findAll()).thenReturn((expectedStudents));
        assertEquals(expectedStudents,out.getAllStudents());
    }
    @Test
    public void shouldReturnStudentsWhenFindStudentsByAge(){
        List<Student> expectedStudents = List.of(expected);
        when(studentRepositoryMock.findByAge(anyInt())).thenReturn(expectedStudents);
        assertEquals(expectedStudents,out.getStudentsByAge(20));
    }



}
