package com.example.knowballbrother2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tv_wc_register;
    private Button bt_login;
    private EditText et_name,et_password;
    private RadioButton rb_manager,rb_student;
    private RadioGroup rg_user;
    private String userName,psw,spPsw;
    private int u = 1;

    private void init(){
        tv_wc_register = findViewById(R.id.tv_wc_register);
        bt_login = findViewById(R.id.bt_login);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        rb_manager = findViewById(R.id.rb_manager);
        rb_student = findViewById(R.id.rb_student);
        rg_user = findViewById(R.id.rg_user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();

        et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

        tv_wc_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(WelcomeActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        rg_user.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rb_manager.isChecked()){
                    u = 0;
                }else if(rb_student.isChecked()){
                    u = 1;
                }
            }
        });


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (u == 0) {
                    userName = et_name.getText().toString().trim();
                    psw = et_password.getText().toString().trim();
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(WelcomeActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(psw)) {
                        Toast.makeText(WelcomeActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (userName.equals("manager") && psw.equals("123456")) {
                        Toast.makeText(WelcomeActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        WelcomeActivity.this.finish();
                        Intent intent = new Intent(WelcomeActivity.this, MessageActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("user",userName);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        return;
                    }else{
                        Toast.makeText(WelcomeActivity.this, "请输入正确的账号或密码", Toast.LENGTH_SHORT).show();
                    }
                } else if (u == 1) {
                    userName = et_name.getText().toString().trim();
                    psw = et_password.getText().toString().trim();
                    spPsw = readPsw(userName);
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(WelcomeActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(psw)) {
                        Toast.makeText(WelcomeActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(userName.equals("manager")){
                        Toast.makeText(WelcomeActivity.this, "此账号不存在", Toast.LENGTH_SHORT).show();
                    }else if(!userName.equals("manager")){
                        if (psw.equals(spPsw)) {
                            Toast.makeText(WelcomeActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            saveLoginStatus(true, userName);
                            Intent data = new Intent();
                            data.putExtra("isLogin", true);
                            setResult(RESULT_OK, data);
                            WelcomeActivity.this.finish();
                            Intent intent = new Intent(WelcomeActivity.this, MessageActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("user",userName);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            return;
                        } else if ((spPsw != null && !TextUtils.isEmpty(spPsw) && !psw.equals(spPsw))) {
                            Toast.makeText(WelcomeActivity.this, "输入的账号和密码不一致", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        Toast.makeText(WelcomeActivity.this, "此账号不存在", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private String readPsw(String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName , "");
    }

    private void saveLoginStatus(boolean status,String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin", status);
        editor.putString("loginUserName", userName);
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            String userName=data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                et_name.setText(userName);
                et_name.setSelection(userName.length());
            }
        }
    }
}
