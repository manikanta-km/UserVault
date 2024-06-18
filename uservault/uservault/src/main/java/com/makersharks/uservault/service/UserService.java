package com.makersharks.uservault.service;

import com.makersharks.uservault.exception.UserNotFoundException;
import com.makersharks.uservault.model.User;
import com.makersharks.uservault.repo.IUserRepo;
import com.makersharks.uservault.service.hashingutility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    public String userSignup(User newUser){
        String newEmail = newUser.getUserEmail();
        User existingUser = userRepo.findFirstByUserEmail(newEmail);
        if (existingUser != null) {
            return "email already in use";
        }
        // passwords are encrypted before we store it in the table
        String rawPassword = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(rawPassword);
            newUser.setUserPassword(encryptedPassword);
            userRepo.save(newUser);
            return "User Registered";
        }
        catch (Exception e) {
            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public User getUser(String userName){
        User existingUser = userRepo.findFirstByUserName(userName);
        if(existingUser != null){
            return existingUser;
        }
        else{
            throw new UserNotFoundException("User not found with user name : " + userName);
        }
    }

    public String updatePassword(String userEmail, String newPassword) {
        User user = userRepo.findFirstByUserEmail(userEmail);
        if(user != null){
            String encryptedPassword = PasswordEncryptor.encrypt(newPassword);
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            return "Password updated successfully";
        }
        return "Email is not registered";
    }

    public String deleteUser(String userEmail){
        User user = userRepo.findFirstByUserEmail(userEmail);
        if(user != null){
            Integer id = user.getUserId();
            userRepo.deleteById(id);
            return "User deleted successfully";
        }
        return "Email is not registered";
    }
}
