package it.unibo.openapi.model;

import java.util.Base64;
import java.util.Date;

public class User {

    public String name;
    public String surname;
    public String username;
    public String email;
    public String password;
    public Date registrationDate;

    public User(String name, String surname, String username, String email, String password, Date registrationDate) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = Base64.getEncoder().encodeToString(password.getBytes());
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
