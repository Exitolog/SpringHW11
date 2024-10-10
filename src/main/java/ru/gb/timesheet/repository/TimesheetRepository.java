package ru.gb.timesheet.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.TimesheetApplication;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
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
    //перегрузка метода с аргументами (можно не использовать)
    public List<Timesheet> findAll() {
       return findAll(null, null);
    }

    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter){
        Predicate<Timesheet> filter = it -> true;

        if(Objects.nonNull(createdAtBefore)){
            filter = filter.and(it -> it.getCreatedAt().isBefore(createdAtBefore));
        }
        if(Objects.nonNull(createdAtAfter)) {
            filter = filter.and(it -> it.getCreatedAt().isAfter(createdAtAfter));
        }

        return timesheets.stream()
                .filter(filter)
                .toList();
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
