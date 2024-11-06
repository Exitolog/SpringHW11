package ru.gb.timesheet.service;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import ru.gb.timesheet.client.ProjectResponse;
import ru.gb.timesheet.client.TimesheetResponse;
import ru.gb.timesheet.page.TimesheetPageDTO;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service

public class TimesheetPageService {


    private final DiscoveryClient discoveryClient;


    public TimesheetPageService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private RestClient restClient(){
        List<ServiceInstance> instances = discoveryClient.getInstances("TIMESHEET-REST");
        int instancesCount = instances.size();
        int instanceIndex  = ThreadLocalRandom.current().nextInt(0, instancesCount);
        ServiceInstance instance = instances.get(instanceIndex);
        String uri = "http://" + instance.getHost() + ":" + instance.getPort();
        System.out.println("URI = " + uri);
        return RestClient.create(uri);
    }
    //    public TimesheetPageService() {
//        this.restClient = RestClient.create("http://localhost:8080");
//        //this.restClient = RestClient.create("http://timesheets-rest:8080");
//    }

    public List<TimesheetPageDTO> findAll(){
        List<TimesheetResponse> timesheets = null;
        int attempts = 5;
        while (attempts-- > 0){
            try {
                timesheets = restClient().get()
                .uri("/timesheets")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<List<TimesheetResponse>>() {
                });
                break;
            } catch (HttpServerErrorException e) {
                //ignore
            }
            if(timesheets == null) {
                throw new RuntimeException("oops");
            }

        }


            //рабочий вариант с семинара
//        List<TimesheetResponse> timesheets = restClient().get()
//                .uri("/timesheets")
//                .retrieve()
//                .body(new org.springframework.core.ParameterizedTypeReference<List<TimesheetResponse>>() {
//                });

        List<TimesheetPageDTO> result = new ArrayList<>();
        for(TimesheetResponse timesheet : timesheets) {
            TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
            timesheetPageDTO.setId(String.valueOf(timesheet.getId()));
            timesheetPageDTO.setMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDTO.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));

            ProjectResponse projectResponse = restClient().get()
                    .uri("/projects/" + timesheet.getProjectId())
                    .retrieve().body(ProjectResponse.class);
            timesheetPageDTO.setProjectName(projectResponse.getName());
            result.add(timesheetPageDTO);
        }

        return result;
    }


    public Optional<TimesheetPageDTO> findById(Long id){
        try {
            TimesheetResponse timesheet = restClient().get()
                    .uri("/timesheets/" + id)
                    .retrieve()
                    .body(TimesheetResponse.class);

            TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
            timesheetPageDTO.setId(String.valueOf(timesheet.getId()));
            timesheetPageDTO.setMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDTO.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));

            ProjectResponse projectResponse = restClient().get()
                    .uri("/projects/" + timesheet.getProjectId())
                    .retrieve().body(ProjectResponse.class);
            timesheetPageDTO.setProjectName(projectResponse.getName());
            return Optional.of(timesheetPageDTO);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }

    }


//    public Optional<TimesheetPageDTO> findProject(Long id) {
//        Project project = projectService.findById(id).orElseThrow();
//        TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
//        timesheetPageDTO.setProjectName(project.getName());
//        timesheetPageDTO.setProjectId(String.valueOf(project.getId()));
//
//        return Optional.of(timesheetPageDTO);
//    }

//    public Optional<ProjectPageDTO> findProjectById(Long id){
//        return projectService.getById(id).map(this::convertProject);
//    }
//
//    private ProjectPageDTO convertProject(Project project){
//        ProjectPageDTO projectPageDTO = new ProjectPageDTO();
//        projectPageDTO.setName(project.getName());
//        projectPageDTO.setId(String.valueOf(project.getId()));
//        return projectPageDTO;
//    }


//    private TimesheetPageDTO convert(Timesheet timesheet){
//        Project project = projectService.findById(timesheet.getProjectId())
//                .orElseThrow();
//
//        TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
//        timesheetPageDTO.setProjectName(project.getName());
//        timesheetPageDTO.setId(String.valueOf(timesheet.getId()));
//        timesheetPageDTO.setMinutes(String.valueOf(timesheet.getMinutes()));
//        timesheetPageDTO.setCreatedAt(timesheet.getCreatedAt().toString());
//        timesheetPageDTO.setProjectId(String.valueOf(timesheet.getProjectId()));
//        return timesheetPageDTO;
//    }


//    public List<TimesheetPageDTO> findAll(){
//        return null;
//    }

}

