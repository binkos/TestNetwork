package com.example.testnetwork.Fragments.PixabayGallery;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testnetwork.R;
import com.squareup.picasso.Picasso;

public class MyDialogFragment extends AppCompatActivity {
    ImageView imageView;
    TextView textViewUserName;
    TextView textViewLikes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fragment_layout);

        imageView = findViewById(R.id.image_view);
        textViewUserName = findViewById(R.id.username_view);
        textViewLikes = findViewById(R.id.likes_view);

        Intent intent = getIntent();
        if (intent!=null){
            Picasso.with(getApplicationContext()).load(intent.getStringExtra("url")).fit().centerInside().into(imageView);
            textViewUserName.setText("Username: "+  intent.getStringExtra("username"));
            textViewLikes.setText("User: "+intent.getIntExtra("likes",0));

            Toast.makeText(getApplicationContext(),intent.getStringExtra("url"),Toast.LENGTH_LONG).show();

        }
    }
}
