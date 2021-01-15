package com.example.knowballbrother3;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private ImageView iv_back;
    private Button bt_rg_register;
    private EditText et_account,et_psw,et_pswagain;
    private String userName,psw,pswAgain;
    private DBHelper dbHelper;

    private void init(){
        iv_back = findViewById(R.id.iv_back);
        bt_rg_register = findViewById(R.id.bt_rg_register);
        et_account = findViewById(R.id.et_account);
        et_psw = findViewById(R.id.et_psw);
        et_pswagain = findViewById(R.id.et_pswagain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(this,"KnowBall.db",null,3);
        init();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        bt_rg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }else if(isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    saveRegisterInfo(userName, psw);
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("user_account",userName);
                    values.put("user_password",psw);
                    values.put("user_name","");
                    values.put("user_sex","");
                    values.put("user_college","");
                    values.put("user_class","");
                    values.put("user_tel","");
                    values.put("football_score",0);
                    values.put("football_assist",0);
                    values.put("football_time,",0);
                    values.put( "basketball_score",0);
                    values.put( "basketball_assist",0);
                    values.put("basketball_time",0);
                    values.put("volleyball_score",0);
                    values.put( "volleyball_assist",0);
                    values.put(  "volleyball_time",0);
                    values.put(  "pingpong_wins",0);
                    values.put(  "pingpong_round",0);
                    values.put(  "pingpong_time",0);
                    values.put( "badminton_wins",0);
                    values.put( "badminton_round",0);
                    values.put( "badminton_time",0);
                    db.insert("User",null,values);
                    values.clear();
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    private void getEditString(){
        userName=et_account.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain=et_pswagain.getText().toString().trim();
    }

    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw=sp.getString(userName, "");
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }

    private void saveRegisterInfo(String userName,String psw){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName, psw);
        editor.commit();
    }

}
