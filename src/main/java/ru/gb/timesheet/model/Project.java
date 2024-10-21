package ru.gb.timesheet.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "project")
@Schema(description = "Проект")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Schema(description = "Идентификатор проекта")
    private Long id;

    @Schema(description = "Название проекта")
    private String name;

}
