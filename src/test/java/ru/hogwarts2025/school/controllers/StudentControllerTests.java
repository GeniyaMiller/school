package ru.hogwarts2025.school.controllers;

import org.json.JSONObject;

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
    @InjectMocks
    private StudentController studentController;
    @MockitoBean(answers = Answers.RETURNS_MOCKS)
    private StudentRepository studentRepository;
    @MockitoBean(answers = Answers.CALLS_REAL_METHODS)
    private StudentService studentService;



    @Test
    public void testStudentController() throws Exception{
        long id = 1;
        String name = "test";
        int age = 20;
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name",name);
        json.put("age",age);


        long facultyId = 1;
        String facultyName = "test";
        String facultyColor = "color";
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);

        student.setFaculty(faculty);

        JSONObject jsonFaculty = new JSONObject();
        jsonFaculty.put("facultyId", facultyId);
        jsonFaculty.put("facultyName",facultyName);
        jsonFaculty.put("facultyColor",facultyColor);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(studentRepository.findByAge(anyInt())).thenReturn(List.of(student));
        when(studentRepository.findByAgeBetween(anyInt(),anyInt())).thenReturn(List.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/student")
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + null)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?age=" + age)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?age=" + null)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(json.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/filterByAge")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(facultyId))
                .andExpect(jsonPath("$.facultyName").value(facultyName))
                .andExpect(jsonPath("$.facultyColor").value(facultyColor));




    }

}
