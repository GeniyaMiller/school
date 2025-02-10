package ru.hogwarts2025.school.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts2025.school.models.Student;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StudentControllerSBTest {
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;


    @Test
    public void contextLoads() {
        assertThat(studentController).isNotNull();
    }

    @Test
    void testGetEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/student", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testPostEndpoint() throws Exception {
        Student student = new Student("qwerty",12);
        Student entity = restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);
        assertEquals("qwerty", entity.getName());
        assertEquals(12, entity.getAge());
        assertNotNull(entity.getId());
        restTemplate.delete("http://localhost:" + port + "/student/" + entity.getId());


    }

}
