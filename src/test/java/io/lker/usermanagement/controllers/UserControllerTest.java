package io.lker.usermanagement.controllers;

import com.google.gson.reflect.TypeToken;
import io.lker.usermanagement.model.User;
import io.lker.usermanagement.services.UserService;
import io.lker.usermanagement.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    Set<User> users;

    @BeforeEach
    void setUp() {
        users = new HashSet<>();
        users.add(User.builder().id(1L).build());
        users.add(User.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    void findAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(users);
        MvcResult result = mockMvc.perform(get("/users/findAll")).andReturn();
        List<User> returnedUsers = TestUtils.jsonToList(result.getResponse().getContentAsString(), new TypeToken<ArrayList<User>>(){});
        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
    }

    @Test
    void getUserDetailsById() throws Exception {
        User user = User.builder().id(1L).firstName("Test").lastName("User").build();
        when(userService.findById(anyLong())).thenReturn(user);
        MvcResult result = mockMvc.perform(get("/users/1")).andReturn();
        User jsonUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
        assertNotNull(jsonUser);
        assertEquals(1L, jsonUser.getId().longValue());
        assertEquals("Test", jsonUser.getFirstName());

    }
}