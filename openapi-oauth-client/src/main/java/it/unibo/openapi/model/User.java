package it.unibo.openapi.model;

import io.quarkus.runtime.util.HashUtil;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

public class User {

    public String name;
    public String surname;
    public String username;
    public String email;
    public String password;
    public LocalDateTime registrationDate;

    public User() {
    }
    public User(String name, String surname, String username, String email, String password, LocalDateTime registrationDate) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = Base64.getEncoder().encodeToString(HashUtil.sha1(password.getBytes()).getBytes());
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
