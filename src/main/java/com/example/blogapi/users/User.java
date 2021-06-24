package com.example.blogapi.users;

import com.example.blogapi.posts.Post;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    private Long userId;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    private boolean isDeleted = false;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private LocalDate lastLogin;

    private LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @Transient
    private Integer age;

    public User () {
        super();
    }

    public User(
        String name,
        String email,
        LocalDate dateOfBirth
    ) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public User(
        Long id,
        String name,
        String email,
        LocalDate dateOfBirth
    ) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + userId +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", isDeleted=" + isDeleted +
        ", dateOfBirth=" + dateOfBirth +
        ", lastLogin=" + lastLogin +
        ", createdAt=" + createdAt +
        ", age=" + age +
        '}';
    }

    public boolean isDifferentName(String newName) {
        return !Objects.equals(this.name, newName);
    }

    public boolean isDifferentEmail(String newEmail) {
        return !Objects.equals(this.email, newEmail);
    }
}
