package com.example.knowballbrother;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {

    private Button bt_compile,bt_logout;
    private Button bt_message,bt_user;

    private void init(){
        bt_compile = findViewById(R.id.bt_compile);
        bt_logout = findViewById(R.id.bt_logout);
        bt_message = findViewById(R.id.bt_message);
        bt_user = findViewById(R.id.bt_user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();

        bt_compile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });

        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,MessageActivity.class);
                startActivity(intent);
            }
        });
    }
}
