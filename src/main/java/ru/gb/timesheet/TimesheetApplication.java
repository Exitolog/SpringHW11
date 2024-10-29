package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.timesheet.model.*;
import ru.gb.timesheet.repository.*;

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

		UserRepository userRepository = ctx.getBean(UserRepository.class);
		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("$2a$12$LbAPCsHn8ZN5MUDqDmIX7e9n1YlDkCxEt0lW3Q2WuW0M1vteo8jvG"); // admin
		admin.addRole(new Role(RoleName.ADMIN));
		userRepository.save(admin);
		admin.addRole(new Role(RoleName.USER));
		admin = userRepository.save(admin);

		User user = new User();
		user.setLogin("user");
		user.setPassword("$2a$12$.dlnBAYq6sOUumn3jtG.AepxdSwGxJ8xA2iAPoCHSH61Vjl.JbIfq"); // user
		user.addRole(new Role(RoleName.USER));
		userRepository.save(user);
		User anonymous = new User();
		anonymous.setLogin("anon");
		anonymous.setPassword("$2a$12$tPkyYzWCYUEePUFXUh3scesGuPum1fvFYwm/9UpmWNa52xEeUToRu"); // anon
		anonymous.addRole(new Role(RoleName.REST));
		userRepository.save(anonymous);

//    // id user_id role_name
//    //  1       1     admin
//    //  2       1     user
//    //  3       2     user

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
