package com.example.blogapi.users;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public User getUserById(Long userId) {
        User user = new User(
            1L,
            "Alexandre",
            "aasdas@gmail.com",
            LocalDate.parse("1988-12-10")
        );

        return user;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();

        User user1 = new User(
            1L,
            "Alex",
            "a1234s@gmail.com",
            LocalDate.parse("1988-12-10")
        );
        User user2 = new User(
            1L,
            "Alexandre",
            "e12345r@gmail.com",
            LocalDate.parse("1988-12-10")
        );

        users.add(user1);
        users.add(user2);

        return users;
    }

    public User createUser(User user) {
        User createdUser = new User(
            user.getName(),
            user.getEmail(),
            user.getDateOfBirth()
        );

        return createdUser;
    }

    @Transactional
    public User updateUser(Long userId, User updatedUserData) {
        User user = new User(
            1L,
            "Alex",
            "a1234s@gmail.com",
            LocalDate.parse("1988-12-10")
        );

        System.out.println("New name" + updatedUserData.getName());

        if (user.isValidName(updatedUserData.getName())) {
            System.out.println("SETTING NEW NAME TO USER");
            user.setName(updatedUserData.getName());
        }

        if (user.isValidEmail(updatedUserData.getEmail())) {
            user.setEmail(updatedUserData.getEmail());
        }

        if (updatedUserData.getDateOfBirth() != null) {
            user.setDateOfBirth(updatedUserData.getDateOfBirth());
        }

        System.out.println(user);
        return user;
    }
}
