package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(ProjectRepository projectRepository, TimesheetRepository timesheetRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.timesheetRepository = timesheetRepository;
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee){
        if(Objects.isNull(employee.getEmployeeId())){
            throw new IllegalArgumentException("Id must not be null");
        }
        return employeeRepository.save(employee);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    public Employee changeEmployeeById(Long id, Employee employee){
        Employee employeeBefore = employeeRepository.getById(id);
        employeeBefore.setFirstName(employee.getFirstName());
        employeeBefore.setLastName(employee.getLastName());
        return employeeBefore;
    }

    public List<Timesheet> getTimesheetsByEmployeeId(Long id){
        if(employeeRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Employee with id = " + id + " does not work on Timesheets");
        }
        return timesheetRepository.findByEmployeeId(id);
    }

}
