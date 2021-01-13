package com.example.knowballbrother;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tv_wc_register;
    private Button bt_login;

    private void init(){
        tv_wc_register = findViewById(R.id.tv_wc_register);
        bt_login = findViewById(R.id.bt_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();

        tv_wc_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(WelcomeActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this,MessageActivity.class);
                startActivity(i);
            }
        });
    }
}
