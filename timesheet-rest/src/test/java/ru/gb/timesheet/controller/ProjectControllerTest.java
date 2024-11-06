package ru.gb.timesheet.controller;

import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.repository.ProjectRepository;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectControllerTest {

    @LocalServerPort
    private int port;
    private RestClient restClient;

    @Autowired
    ProjectRepository projectRepository;

    @BeforeEach
    void beforeEach(){
        restClient = RestClient.create("http://localhost:" + port);
    }


    @Test
    void getByIdNotFound(){
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () ->{
            restClient.get()
                .uri("/projects/-2")
                .retrieve()
                .toBodilessEntity();
        });
    }


    @Test
    void getTimesheetsByProjectIdOK() {
        // save (project)
        // GET /projects/1L => 200 OK

        Project expected = new Project();
        expected.setName("projectName");
        expected = projectRepository.save(expected);


        ResponseEntity<Project> actual = restClient.get()
                .uri("/projects/" + expected.getId())
                .retrieve().toEntity(Project.class);

        // assert 200 OK
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Project responseBody = actual.getBody();
        assertEquals(responseBody.getId() ,expected.getId());
        assertEquals( responseBody.getName() ,expected.getName());
    }

    @Test
    void testCreate(){
        Project toCreate = new Project();
        toCreate.setName("NewName");

        ResponseEntity<Project> response = restClient.post()
                .uri("/projects")
                .body(toCreate)
                .retrieve()
                .toEntity(Project.class);

        //Проверяем HTTP-ручку сервера
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Project responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody.getName(), toCreate.getName());

        // Проверяем, что запись в бд есть
        assertTrue(projectRepository.existsById(responseBody.getId()));

    }


    @Test
    void testDeleteById(){

        Project toDelete = new Project();
        toDelete.setName("NewName");
        projectRepository.save(toDelete);

        ResponseEntity<Void> response = restClient.delete()
                .uri("/projects/" + toDelete.getId())
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        //Проверяем запись в бд отсутствует
        assertFalse(projectRepository.existsById(toDelete.getId()));

    }

}