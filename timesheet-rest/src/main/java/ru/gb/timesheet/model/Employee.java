package ru.gb.timesheet.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "employee")
@Schema(description = "Работник")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор сотрудника")
    private Long employeeId;

    @Schema(description = "Фамилия сотрудника")
    private String firstName;

    @Schema(description = "Имя сотрудника")
    private String lastName;
}
