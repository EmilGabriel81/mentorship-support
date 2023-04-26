package com.example.mentorshipsupport01;

public class User {
    private String firstName;
    private String lastName;
    private String email;

    private static int count=0;
    private int userId =0;

    public User(){

    }

    public User(String firstName, String lastName, String email) {
        userId = ++count;
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

    public int getUserId() {
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
