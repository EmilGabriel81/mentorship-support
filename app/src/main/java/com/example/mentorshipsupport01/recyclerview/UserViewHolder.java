package com.example.mentorshipsupport01.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mentorshipsupport01.R;

public class UserViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView firstName, lastName, email;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        firstName = itemView.findViewById(R.id.firstName);
        lastName = itemView.findViewById(R.id.lastName);
        email = itemView.findViewById(R.id.email);
    }
}
