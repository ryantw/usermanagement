package io.lker.usermanagement.services.springjpa;

import io.lker.usermanagement.model.User;
import io.lker.usermanagement.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        returnUser = User.builder().id(1L).firstName("Test").lastName("User")
                .emailAddress("test@user.com")
                .build();
    }

    @Test
    void findByLastName() {
        when(userRepository.findByLastName(any())).thenReturn(returnUser);
        assertEquals("User", returnUser.getLastName());
        userJPAService.findByLastName("User");
        verify(userRepository).findByLastName(any());
    }

    @Test
    void findByFirstName() {
        when(userRepository.findByFirstName(any())).thenReturn(returnUser);
        assertEquals("Test", returnUser.getFirstName());
        userJPAService.findByFirstName("Test");
        verify(userRepository).findByFirstName(any());
    }

    @Test
    void findAllByLastName() {
    }

    @Test
    void findAllByFirstName() {
    }

    @Test
    void findAll() {
        Set<User> users = new HashSet<>();
        users.add(User.builder().id(1L).build());
        users.add(User.builder().id(2L).build());
        when(userRepository.findAll()).thenReturn(users);

        Set<User> returnedUsers = userJPAService.findAll();

        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
        //verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById() {
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
    void delete() {
    }

    @Test
    void deleteById() {
    }
}