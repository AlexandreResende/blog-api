package com.example.blogapi.users;

import com.example.blogapi.users.requests.CreateUserRequest;
import com.example.blogapi.users.requests.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public User createUser(CreateUserRequest user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            throw new IllegalStateException("Email already being used");
        }

        User usr = new User(
            user.getName(),
            user.getEmail(),
            user.getDateOfBirth()
        );

        return userRepository.save(usr);
    }

    @Transactional
    public User updateUser(Long userId, UpdateUserRequest updatedUser) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException("Student not found"));

        if (
            updatedUser.getName() != null &&
            updatedUser.getName().length() > 0 &&
            user.isDifferentName(updatedUser.getName())
        ) {
            user.setName(updatedUser.getName());
        }

        if (
            updatedUser.getEmail() != null &&
            updatedUser.getEmail().length() > 0 &&
            user.isDifferentEmail(updatedUser.getEmail())
        ) {
            user.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getDateOfBirth() != null) {
            user.setDateOfBirth(updatedUser.getDateOfBirth());
        }

        return user;
    }
}
