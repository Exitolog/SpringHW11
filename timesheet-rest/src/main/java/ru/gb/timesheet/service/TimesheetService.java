package ru.gb.timesheet.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.aspect.Recover;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
//@Timer
public class TimesheetService {


    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }


    //@Timer
    @Recover
    public Optional<Timesheet> findById(Long id){
        //throw new RuntimeException("абракадабра");
        return timesheetRepository.findById(id);
    }

    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter){
        // FIXME: Вернуть фильтрацию
        return timesheetRepository.findAll();
    }

    public List<Timesheet> findAll(){
        return findAll(null, null);
    }

    public Timesheet create(Timesheet timesheet){
        if(Objects.isNull(timesheet.getProjectId())){
            throw new IllegalArgumentException("projectId must not be null");
        }
        if(projectRepository.findById(timesheet.getProjectId()).isEmpty()){
            throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exists");
        }
        timesheet.setCreatedAt(LocalDate.now());
        return timesheetRepository.save(timesheet);
    }

    //@Timer(enabled = false)
    public void  delete(Long id){
        timesheetRepository.deleteById(id);
    }

}
