package com.example.blogapi.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    List<Post> getNonDeletedPosts(Long userId) {
        List<Post> nonDeletedPosts = this.postRepository.getNonDeletedPosts(userId);

        return nonDeletedPosts;
    }

    Post getNonDeletedPost(Long userId, Long postId) {
        Post post = this.postRepository.getNonDeletedPostByUserIdAndPostId(userId, postId)
            .orElseThrow(() -> new IllegalStateException("Post not found"));

        return post;
    }
}
