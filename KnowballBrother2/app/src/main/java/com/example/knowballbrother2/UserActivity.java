package com.example.knowballbrother2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    private Button bt_compile,bt_logout,bt_modify;
    private Button bt_message;
    private TextView tv_uer_name,tv_uer_sex,tv_uer_college,tv_uer_class,tv_uer_tel;
    private String user,user_name,user_sex,user_college,user_class,user_tel;
    private DBHelper dbHelper;

    private void init(){
        bt_compile = findViewById(R.id.bt_compile);
        bt_logout = findViewById(R.id.bt_logout);
        bt_modify = findViewById(R.id.bt_modify);
        bt_message = findViewById(R.id.bt_message);
        tv_uer_name = findViewById(R.id.tv_user_name);
        tv_uer_sex = findViewById(R.id.tv_user_sex);
        tv_uer_college = findViewById(R.id.tv_user_college);
        tv_uer_class = findViewById(R.id.tv_user_class);
        tv_uer_tel = findViewById(R.id.tv_user_tel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();

        Bundle bundle = getIntent().getExtras();
        user = bundle.getString("user");

        if(!user.equals("manager")){
            bt_modify.setAlpha(0);
            bt_modify.setClickable(false);
        }else {
            bt_modify.setAlpha(1);
            bt_modify.setClickable(true);
        }

        dbHelper = new DBHelper(this,"KnowBall.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where user_account = ?",new String[]{user});
        cursor.moveToFirst();
        user_name = cursor.getString(cursor.getColumnIndex("user_name"));
        user_sex = cursor.getString(cursor.getColumnIndex("user_sex"));
        user_college = cursor.getString(cursor.getColumnIndex("user_college"));
        user_class = cursor.getString(cursor.getColumnIndex("user_class"));
        user_tel = cursor.getString(cursor.getColumnIndex("user_tel"));
        cursor.close();

        tv_uer_name.setText(user_name);
        tv_uer_sex.setText(user_sex);
        tv_uer_college.setText(user_college);
        tv_uer_class.setText(user_class);
        tv_uer_tel.setText(user_tel);

        bt_compile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = tv_uer_name.getText().toString();
                String user_sex = tv_uer_sex.getText().toString();
                String user_college = tv_uer_college.getText().toString();
                String user_class = tv_uer_class.getText().toString();
                String user_tel = tv_uer_tel.getText().toString();

                Intent intent = new Intent(UserActivity.this, CompileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",user_name);
                bundle.putString("sex",user_sex);
                bundle.putString("college",user_college);
                bundle.putString("class",user_class);
                bundle.putString("tel",user_tel);
                bundle.putString("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });

        bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,ModifyActivity.class);
                startActivity(intent);
            }
        });

        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,MessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
