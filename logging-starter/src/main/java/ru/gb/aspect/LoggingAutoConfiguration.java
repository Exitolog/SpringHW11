package ru.gb.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(value = "application.logging.enabled", matchIfMissing = true)
public class LoggingAutoConfiguration {


    @Bean
    public LoggingAspect loggingAspect(LoggingProperties properties){
        return new LoggingAspect(properties);
    }

}
