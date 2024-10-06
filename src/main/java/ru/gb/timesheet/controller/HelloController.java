package ru.gb.timesheet.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam String username){
        return "<h1>Hello, " + username + " !</h1>";
    }

    @GetMapping("/home/{username}")
    public String homePage(@PathVariable String username){
        return "<h1>Hello, " + username + " !</h1>";
    }
}
