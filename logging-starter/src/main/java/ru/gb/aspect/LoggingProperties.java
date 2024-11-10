package ru.gb.aspect;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.logging")
public class LoggingProperties {

    private Level level = Level.DEBUG;
    private Boolean printArgs = false;


    //enum, int , string

    // List<...>
    //в ямле выглядит как:
    // properties:
    // -INFO
    // -DEBUG
    // -POST

    //Map<...., ....>
    //в ямле выглядит как:
    // mapping:
    // key1: value1
    // key2: value2

    // Any class

}
