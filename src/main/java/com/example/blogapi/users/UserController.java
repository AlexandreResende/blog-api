package com.example.blogapi.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(
        @PathVariable("userId") Long userId
    ) {
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<HashMap<String, List<User>>> getUsers() {
       List<User> users = userService.getUsers();
       HashMap<String, List<User>> response = new HashMap<>();

       response.put("users", users);

       return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }

    @PutMapping(
        value = "/{userId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> updateUser(
        @PathVariable("userId") Long userId,
        @RequestBody User user
    ) {
        // criar uma classe de payload para o update user
        User updatedUser = userService.updateUser(userId, user);

        return ResponseEntity.ok(updatedUser);
    }
}
