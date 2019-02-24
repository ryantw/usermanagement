package io.lker.webstore.controllers;

import io.lker.webstore.common.model.user.User;
import io.lker.webstore.usermanagement.services.springjpa.UserJPAService;
import io.lker.webstore.usermanagement.util.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/api/user")
@RestController
@Slf4j
public class UserController {

    private final UserJPAService userService;

    public UserController(UserJPAService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Set<User> findAllUsers(){
        return userService.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user){
        log.info("SAVING NEW USER");
        return userService.save(user);
    }

    @GetMapping("/{userId}")
    public User getUserDetailsById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @DeleteMapping("/{userId}")
    void deleteSingleUser(@PathVariable Long userId){
        log.info("Deleting USER_ID: " + userId);
        userService.deleteById(userId);
    }

    @PutMapping("/{userId}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long userId){
        log.info("Replacing/New user: " + userId);
        try {
            User user = userService.findById(userId);
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmailAddress(newUser.getEmailAddress());
            return userService.save(user);
        } catch (UserNotFoundException e){
            return userService.save(newUser);
        }
    }

    @GetMapping("/search/{lastName}")
    public Set<User> findByLastNameLike(@PathVariable String lastName){
        if(lastName == null)
            return null;

        Set<User> results = userService.findAllByLastNameLike(lastName);

        if(results.isEmpty()){
            //result.rejectValue("lastName", "notFound", "not found");
            return null;
        } else if (results.size() == 1){
            return results;
        } else {
            return results;
        }
    }

}
