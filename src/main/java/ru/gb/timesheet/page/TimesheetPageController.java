package ru.gb.timesheet.page;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.service.TimesheetPageService;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor //аннотация из lombok для
// автоматического конструктора из final полей
//@AllArgsConstructor - аннотация из lombok для автоматического констурктора всех полей

public class TimesheetPageController {


    private final TimesheetPageService service;

    //Вместо конструктора использовали аннотацию RequiredArgsConstructor
//    public TimesheetPageController(TimesheetService service) {
//        this.service = service;
//    }

    @GetMapping
    public String getAllTimesheets(Model model){
        List<TimesheetPageDTO> timesheets = service.findAll();
        model.addAttribute("timesheets",timesheets);
        return "timesheets-page.html";
    }

    @GetMapping("/project/{id}")
    public String getProjectPage(@PathVariable Long id, Model model){
        Optional<TimesheetPageDTO> projectPageDTO = service.findProject(id);
        if(projectPageDTO.isEmpty()){
            return "not-found.html";
        }
        model.addAttribute("timesheet", projectPageDTO.get());
        return "project-page.html";
    }

    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model){
   Optional<TimesheetPageDTO> timesheetPageDTO = service.findById(id);
   if(timesheetPageDTO.isEmpty()){
        return "not-found.html";
   }
   model.addAttribute("timesheet", timesheetPageDTO.get());
   return "timesheet-page.html";
    }
}
