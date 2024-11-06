package ru.gb.timesheet.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Идентификатор расписания")
    private Long id;

    @Schema(description = "Идентификатор проекта")
    private Long projectId;

    @Schema(description = "Идентификатор сотрудника")
    private Long employeeId;

    @Schema(description = "Количество минут, потраченных на проект сотрудником")
    private Integer minutes;

    @Schema(description = "Дата создания расписания")
    private LocalDate createdAt;

}
