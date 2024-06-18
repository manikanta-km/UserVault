package com.makersharks.uservault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Auto-generated ID strategy
    private Integer userId;
    @Size(min = 4, max = 20, message = "User name must be 4-20 characters long")
    private String userName;
    @Email(message = "Please provide a valid email address")
    private String userEmail;
    @NotBlank(message = "Password cannot be blank")
    private String userPassword;
    @Size(min = 10,max = 10, message = "Phone number must be exactly 10 digits long")
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    private String contactNumber;
    @NotBlank(message = "Address cannot be blank")
    private String address;
}
