package com.example.blogapi.users;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserEntity getUserById(Long userId) {
        UserEntity user = new UserEntity(
            1L,
            "Alexandre",
            "aasdas@gmail.com",
            LocalDate.parse("1988-12-10")
        );

        return user;
    }

    public List<UserEntity> getUsers() {
        List<UserEntity> users = new ArrayList<UserEntity>();

        UserEntity user1 = new UserEntity(
            1L,
            "Alex",
            "a1234s@gmail.com",
            LocalDate.parse("1988-12-10")
        );
        UserEntity user2 = new UserEntity(
            1L,
            "Alexandre",
            "e12345r@gmail.com",
            LocalDate.parse("1988-12-10")
        );

        users.add(user1);
        users.add(user2);

        return users;
    }

    public UserEntity createUser(UserEntity user) {
        UserEntity createdUser = new UserEntity(
            user.getName(),
            user.getEmail(),
            user.getDateOfBirth()
        );

        return createdUser;
    }
}
