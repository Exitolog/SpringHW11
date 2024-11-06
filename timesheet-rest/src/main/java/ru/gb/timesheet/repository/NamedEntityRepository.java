package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.yaml.snakeyaml.events.Event;

import java.awt.*;
import java.util.List;


@NoRepositoryBean
public interface NamedEntityRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findByName(String name);


}
