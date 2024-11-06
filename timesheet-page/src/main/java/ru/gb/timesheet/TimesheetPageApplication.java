package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class TimesheetPageApplication {


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetPageApplication.class, args);


	}
}
