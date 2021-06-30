package com.example.blogapi.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{userId}/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Post>> getNonDeletedPostById(
        @PathVariable("userId") Long userId,
        @PathVariable("postId") Long postId
    ) {
        HashMap<String, Post> response = new HashMap<>();
        Post post = this.postService.getNonDeletedPost(userId, postId);

        response.put("post", post);

        return ResponseEntity.ok(response);
    }

    @PostMapping(
        value = "/posts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HashMap<String, Boolean>> createPost(
        @RequestBody Post post
    ) {
        /*
        * Getting the use id by the token
        * verify this id from user in the db
        * throw error if this does not exist
        * create post
        * */
        HashMap<String, Boolean> response = new HashMap();
        boolean postCreated = this.postService.createPost(post);

        response.put("created", true);

        return ResponseEntity.ok(response);
    }
}
