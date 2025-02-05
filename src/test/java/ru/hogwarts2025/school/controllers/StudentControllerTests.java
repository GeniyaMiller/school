package ru.hogwarts2025.school.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts2025.school.models.Faculty;
import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.repositories.StudentRepository;
import ru.hogwarts2025.school.services.StudentService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean(answers = Answers.RETURNS_MOCKS)
    private StudentRepository studentRepository;
    @MockitoBean(answers = Answers.RETURNS_MOCKS)
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;
    final long id = 1;
    final String name = "test";
    final int age = 20;
    Student student = new Student();
    JSONObject json = new JSONObject();
    long facultyId = 1;
    String facultyName = "test";
    String facultyColor = "color";
    Faculty faculty = new Faculty();
    JSONObject jsonFaculty = new JSONObject();

    @BeforeEach
    public void setup() throws JSONException {
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        json.put("id", id);
        json.put("name", name);
        json.put("age", age);

        faculty.setId(facultyId);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);
        student.setFaculty(faculty);
        jsonFaculty.put("id", facultyId);
        jsonFaculty.put("name", facultyName);
        jsonFaculty.put("color", facultyColor);

        when(studentService.saveStudent(any(Student.class))).thenReturn(student);
        when(studentService.findById(anyLong())).thenReturn(student);
        when(studentService.getAllStudents()).thenReturn(List.of(student));
        when(studentService.updateStudent(any(Student.class))).thenReturn(student);
        when(studentService.getStudentsByAge(anyInt())).thenReturn(List.of(student));
        when(studentService.getStudentsBetweenAge(anyInt(), anyInt())).thenReturn(List.of(student));
        when(studentService.getFaculty(anyLong())).thenReturn(faculty);

    }


    @Test
    public void createStudentTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }
    @Test
    public void findStudentByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
    @Test
    public void notFindStudentsByIdTest() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + null)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void getAllStudentsByAgeTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
                    .get("/student?age=" + age)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
}
    @Test
    public void getAllStudentsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void updateStudentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
    @Test
    public void deleteStudentByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void getStudentBetweenAgeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/filterByAge?min="+ age + "&max=" + age)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void getFacultyByStudentByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty?id=" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(facultyId))
                .andExpect(jsonPath("$.name").value(facultyName))
                .andExpect(jsonPath("$.color").value(facultyColor));
    }



}
