package com.example.blogapi.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> getUser(
        @PathVariable("userId") Long userId
    ) {
        UserEntity user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<HashMap<String, List<UserEntity>>> getUsers() {
       List<UserEntity> users = userService.getUsers();
       HashMap<String, List<UserEntity>> response = new HashMap<>();

       response.put("users", users);

       return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> createUser(
        @RequestBody UserEntity user
    ) {
        UserEntity createdUser = userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }
}
