package com.example.blogapi.posts;

import com.example.blogapi.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @SequenceGenerator(
    name = "post_sequence",
    sequenceName = "post_sequence",
    allocationSize = 1
    )
    @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "post_sequence"
    )
    private Long postId;

    @NotNull
    @Lob
    private String post;

    @NotNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private LocalDate createdAt = LocalDate.now();

    private boolean isDeleted = false;

    public Post() { super(); }

    public Post(String post, String title, User user) {
        this.post = post;
        this.title = title;
        this.user = user;
    }

    public Post(String post, String title) {
        this.post = post;
        this.title = title;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
