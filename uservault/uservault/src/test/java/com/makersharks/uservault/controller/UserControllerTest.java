package com.makersharks.uservault.controller;

import com.makersharks.uservault.model.User;
import com.makersharks.uservault.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    void testRegisterUser() throws Exception {
        User newUser = new User(null, "user2", "newuser@example.com", "password", "1234567890", "address");
        when(userService.userSignup(any(User.class))).thenReturn("User Registered");

        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userName\": \"user2\", \"userEmail\": \"newuser@example.com\", \"userPassword\": \"password\", \"contactNumber\": \"1234567890\", \"address\": \"address\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("User Registered"));
    }
    @Test
    void testGetUserByUserName() throws Exception {
        User user = new User();
        user.setUserName("testUser");

        when(userService.getUser("testUser")).thenReturn(user);

        mockMvc.perform(get("/api/user/fetch")
                        .param("userName", "testUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("testUser"));
    }

    @Test
    void testUpdatePassword() throws Exception {
        when(userService.updatePassword("test@example.com", "newPassword")).thenReturn("Password updated successfully");

        mockMvc.perform(put("/api/user/updatePassword")
                        .param("email", "test@example.com")
                        .param("password", "newPassword"))
                .andExpect(status().isOk())
                .andExpect(content().string("Password updated successfully"));
    }

    @Test
    void testDeleteUser() throws Exception {
        when(userService.deleteUser("test@example.com")).thenReturn("User deleted successfully");

        mockMvc.perform(delete("/api/user")
                        .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));
    }
}
