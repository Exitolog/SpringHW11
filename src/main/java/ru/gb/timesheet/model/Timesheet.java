package ru.gb.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

/**
 * Описание структуры JSON-ответа на REST-запросы.
 * т.е. запросы, ответ на которые - JSON
 */
@Data
@Entity
@Table(name = "timesheet")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private Long projectId;
    private Long employeeId;
    private Integer minutes;
    private LocalDate createdAt;

}
