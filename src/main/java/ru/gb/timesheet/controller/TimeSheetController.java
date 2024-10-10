package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
public class TimeSheetController {

private final TimesheetService service;

    public TimeSheetController(TimesheetService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id){
        Optional<Timesheet> timesheet = service.getById(id);

       if(timesheet.isPresent()) {
           return ResponseEntity.status(HttpStatus.OK).body(timesheet.get());
       }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<Timesheet>> getByProjectId(@PathVariable Long id){
        return ResponseEntity.ok(service.getTimesheetByProjectId(id));
    }
    //timesheets
    //timesheets?createdAtBefore=2024-07-09
    //timesheets?createdAtAfter=2024-07-15
    @GetMapping //получить все
    public ResponseEntity<List<Timesheet>> getAll(
        @RequestParam(required = false) LocalDate createdAtBerore,
        @RequestParam(required = false) LocalDate createdAtAfter
    ) {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping //создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet){
        timesheet = service.create(timesheet);
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e){
        return ResponseEntity.notFound().build();
    }
}
