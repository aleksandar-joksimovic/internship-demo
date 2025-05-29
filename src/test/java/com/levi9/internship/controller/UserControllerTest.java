package com.levi9.internship.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.internship.model.UserDTO;
import com.levi9.internship.service.OrderService;
import com.levi9.internship.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private UserService userService;
    private OrderService orderService;

    private ObjectMapper objectMapper;
    private UserDTO sampleUser;

    @BeforeEach
    void setUp() {

        userService = mock(UserService.class);
        UserController userController = new UserController(userService, orderService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        objectMapper = new ObjectMapper();
        sampleUser = new UserDTO();
        sampleUser.setUsername("john");
        sampleUser.setFirstName("John");
        sampleUser.setLastName("Doe");
        sampleUser.setEmail("john@example.com");
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.createUser(any(UserDTO.class))).thenReturn(sampleUser);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("john"));
    }

    @Test
    void testDeleteUserByUsername() throws Exception {
        when(userService.deleteUserByUsername("john")).thenReturn(true);

        mockMvc.perform(delete("/api/v1/users/delete/{username}", "john"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Deleted"))
                .andExpect(jsonPath("$.value").value(true));
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserByUserId("123")).thenReturn(sampleUser);

        mockMvc.perform(get("/api/v1/users/id/{userId}", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john"));
    }

    @Test
    void testGetUserByUsername() throws Exception {
        when(userService.getUserByUsername("john")).thenReturn(sampleUser);

        mockMvc.perform(get("/api/v1/users/username/{username}", "john"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testGetUsers() throws Exception {
        when(userService.getUsers()).thenReturn(List.of(sampleUser));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("john"));
    }
}
