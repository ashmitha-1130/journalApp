package com.journal.journalApp.Controller;

import com.journal.journalApp.Services.UserService;
import com.journal.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @Autowired
    private UserService userService;


    @PostMapping("/create-user")
    public void addUser(@RequestBody User user){
        userService.saveNewUser(user);

    }
}
