package io.lker.usermanagement.controllers;

import io.lker.usermanagement.model.User;
import io.lker.usermanagement.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Disallow field of ID to be altered
    // via forms/requests
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/findAll")
    public Set<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserDetailsById(@PathVariable("userId") Long userId){
        return userService.findById(userId);
    }

}
