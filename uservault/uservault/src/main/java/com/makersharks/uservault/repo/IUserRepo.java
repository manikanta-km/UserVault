package com.makersharks.uservault.repo;

import com.makersharks.uservault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);
    User findFirstByUserName(String newUserName);
}
