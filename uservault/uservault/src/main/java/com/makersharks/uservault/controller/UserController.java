package com.makersharks.uservault.controller;

import com.makersharks.uservault.model.User;
import com.makersharks.uservault.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/user/register")
    public String registerUser(@Valid @RequestBody User newUser){
        return userService.userSignup(newUser);
    }

    @GetMapping("/api/user/fetch")
    public User getUserByUserName(@RequestParam String userName){
        return userService.getUser(userName);
    }

    @PutMapping("/api/user/updatePassword")
    public String updatePassword(@RequestParam String email, @RequestParam String password){
        return userService.updatePassword(email, password);
    }

    @DeleteMapping("/api/user")
    public String deleteUser(@RequestParam String email){
        return userService.deleteUser(email);
    }
}
