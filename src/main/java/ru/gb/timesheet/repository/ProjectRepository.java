package ru.gb.timesheet.repository;


import org.springframework.stereotype.Component;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProjectRepository {

    private final List<Project> projects = new ArrayList<>();
    private static Long id = 1L;

    public Optional<Project> getById(Long id){
        return projects.stream()
                .filter(pr -> Objects.equals(pr.getId(),id))
                .findFirst();

    }

    public List<Project> getAll(){
        return List.copyOf(projects);
    }

    public Project create(Project project){
        project.setId(id++);
        projects.add(project);
        return project;
    }

    public void  delete(Long id){
        projects.stream().
                filter(pr -> Objects.equals(pr.getId(), id))
                .findFirst().
                ifPresent(projects::remove);
    }

    public boolean findById(Long id){
       return projects.stream().anyMatch(project -> Objects.equals(project.getId(), id));
    }

}
