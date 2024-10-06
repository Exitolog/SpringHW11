package ru.gb.timesheet.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TimesheetRepository {

    private final List<Timesheet> timesheets = new ArrayList<>();
    private static Long sequence = 1L;

    public Optional<Timesheet> getById(Long id){
        return timesheets.stream()
                .filter(ti -> Objects.equals(ti.getId(),id))
                .findFirst();

    }

    public List<Timesheet> getAll(){
        return List.copyOf(timesheets);
    }

    public Timesheet create(Timesheet timesheet){
        timesheet.setId(sequence++);
        timesheet.setCreatedAt(LocalDate.now());
        timesheets.add(timesheet);
        return timesheet;
    }

    public void  delete(Long id){
        timesheets.stream().
                filter(ti -> Objects.equals(ti.getId(), id))
                .findFirst().
                ifPresent(timesheets::remove);
    }

    public List<Timesheet> getTimesheetByProjectId(Long id){
       return timesheets.stream().filter(timesheet -> Objects.equals(timesheet.getProjectId(), id)).toList();
    }
}
