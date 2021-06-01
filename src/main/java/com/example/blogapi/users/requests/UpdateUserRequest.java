package com.example.blogapi.users.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.time.LocalDate;

public class UpdateUserRequest {
    @Length(min = 1)
    public String name;
    @Email
    public String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public String dateOfBirth;

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

    public LocalDate getDateOfBirth() {
        if (this.dateOfBirth == null) {
            return null;
        }

        return LocalDate.parse(this.dateOfBirth);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", dateOfBirth='" + dateOfBirth + '\'' +
        '}';
    }
}
