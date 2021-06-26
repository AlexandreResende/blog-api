package com.example.blogapi.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, List<Post>>> getUserNonDeletedPosts(
        @PathVariable("userId") Long userId
    ) {
        /*
        * There is a security breach in this endpoint because anyone can get
        * other user's post from the endpoint. A better approach here would be to
        * get the userId from the token and then get all the non deleted posts
        * from that particular userId
        * */
//        List<Post> nonDeletedPosts = new ArrayList<>();
        HashMap<String, List<Post>> response = new HashMap<String, List<Post>>();
        List<Post> nonDeletedPosts = this.postService.getNonDeletedPosts(userId);

        response.put("posts", nonDeletedPosts);

        return ResponseEntity.ok(response);
    }
}
