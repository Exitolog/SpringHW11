package ru.gb.timesheet.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;


    @Test
    void findByIdEmpty() {
        //given
        assertTrue(projectRepository.existsById(2L));

        assertTrue(projectService.findById(2L).isEmpty());
    }

    @Test
    void findByIdPresent() {

        //giver - дано
        Project project = new Project();
        //project.setId(150L);
        project.setName("projectName");
        project = projectRepository.save(project);

        // when - что сделал
        Optional<Project> actual = projectService.findById(project.getId());

        //then - проверка
        assertTrue(actual.isPresent());
        assertEquals(actual.get().getId(), project.getId());
        assertEquals(actual.get().getName(), project.getName());
    }

}