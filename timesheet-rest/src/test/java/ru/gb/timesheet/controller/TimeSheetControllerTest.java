package ru.gb.timesheet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cglib.core.Local;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeSheetControllerTest {

    @LocalServerPort
    private int port;
    private RestClient restClient;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    ProjectRepository projectRepository;





    @BeforeEach
    void beforeEach(){
        restClient = RestClient.create("http://localhost:" + port);
    }


    @Test
    void testGetByID() {

        LocalDate localDate = LocalDate.now();

        //создание нового Timesheet, который хотим получить
        Timesheet expected = new Timesheet();
        expected.setEmployeeId(1L);
        expected.setProjectId(1L);
        expected.setCreatedAt(localDate);
        expected.setMinutes(10);
        timesheetRepository.save(expected);

        //cоздаем запрос, ожидая 200 код, что созданный нами таймшит найден
        ResponseEntity<Timesheet> actual = restClient.get()
                .uri("/timesheets/" + expected.getId())
                .retrieve()
                .toEntity(Timesheet.class);

        //тест на то, что запрос выполнен корректно
        assertEquals(HttpStatus.OK, actual.getStatusCode());

        //вытягивание таймшита из запроса
        Timesheet responseBody = actual.getBody();

        //проверки на то, что вытянули именно тот таймшит, который мы создали до этого
        assertEquals(expected.getProjectId(), responseBody.getProjectId());
        assertEquals(expected.getEmployeeId(), responseBody.getEmployeeId());
        assertEquals(expected.getMinutes(), responseBody.getMinutes());
        assertEquals(expected.getCreatedAt(), responseBody.getCreatedAt());

    }

    @Test
    void testGetAll() {
        // создаем 2 таймшита
        LocalDate localDate = LocalDate.now();

        Timesheet ts1 = new Timesheet();
        ts1.setEmployeeId(1L);
        ts1.setProjectId(1L);
        ts1.setCreatedAt(localDate);
        ts1.setMinutes(10);
        timesheetRepository.save(ts1);

        Timesheet ts2 = new Timesheet();
        ts2.setEmployeeId(2L);
        ts2.setProjectId(2L);
        ts2.setCreatedAt(localDate);
        ts2.setMinutes(20);
        timesheetRepository.save(ts2);

        //cоздаем запрос, ожидая 200 код, что созданные нами таймшиты найдены
        ResponseEntity<List<Timesheet>> response = restClient.get()
                .uri("/timesheets")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Timesheet>>() {
                });

        //проверка на корректность запроса
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Timesheet> responseBody = response.getBody();

        //проверка на количество таймшитов в листе
        assertEquals(responseBody.size(), 2);

    }

    @Test
    void testCreate() {
        LocalDate localDate = LocalDate.now();

        //cоздаем проект, чтобы его прикрепить к таймшиту,
        //иначе приходит ошибка 404 по несуществующему проекту
        //условия прописаны в timesheetService 54-56 cтрочки
        Project project = new Project();
        project.setName("newProject");
        projectRepository.save(project);


        //создаем таймшит
        Timesheet toCreate = new Timesheet();
        toCreate.setEmployeeId(1L);
        toCreate.setProjectId(1L);
        toCreate.setCreatedAt(localDate);
        toCreate.setMinutes(10);

        //создаем запрос
        ResponseEntity<Timesheet> response = restClient.post()
                .uri("/timesheets")
                .body(toCreate)
                .retrieve()
                .toEntity(Timesheet.class);


        //проверяем статус-код
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //Вытягиваем таймшит из запроса
        Timesheet responseBody = response.getBody();

        //проверяем на null
        assertNotNull(responseBody);

        //проверки на корректные создания таймшита(с нашими данными)
        assertEquals(toCreate.getProjectId(), responseBody.getProjectId());
        assertEquals(toCreate.getEmployeeId(), responseBody.getEmployeeId());
        assertEquals(toCreate.getMinutes(), responseBody.getMinutes());
        assertEquals(toCreate.getCreatedAt(), responseBody.getCreatedAt());

        //проверка на сохранение в бд нашего таймшита
        assertTrue(timesheetRepository.existsById(responseBody.getId()));
    }

    @Test
    void testDelete() {

        LocalDate localDate = LocalDate.now();

        //создаем таймшит
        Timesheet toDelete = new Timesheet();
        toDelete.setEmployeeId(1L);
        toDelete.setProjectId(1L);
        toDelete.setCreatedAt(localDate);
        toDelete.setMinutes(10);
        timesheetRepository.save(toDelete);

        //создаем запрос
        ResponseEntity<Void> response = restClient.delete()
                .uri("/timesheets/" + toDelete.getId())
                .retrieve()
                .toBodilessEntity();


        //проверяем статус код
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        //проверяем удаление таймшита из бд
        assertFalse(timesheetRepository.existsById(toDelete.getId()));
    }
}