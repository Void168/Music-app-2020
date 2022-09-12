package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Singer extends AppCompatActivity {
    ImageButton btnbackmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer);
        AnhXa();

        btnbackmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Singer.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa(){
        btnbackmain = (ImageButton) findViewById(R.id.btnbackmain);
    }
}