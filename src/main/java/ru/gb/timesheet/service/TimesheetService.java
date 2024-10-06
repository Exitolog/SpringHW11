package ru.gb.timesheet.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TimesheetService {


    private final TimesheetRepository repository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> getById(Long id){
        return repository.getById(id);

    }

    public List<Timesheet> getAll(){
        return repository.getAll();
    }

    public Timesheet create(Timesheet timesheet){
        if(projectRepository.findById(timesheet.getProjectId())) return repository.create(timesheet);

        //не очень понимаю, как можно обработать в этом случае
        //????
        else {
            System.out.println("ProjectId с номером " + timesheet.getProjectId() + " не существует");
            return null;
        }
    }

    public void  delete(Long id){
        repository.delete(id);
    }

    public List<Timesheet> getTimesheetByProjectId(Long id){
        if(projectRepository.findById(id)) return repository.getTimesheetByProjectId(id);

        //?????
        else {
            System.out.println("Project с id " + id + "не существует");
            return null;
        }
    }

}
