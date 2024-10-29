package ru.gb.timesheet.page;


import lombok.Data;

/**
 * класс DTO - data transfer Object, который описывает параметры для шаблонов HTML-страниц
 * т.е. он нужен для передачи параметров внутоб thymeleaf в тех котроллерах, которые сразу отдают HTML-страницы
 */

@Data
public class TimesheetPageDTO {

        private String projectName;
        private String id;
        private String minutes;
        private String createdAt;
        private String projectId;
}
