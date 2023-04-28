package com.example.mentorshipsupport01;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.UUID;

public class User implements Parcelable {
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

    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        userId = in.readLong();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeLong(userId);
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
