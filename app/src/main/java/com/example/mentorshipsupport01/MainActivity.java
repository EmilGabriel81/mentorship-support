package com.example.mentorshipsupport01;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
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

    //Firebase database and tools
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);

        Button uploadButton = findViewById(R.id.upload);
        Button downloadButton = findViewById(R.id.download);
        Button secondActivity = findViewById(R.id.secondactivity);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

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

        secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1. to send the database reference
                Intent intent = new Intent(getApplicationContext(), DisplayUsersActivity.class);
                startActivity(intent);
            }
        });

    }


    private void executeUpload() {
        String firstName = String.valueOf(firstNameEditText.getEditText().getText());
        String lastName = String.valueOf(lastNameEditText.getEditText().getText());
        String email = String.valueOf(emailEditText.getEditText().getText());

        if (firstName.equals("") || lastName.equals("") || email.equals("")) {
            Toast.makeText(MainActivity.this, "Fields not valid", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(firstName, lastName, email);

        databaseReference.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "Executed successfully", Toast.LENGTH_SHORT).show();

            }
        });

        //Clear the fields
        if (firstNameEditText != null) {
            firstNameEditText.getEditText().getText().clear();
        }

        if (lastNameEditText != null) {
            lastNameEditText.getEditText().getText().clear();
        }

        if (emailEditText != null) {
            emailEditText.getEditText().getText().clear();
        }
    }

    private void executeDownload() {
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        //Todo: move the code from bellow to the required method

                        List<User> users = new ArrayList<>();
                        Toast.makeText(MainActivity.this, "Objects in the Db: "+dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                        Log.e("Count", "" + dataSnapshot.getChildrenCount());
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            User user = postSnapshot.getValue(User.class);
                            users.add(user);
                        }
                        Log.e("Button pressed: ", "=============================================");
                        users.stream().forEach(user -> System.out.println(user.toString()));
                        Log.e("Button pressed: ", "=============================================");
                           // executeDownload();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //handle databaseError
                        Log.e("The read failed: ", error.getMessage());
                    }
                }
        );
    }

    private void collectUserData(Map<String, Object> users) {
        //Todo: remove this code and replace it with the one from the above method. you can also comment it
        List<User> userList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleUser = (Map) entry.getValue();
            userList.add((User) singleUser);
        }
    }

}