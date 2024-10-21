package ru.gb.timesheet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.EmployeeService;
import ru.gb.timesheet.service.TimesheetService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
@Tag(name = "Работники", description = "API для работы с сотрудниками")
public class EmployeesController {

    private final EmployeeService employeeService;


    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @Operation(summary = "Get all Employee", description = "Получить список всех сотрудников")
    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }


    @Operation(summary = "Get Employee by id", description = "Получить сотрудника по идентификатору")
    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        Optional<Employee> employee = employeeService.findById(id);
        if(employee.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(employee.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all timesheets by EmployeeId", description = "Получить список таймшитов по идентификатору сотрудника")
    @GetMapping("{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheetsByEmployeeId(@PathVariable("id") Long employeeId){
        try {
            return ResponseEntity.ok((employeeService.getTimesheetsByEmployeeId(employeeId)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create new Employee", description = "Добавить нового сотрудника")
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        employee = employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @Operation(summary = "Delete Employee by Id", description = "Удалить сотрудника по идентификатору")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
