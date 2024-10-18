package ru.gb.timesheet.controller;

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
public class TimeSheetController {

private final TimesheetService timesheetService;
private final EmployeeService employeeService;
private final ProjectService projectService;

    public TimeSheetController(TimesheetService timesheetService, EmployeeService employeeService, ProjectService projectService) {
        this.timesheetService = timesheetService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id){
        Optional<Timesheet> timesheet = timesheetService.findById(id);

       if(timesheet.isPresent()) {
           return ResponseEntity.status(HttpStatus.OK).body(timesheet.get());
       }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping("/project/{id}")
//    public ResponseEntity<List<Timesheet>> getByProjectId(@PathVariable Long id){
//        return ResponseEntity.ok(timesheetService.getTimesheetByProjectId(id));
//    }
    @GetMapping //получить все
    public ResponseEntity<List<Timesheet>> getAll(
        @RequestParam(required = false) LocalDate createdAtBerore,
        @RequestParam(required = false) LocalDate createdAtAfter
    ) {
        return ResponseEntity.ok(timesheetService.findAll());
    }

    @PostMapping //создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet){
        timesheet = timesheetService.create(timesheet);
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        timesheetService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/employees/{id}/timesheets")
//    public List<Timesheet> getTimesheetsByEmployeeId(@PathVariable Long employeeId){
//        return timesheetService.getTimesheetByEmployeeId(employeeId);
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e){
        return ResponseEntity.notFound().build();
    }
}
