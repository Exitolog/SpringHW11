package ru.gb.timesheet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.ExceptionResponse;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Tag(name = "Проекты", description = "API для работы с проектами")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @Operation(summary = "Get all timesheets by projectId", description = "Получить все таймшиты по айди проекта")
    @GetMapping("{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheetsByProjectId(@PathVariable Long id){
        return ResponseEntity.ok(service.getTimesheets(id));
    }


    @Operation(summary = "Get Project", description = "Получить проект по его идентификатору")
    @API.NoFoundResponse
    @API.ThatsOk
    @API.ServerIsNotWork
    @GetMapping("{id}")
    public ResponseEntity<Project> get(@PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        Project project = service.findById(id).orElseThrow(() ->new NoSuchElementException("Проект не найден"));
        return ResponseEntity.ok(project);
    }

    @Operation(summary = "Get all Projects", description = "Получить все проекты")
    @GetMapping //получить все
    public ResponseEntity<List<Project>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Add Project", description = "Добавить новый проект")
    @PostMapping //создание нового ресурса
    public ResponseEntity<Project> create(@RequestBody Project project){
        project = service.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @Operation(summary = "Delete Project", description = "Удалить проект с идентификатором")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
