package io.lker.usermanagement.controllers;

import com.google.gson.reflect.TypeToken;
import io.lker.usermanagement.model.User;
import io.lker.usermanagement.services.UserService;
import io.lker.usermanagement.services.springjpa.UserJPAService;
import io.lker.usermanagement.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.acl.Owner;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserJPAService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    Set<User> users;

    @BeforeEach
    void setUp() {
        users = new HashSet<>();
        users.add(User.builder().id(1L).firstName("Reb").build());
        users.add(User.builder().id(2L).firstName("Blah").build());

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    void findAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(users);
        MvcResult result = mockMvc.perform(get("/admin/users")).andReturn();
        List<User> returnedUsers = TestUtils.jsonToList(result.getResponse().getContentAsString(), new TypeToken<ArrayList<User>>(){});
        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
    }

    @Test
    void getUserDetailsById() throws Exception {
        User user = User.builder().id(1L).firstName("Test").lastName("User").build();
        when(userService.findById(anyLong())).thenReturn(user);
        MvcResult result = mockMvc.perform(get("/admin/users/1")).andReturn();
        User jsonUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
        assertNotNull(jsonUser);
        assertEquals(1L, jsonUser.getId().longValue());
        assertEquals("Test", jsonUser.getFirstName());
    }

    @Test
    void findByLastNameLike() throws Exception {
        User users = User.builder().id(10L).lastName("Walker").build();
        Set<User> userSet = new HashSet<>();
        userSet.add(users);
        when(userService.findAllByLastNameLike(anyString())).thenReturn(userSet);
        mockMvc.perform(get("/admin/users/find/Walker"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastName", is(users.getLastName())));

        //Set<User> returnedUsers = userService.findAllByLastNameLike("Walker");
        //assertEquals(2, returnedUsers.size());
    }

    @Test
    void addNewUser() throws Exception {
        // Given
        User user = User.builder().id(15L).build();
        String jsonUser = TestUtils.objectToJson(user);
        //when
        mockMvc.perform(post("/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$", hasSize(1)))
                //.andExpect(jsonPath("$[0].id", is(15L)));

        //then

    }
}