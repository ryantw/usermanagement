package io.lker.webstore.services;

import io.lker.usermanagement.model.user.User;
import io.lker.usermanagement.repositories.UserRepository;
import io.lker.usermanagement.services.springjpa.UserJPAService;
import io.lker.usermanagement.util.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserJPAServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserJPAService userJPAService;

    User returnUser;

    @BeforeEach
    void setUp() {
        returnUser = User.builder().id(1L).firstName("Test").lastName("user")
                .emailAddress("test@user.com")
                .build();
    }

    @Test
    void findAllByLastNameLike() {
        // Returns 3 obv, need to find a way to stream out Smith
        // Given
        Set<User> users = new HashSet<>();
        users.add(User.builder().id(1L).firstName("Ronald").lastName("McDonald").build());
        users.add(User.builder().id(2L).firstName("Ruddy").lastName("McDonald").build());
        users.add(User.builder().id(3L).firstName("Joe").lastName("Smith").build());
        //users.stream().forEach(user -> userRepository.save(user));
        //assertEquals(3, userRepository.count());
        //when
        //when(userRepository.findAllByLastNameLike("walker").thenReturn(users);
        //Set<user> returnedUsers = userJPAService.findAllByLastNameLike("Smith");
        //then
        //assertEquals(1, returnedUsers.size());
    }


    @Test
    void findAll() {
        Set<User> users = new HashSet<>();
        users.add(User.builder().id(1L).firstName("Rye").build());
        users.add(User.builder().id(2L).firstName("Bread").build());
        when(userRepository.findAll()).thenReturn(users);

        Set<User> returnedUsers = userJPAService.findAll();

        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
        //verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Optional<User> optU = Optional.of(returnUser);
        when(userRepository.findById(anyLong())).thenReturn(optU);
        User returnedUser = userJPAService.findById(1L);
        assertNotNull(returnedUser);
        verify(userRepository, times(1)).findById(anyLong());


    }

    @Test
    void save() {
        when(userRepository.save(any())).thenReturn(returnUser);
        User userSaved = userJPAService.save(returnUser);
        assertNotNull(userSaved);
        verify(userRepository).save(any());
        verify(userRepository,times(1)).save(any());
    }

    @Test
    void getRecipeByIdNotFound(){
        assertThrows(UserNotFoundException.class,
                ()->{
                    User notFound = User.builder().id(8L).build();
                    userJPAService.findById(notFound.getId());
                });
    }

    @Test
    void deleteById() {
        Long idToDelete = Long.valueOf(1L);
        userJPAService.deleteById(idToDelete);
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}