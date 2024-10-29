package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {


    //это запрос на jql = java query language
    // select * from timesheet t where t.projectId = :projectId order by t.created_at desc
    //@Query("select t from Timesheet t where t.projectId = :projectId order by t.createdAt desc")
    List<Timesheet> findByProjectId(Long projectId);


    //чтобы был запрос на SQL необходимо прописать nativeQuery
    @Query(nativeQuery = true, value = "select id from timesheet where project_id = :projectId")
    List<Long> fidnIdsByProjectId(Long projectId);

    @Query(nativeQuery = true, value = "update timesheet set activr = false where project_id = :projectId")
    @Modifying
    void deactiveTimesheetWithProjectId(Long projectId);
    //select * from timesheet where project_id = $1
    //List<Timesheet> findByProjectId(Long id);

    // select * from timesheet where project_id = $1 and minutes = $2
    List<Timesheet> findByProjectIdAndMinutes(Long projectId, Integer minutes);

//    select * from tinesheet whewe created_at > $1
//    List<Timesheet> findByCreatedAtBetween(LocalDate createdAt);
     //@Query(nativeQuery = true, value = "select * from timesheets where timesheet.employee_Id = :employeeId")
     //@Query("select t from Timesheet t where t.employeeId = :employeeId order by t.employeeId desc")
     List<Timesheet> findByEmployeeId(Long employeeId);
}
