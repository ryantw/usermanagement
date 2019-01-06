package io.lker.usermanagement.bootstrap;

import io.lker.usermanagement.model.User;
import io.lker.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartUpData implements CommandLineRunner {

    private final UserService userService;

    public StartUpData(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData(){
        User user1 = User.builder().id(1L).firstName("Ryan").lastName("Walker")
                .emailAddress("r@r.com").build();
        User user2 = User.builder().id(2L).firstName("Alicia").lastName("Walker")
                .emailAddress("a@a.com").build();

        userService.save(user1);
        userService.save(user2);
    }
}
