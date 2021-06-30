package com.example.blogapi.posts.requests;

import javax.validation.constraints.NotNull;

public class CreatePostRequest {
    @NotNull
    private String post;

    @NotNull
    private String title;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
