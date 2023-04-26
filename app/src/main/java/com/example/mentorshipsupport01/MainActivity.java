package com.example.mentorshipsupport01;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Views and Button
    private TextInputLayout firstNameEditText;
    private TextInputLayout lastNameEditText;
    private TextInputLayout emailEditText;

    private Button uploadButton;
    private Button downloadButton;

    //Fields
    private String firstName;
    private String lastName;
    private String email;

    private User user;

    //Firebase database and tools
    private DatabaseReference databaseReference;
    private StringBuilder sb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = new StringBuilder();
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        uploadButton = findViewById(R.id.upload);
        downloadButton = findViewById(R.id.download);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    executeUpload();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeDownload();
            }
        });

    }


    private void executeUpload() {
        //Get the values
        firstName = String.valueOf(firstNameEditText.getEditText().getText());
        lastName = String.valueOf(lastNameEditText.getEditText().getText());
        email = String.valueOf(emailEditText.getEditText().getText());

        user = new User(firstName, lastName, email);

        databaseReference.child("users").child(String.valueOf(user.getUserId())).setValue(user);

        //print messages
       // System.out.println("Executed successfully: "+user.toString());
        Toast.makeText(MainActivity.this, "Executed successfully", Toast.LENGTH_SHORT).show();

        //Clear the fields
        if(firstNameEditText != null){
            firstNameEditText.getEditText().getText().clear();
        }

        if(lastNameEditText != null){
            lastNameEditText.getEditText().getText().clear();
        }

        if(emailEditText != null){
            emailEditText.getEditText().getText().clear();
        }
    }

    private void executeDownload() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        //collectUserData((Map<String,Object>) dataSnapshot.getValue());
                        List<User> users = new ArrayList<>();
                        System.out.println("download button pressed");
                        Log.e("Count",""+dataSnapshot.getChildrenCount());
                        for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                            User user = postSnapshot.getValue(User.class);
                            users.add(user);
                        }

                        users.stream().forEach(user -> System.out.println(user.toString()));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //handle databaseError
                        Log.e("The read failed: ", error.getMessage());
                    }
                }
        );
    }

    private void collectUserData(Map<String, Object> users){
        List<User> userList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : users.entrySet()){
            Map singleUser = (Map) entry.getValue();
            userList.add((User) singleUser);
        }
    }

}