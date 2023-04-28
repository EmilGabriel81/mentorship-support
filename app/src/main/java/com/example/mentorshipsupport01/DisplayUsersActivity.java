package com.example.mentorshipsupport01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;

import com.example.mentorshipsupport01.recyclerview.UserAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayUsersActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_users);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

        initDownload();


        List<User> hardcodedUsers = new ArrayList<>(Arrays.asList(new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail"),
                new User("Ulloriaq","Vladimirs", "vlad@mail")));



//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        UserAdapter adapter = new UserAdapter(getApplicationContext(), hardcodedUsers);
//        adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(User user) {
//                Toast.makeText(DisplayUsersActivity.this, "Item was clicked: "+user.getFirstName()+" "+user.getLastName(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        recyclerView.setAdapter(adapter);
    }//onCreate

    private void initDownload() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<User> users = new ArrayList<>();

                Log.e("Count", "" + dataSnapshot.getChildrenCount());
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    User user = postSnapshot.getValue(User.class);
                    users.add(user);
                }

                Log.e("Button pressed: ", "=============================================");
                users.stream().forEach(user -> System.out.println(user.toString()));
                Log.e("Button pressed: ", "=============================================");

                RecyclerView recyclerView = findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                UserAdapter adapter = new UserAdapter(getApplicationContext(), users);
                adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(User user) {
                        Toast.makeText(DisplayUsersActivity.this, "Item was clicked: " + user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_SHORT).show();
                    }
                });

                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //handle databaseError
                Log.e("The read failed: ", error.getMessage());
            }
        });

    }
}