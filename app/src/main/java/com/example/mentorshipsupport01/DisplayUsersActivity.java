package com.example.mentorshipsupport01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mentorshipsupport01.recyclerview.UserAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_users);

        List<User> users = new ArrayList<>(Arrays.asList(new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail")));


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserAdapter(getApplicationContext(), users));
    }
}