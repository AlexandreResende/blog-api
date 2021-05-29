package com.example.blogapi.users;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Table
@Entity
public class UserEntity {
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
    private Long id;
    private String name;
    private String email;
    private boolean isDeleted = false;
    private LocalDate dateOfBirth;
    private LocalDate lastLogin;
    private LocalDate createdAt = LocalDate.now();
    @Transient
    private Integer age;

    public UserEntity(
        String name,
        String email,
        LocalDate dateOfBirth
    ) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public UserEntity(
        Long id,
        String name,
        String email,
        LocalDate dateOfBirth
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
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
}
