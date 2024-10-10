package ru.gb.timesheet.model;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

/**
 * Описание структуры JSON-ответа на REST-запросы.
 * т.е. запросы, ответ на которые - JSON
 */
@Data
public class Timesheet {

    private Long id;
    private Long projectId;
    private int minutes;
    private LocalDate createdAt;

}
