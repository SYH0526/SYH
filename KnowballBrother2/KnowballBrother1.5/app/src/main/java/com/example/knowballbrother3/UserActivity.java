package com.example.knowballbrother3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    private Button bt_compile,bt_logout,bt_modify,bt_record;
    private Button bt_message;
    private TextView tv_uer_name,tv_uer_sex,tv_uer_college,tv_uer_class,tv_uer_tel;
    private TextView tv_football_score,tv_football_assist,tv_football_time;
    private TextView tv_basketball_score,tv_basketball_assist,tv_basketball_time;
    private TextView tv_volleyball_score,tv_volleyball_assist,tv_volleyball_time;
    private TextView tv_pingpong_round,tv_pingpong_rate,tv_pingpong_time;
    private TextView tv_badminton_round,tv_badminton_rate,tv_badminton_time;
    private String user,user_name,user_sex,user_college,user_class,user_tel;
    private int football_score,football_assist,football_time;
    private int basketball_score,basketball_assist,basketball_time;
    private int volleyball_score,volleyball_assist,volleyball_time;
    private int pingpong_round,pingpong_time;
    private int badminton_round,badminton_time;
    private String pingpong_rate,badminton_rate;
    private DBHelper dbHelper;

    private void init(){
        bt_compile = findViewById(R.id.bt_compile);
        bt_logout = findViewById(R.id.bt_logout);
        bt_modify = findViewById(R.id.bt_modify);
        bt_message = findViewById(R.id.bt_message);
        bt_record = findViewById(R.id.bt_record);
        tv_uer_name = findViewById(R.id.tv_user_name);
        tv_uer_sex = findViewById(R.id.tv_user_sex);
        tv_uer_college = findViewById(R.id.tv_user_college);
        tv_uer_class = findViewById(R.id.tv_user_class);
        tv_uer_tel = findViewById(R.id.tv_user_tel);
        tv_football_score = findViewById(R.id.tv_football_score);
        tv_football_assist = findViewById(R.id.tv_football_assist);
        tv_football_time = findViewById(R.id.tv_football_time);
        tv_basketball_score = findViewById(R.id.tv_basketball_score);
        tv_basketball_assist = findViewById(R.id.tv_basketball_assist);
        tv_basketball_time = findViewById(R.id.tv_basketball_time);
        tv_volleyball_score = findViewById(R.id.tv_volleyball_score);
        tv_volleyball_assist = findViewById(R.id.tv_volleyball_assist);
        tv_volleyball_time = findViewById(R.id.tv_volleyball_time);
        tv_pingpong_round = findViewById(R.id.tv_pingpong_round);
        tv_pingpong_rate = findViewById(R.id.tv_pingpong_rate);
        tv_pingpong_time = findViewById(R.id.tv_pingpong_time);
        tv_badminton_round = findViewById(R.id.tv_badminton_round);
        tv_badminton_rate = findViewById(R.id.tv_badminton_rate);
        tv_badminton_time = findViewById(R.id.tv_badminton_time);
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
            bt_record.setAlpha(0);
            bt_record.setClickable(false);
        }else {
            bt_modify.setAlpha(1);
            bt_modify.setClickable(true);
            bt_record.setAlpha(1);
            bt_record.setClickable(true);
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
        football_score = cursor.getInt(cursor.getColumnIndex("football_score"));
        football_assist = cursor.getInt(cursor.getColumnIndex("football_assist"));
        football_time = cursor.getInt(cursor.getColumnIndex("football_time"));
        basketball_score = cursor.getInt(cursor.getColumnIndex("basketball_score"));
        basketball_assist = cursor.getInt(cursor.getColumnIndex("basketball_assist"));
        basketball_time = cursor.getInt(cursor.getColumnIndex("basketball_time"));
        volleyball_score = cursor.getInt(cursor.getColumnIndex("volleyball_score"));
        volleyball_assist = cursor.getInt(cursor.getColumnIndex("volleyball_assist"));
        volleyball_time = cursor.getInt(cursor.getColumnIndex("volleyball_time"));
        pingpong_round = cursor.getInt(cursor.getColumnIndex("pingpong_round"));
        pingpong_rate = cursor.getInt(cursor.getColumnIndex("pingpong_wins"))*100/cursor.getInt(cursor.getColumnIndex("pingpong_round"))+"%";
        pingpong_time = cursor.getInt(cursor.getColumnIndex("pingpong_time"));
        badminton_round = cursor.getInt(cursor.getColumnIndex("badminton_round"));
        badminton_rate = cursor.getInt(cursor.getColumnIndex("badminton_wins"))*100/cursor.getInt(cursor.getColumnIndex("badminton_round"))+"%";
        badminton_time = cursor.getInt(cursor.getColumnIndex("badminton_time"));
        cursor.close();

        tv_uer_name.setText(user_name);
        tv_uer_sex.setText(user_sex);
        tv_uer_college.setText(user_college);
        tv_uer_class.setText(user_class);
        tv_uer_tel.setText(user_tel);
        tv_football_score.setText(football_score);
        tv_football_assist.setText(football_assist);
        tv_football_time.setText(football_time);
        tv_basketball_score.setText(basketball_score);
        tv_basketball_assist.setText(basketball_assist);
        tv_basketball_time.setText(basketball_time);
        tv_volleyball_score.setText(volleyball_score);
        tv_volleyball_assist.setText(volleyball_assist);
        tv_volleyball_time.setText(volleyball_time);
        tv_pingpong_round.setText(pingpong_round);
        tv_pingpong_rate.setText(pingpong_rate);
        tv_pingpong_time.setText(pingpong_time);
        tv_badminton_round.setText(badminton_round);
        tv_badminton_rate.setText(badminton_rate);
        tv_badminton_time.setText(badminton_time);

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
