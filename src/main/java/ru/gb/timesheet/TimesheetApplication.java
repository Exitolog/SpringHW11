package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {


	/**
	 *
	 *
	 */

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);
		TimesheetRepository timesheetRepository = ctx.getBean(TimesheetRepository.class);
		ProjectRepository projectRepository = ctx.getBean(ProjectRepository.class);
		EmployeeRepository employeeRepository = ctx.getBean(EmployeeRepository.class);
		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setName("Project #" + i);
			projectRepository.save(project);
		}


		String[] firstNames = {"Elena", "Patric", "Sergey", "Sam", "Anna", "Miya"};
		String[] lastNames = {"Grayms","Simons","Kraygon", "Boiko","Adam","Pronto"};

		for(int i = 1; i <= 6; i++){
			Employee employee = new Employee();
			employee.setFirstName(firstNames[ThreadLocalRandom.current().nextInt(0,6)]);
			employee.setLastName(lastNames[ThreadLocalRandom.current().nextInt(0,6)]);
			employeeRepository.save(employee);
		}

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10 ; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepository.save(timesheet);
		}
	}

}
