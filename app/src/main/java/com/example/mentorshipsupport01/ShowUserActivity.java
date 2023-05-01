package com.example.mentorshipsupport01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mentorshipsupport01.databinding.ActivityMainBinding;

public class ShowUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

    }
}