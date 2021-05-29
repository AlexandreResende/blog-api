package com.example.blogapi.users;

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

    public User createUser(User user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            throw new IllegalStateException("Email already being used");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long userId, User user) {
        User user1 = new User(
            1L,
            "Alex",
            "a1234s@gmail.com",
            LocalDate.parse("1988-12-10")
        );

        if (user.getName() != null && user.getName().length() > 0 && !Objects.equals(user1.getName(), user.getName())) {
            System.out.println("SETTING NEW USER NAME\n\n");
            user1.setName(user.getName());
        }

        if (user.getEmail() != null && user.getEmail().length() > 0 && !Objects.equals(user1.getEmail(), user.getEmail())) {
            user1.setEmail(user.getEmail());
        }

        if (user.getDateOfBirth() != null) {
            user1.setDateOfBirth(user.getDateOfBirth());
        }

        return user1;
    }
}
