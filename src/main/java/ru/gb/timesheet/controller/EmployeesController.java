package ru.gb.timesheet.controller;


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
public class EmployeesController {

    private final EmployeeService employeeService;


    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        Optional<Employee> employee = employeeService.findById(id);
        if(employee.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(employee.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheetsByEmployeeId(@PathVariable("id") Long employeeId){
        try {
            return ResponseEntity.ok((employeeService.getTimesheetsByEmployeeId(employeeId)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        employee = employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
