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

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private LocalDate createdAt = LocalDate.now();

    public Long getId() {
        return postId;
    }

    public void setId(Long id) {
        this.postId = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
