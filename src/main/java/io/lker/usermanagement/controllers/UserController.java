package io.lker.usermanagement.controllers;

import io.lker.usermanagement.model.User;
import io.lker.usermanagement.services.springjpa.UserJPAService;
import io.lker.usermanagement.util.exceptions.UserNotFoundException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
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
    public Set<User> findAllUsers(){
        return userService.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/{userId}")
    public User getUserDetailsById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @DeleteMapping("/{userId}")
    void deleteSingleUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }

    @PutMapping("/{userId}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long userId){
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
