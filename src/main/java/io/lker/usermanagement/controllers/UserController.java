package io.lker.usermanagement.controllers;

import io.lker.usermanagement.model.User;
import io.lker.usermanagement.services.springjpa.UserJPAService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/admin/users")
@RestController
public class UserController {

    private final UserJPAService userService;

    public UserController(UserJPAService userService) {
        this.userService = userService;
    }

    // Disallow field of ID to be altered
    // via forms/requests
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public Set<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserDetailsById(@PathVariable("userId") Long userId){
        return userService.findById(userId);
    }

    @GetMapping("/find/{lastName}")
    public Set<User> findByLastNameLike(@PathVariable("lastName") String lastName){
        Set<User> returnSet = new HashSet<>();
        if(lastName == null)
            return null;

        List<User> results = userService.findAllByLastNameLike("%" + lastName + "%");

        if(results.isEmpty()){
            //result.rejectValue("lastName", "notFound", "not found");
            return null;
        } else if (results.size() == 1){
            returnSet.add(results.iterator().next());
            return returnSet;
        } else {
            results.stream().forEach(returnSet::add);
            return returnSet;
        }
    }

}
