package it.unibo.openapi.model;

import io.quarkus.runtime.util.HashUtil;

import java.time.LocalDateTime;
import java.util.Base64;

public class UserInfo {

    public String name;
    public String surname;
    public String username;
    public String email;
    public LocalDateTime registrationDate;

    public UserInfo() {
    }
    public UserInfo(String name, String surname, String username, String email, LocalDateTime registrationDate) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public UserInfo(User user) {
        this.name = user.name;
        this.surname = user.surname;
        this.username = user.username;
        this.email = user.email;
        this.registrationDate = user.registrationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
