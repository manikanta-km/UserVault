package com.makersharks.uservault.service;

import com.makersharks.uservault.exception.UserNotFoundException;
import com.makersharks.uservault.model.User;
import com.makersharks.uservault.repo.IUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserSignup_Success() {
        User newUser = new User();
        newUser.setUserEmail("test@example.com");
        newUser.setUserPassword("password");

        when(userRepo.findFirstByUserEmail("test@example.com")).thenReturn(null);
        when(userRepo.save(any(User.class))).thenReturn(newUser);

        String result = userService.userSignup(newUser);

        assertThat(result).isEqualTo("User Registered");
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void testUserSignup_EmailAlreadyInUse() {
        User newUser = new User();
        newUser.setUserEmail("test@example.com");
        newUser.setUserPassword("password");

        when(userRepo.findFirstByUserEmail("test@example.com")).thenReturn(new User());

        String result = userService.userSignup(newUser);

        assertThat(result).isEqualTo("email already in use");
    }

    @Test
    void testGetUser_Success() {
        User user = new User();
        user.setUserName("testUser");

        when(userRepo.findFirstByUserName("testUser")).thenReturn(user);

        User result = userService.getUser("testUser");

        assertThat(result).isEqualTo(user);
    }

    @Test
    void testGetUser_UserNotFound() {
        when(userRepo.findFirstByUserName("testUser")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userService.getUser("testUser"));
    }

    @Test
    void testUpdatePassword_Success() {
        User user = new User();
        user.setUserEmail("test@example.com");
        user.setUserPassword("oldPassword");

        when(userRepo.findFirstByUserEmail("test@example.com")).thenReturn(user);

        String result = userService.updatePassword("test@example.com", "newPassword");

        assertThat(result).isEqualTo("Password updated successfully");
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testUpdatePassword_UserNotFound() {
        when(userRepo.findFirstByUserEmail("test@example.com")).thenReturn(null);

        String result = userService.updatePassword("test@example.com", "newPassword");

        assertThat(result).isEqualTo("Email is not registered");
    }

    @Test
    void testDeleteUser_Success() {
        User user = new User();
        user.setUserId(1);
        user.setUserEmail("test@example.com");

        when(userRepo.findFirstByUserEmail("test@example.com")).thenReturn(user);

        String result = userService.deleteUser("test@example.com");

        assertThat(result).isEqualTo("User deleted successfully");
        verify(userRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        when(userRepo.findFirstByUserEmail("test@example.com")).thenReturn(null);

        String result = userService.deleteUser("test@example.com");

        assertThat(result).isEqualTo("Email is not registered");
    }
}
