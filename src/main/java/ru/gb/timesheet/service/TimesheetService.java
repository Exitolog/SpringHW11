package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class TimesheetService {


    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> getById(Long id){
        return timesheetRepository.getById(id);
    }

    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter){
        return timesheetRepository.findAll();
    }

    public List<Timesheet> findAll(){
        return findAll(null, null);
    }

    public Timesheet create(Timesheet timesheet){
        if(Objects.isNull(timesheet.getProjectId())) {
            throw new NoSuchElementException();
        }
        //не очень понимаю, как можно обработать в этом случае
        //????
        else {
            System.out.println("ProjectId с номером " + timesheet.getProjectId() + " не существует");
            return null;
        }
    }

    public void  delete(Long id){
        timesheetRepository.delete(id);
    }

    public List<Timesheet> getTimesheetByProjectId(Long id){
        if(projectRepository.findById(id)) return timesheetRepository.getTimesheetByProjectId(id);

        //?????
        else {
            System.out.println("Project с id " + id + "не существует");
            return null;
        }
    }

}
