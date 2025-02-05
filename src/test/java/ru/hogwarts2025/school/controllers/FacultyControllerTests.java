package ru.hogwarts2025.school.controllers;


import org.json.JSONObject;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts2025.school.models.Faculty;
import ru.hogwarts2025.school.models.Student;
import ru.hogwarts2025.school.repositories.FacultyRepository;
import ru.hogwarts2025.school.services.FacultyService;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private FacultyRepository facultyRepository;

    @MockitoSpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void facultyControllerTests() throws Exception {
        long id = 1;
        String name = "test";
        String color = "color";
        long studentId = 1;
        String studentName = "qwerty qwerty";
        int studentAge = 20;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        Student student = new Student();
        student.setId(studentId);
        student.setName(studentName);
        student.setAge(studentAge);
        faculty.setStudents(List.of(student));

        JSONObject facJson = new JSONObject();
        facJson.put("name", name);
        facJson.put("color", color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));
        when(facultyRepository.findAll()).thenReturn(List.of(faculty));
        when(facultyRepository.findFacultyByColorIgnoreCase(anyString())).thenReturn(List.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect((jsonPath("$.name").value(name)))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?color=" + color)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect((jsonPath("$.name").value(name)))
                .andExpect(jsonPath("$.color").value(color));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/get/students?id=" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(studentId))
                .andExpect(jsonPath("$.studentName").value(studentName))
                .andExpect(jsonPath("$.studentAge").value(studentAge));
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/faculty/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
