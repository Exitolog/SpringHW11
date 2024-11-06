package ru.gb.timesheet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.EmployeeService;
import ru.gb.timesheet.service.ProjectService;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
@Tag(name = "Расписания", description = "API для работы с таймшитами")
public class TimeSheetController {

private final TimesheetService timesheetService;
private final EmployeeService employeeService;
private final ProjectService projectService;

    public TimeSheetController(TimesheetService timesheetService, EmployeeService employeeService, ProjectService projectService) {
        this.timesheetService = timesheetService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @Operation(summary = "Get Timesheet by Id", description = "Получить таймшит по идентификатору")
    @GetMapping("{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id){
        Optional<Timesheet> timesheet = timesheetService.findById(id);

       if(timesheet.isPresent()) {
           return ResponseEntity.status(HttpStatus.OK).body(timesheet.get());
       }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Gel all Timesheets", description = "Получить список всех таймшитов")
    @GetMapping //получить все
    public ResponseEntity<List<Timesheet>> getAll(
        @RequestParam(required = false) LocalDate createdAtBerore,
        @RequestParam(required = false) LocalDate createdAtAfter
    ) {
        return ResponseEntity.ok(timesheetService.findAll());
    }

    @Operation(summary = "Create new Timesheet", description = "Добавить новый таймшит")
    @PostMapping //создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet){
        timesheet = timesheetService.create(timesheet);
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @Operation(summary = "Delete timesheet by id", description = "Удалить таймшит по идентификатору")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        timesheetService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
