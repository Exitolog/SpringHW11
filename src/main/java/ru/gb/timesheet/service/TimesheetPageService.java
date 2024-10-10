package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.TimesheetPageDTO;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    public List<TimesheetPageDTO> findAll(){
        return timesheetService.findAll().stream().map(this::convert)
                .toList();
    }


    public Optional<TimesheetPageDTO> findById(Long id){
        Optional<Timesheet> timesheetOptional = timesheetService.getById(id);
        if(timesheetOptional.isEmpty())return Optional.empty();
        Timesheet timesheet = timesheetOptional.get();
        Project project = projectService.getById(timesheet.getProjectId()).orElseThrow();

        TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
        timesheetPageDTO.setProjectName(project.getName());
        timesheetPageDTO.setId(String.valueOf(timesheet.getId()));
        timesheetPageDTO.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDTO.setCreatedAt(timesheet.getCreatedAt().toString());

        return Optional.of(timesheetPageDTO);
    }


    public Optional<TimesheetPageDTO> findProject(Long id) {
        Project project = projectService.getById(id).orElseThrow();
        TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
        timesheetPageDTO.setProjectName(project.getName());
        timesheetPageDTO.setProjectId(String.valueOf(project.getId()));

        return Optional.of(timesheetPageDTO);
    }

//    public Optional<ProjectPageDTO> findProjectById(Long id){
//        return projectService.getById(id).map(this::convertProject);
//    }
//
//    private ProjectPageDTO convertProject(Project project){
//        ProjectPageDTO projectPageDTO = new ProjectPageDTO();
//        projectPageDTO.setName(project.getName());
//        projectPageDTO.setId(String.valueOf(project.getId()));
//        return projectPageDTO;
//    }


    private TimesheetPageDTO convert(Timesheet timesheet){
        Project project = projectService.getById(timesheet.getProjectId())
                .orElseThrow();

        TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
        timesheetPageDTO.setProjectName(project.getName());
        timesheetPageDTO.setId(String.valueOf(timesheet.getId()));
        timesheetPageDTO.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDTO.setCreatedAt(timesheet.getCreatedAt().toString());
        timesheetPageDTO.setProjectId(String.valueOf(timesheet.getProjectId()));
        return timesheetPageDTO;
    }


//    public List<TimesheetPageDTO> findAll(){
//        return null;
//    }

}

