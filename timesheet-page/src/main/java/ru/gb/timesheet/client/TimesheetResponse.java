package ru.gb.timesheet.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
public class TimesheetResponse {

        private Long id;
        private Long projectId;
        private Long employeeId;
        private Integer minutes;
        private LocalDate createdAt;

    }

