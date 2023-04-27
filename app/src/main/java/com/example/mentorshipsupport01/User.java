package com.example.mentorshipsupport01;

import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String email;

    private long userId;

    public User(){

    }

    public User(String firstName, String lastName, String email) {
        this.userId = UUID.randomUUID().getMostSignificantBits();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
