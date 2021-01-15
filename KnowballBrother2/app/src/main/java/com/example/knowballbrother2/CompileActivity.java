package com.example.knowballbrother2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CompileActivity extends AppCompatActivity {

    private ImageView iv_back;
    private Button bt_finish;
    private EditText et_compile_name,et_compile_sex,et_compile_college,et_compile_class,et_compile_tel;
    private DBHelper dbHelper;
    private String user_account;

    private void init(){
        iv_back = findViewById(R.id.iv_back);
        bt_finish = findViewById(R.id.bt_finish);
        et_compile_name = findViewById(R.id.et_compile_name);
        et_compile_sex = findViewById(R.id.et_compile_sex);
        et_compile_college = findViewById(R.id.et_compile_college);
        et_compile_class = findViewById(R.id.et_compile_class);
        et_compile_tel = findViewById(R.id.et_compile_tel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile);
        init();

        dbHelper = new DBHelper(this,"KnowBall.db",null,3);

        Bundle bundle = getIntent().getExtras();
        String user_name = bundle.getString("name");
        String user_sex = bundle.getString("sex");
        String user_college = bundle.getString("college");
        String user_class = bundle.getString("class");
        String user_tel = bundle.getString("tel");
        user_account = bundle.getString("user");

        et_compile_name.setText(user_name);
        et_compile_sex.setText(user_sex);
        et_compile_college.setText(user_college);
        et_compile_class.setText(user_class);
        et_compile_tel.setText(user_tel);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompileActivity.this.finish();
            }
        });

        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();;
                ContentValues values =new ContentValues();

                values.put("user_name",et_compile_name.getText().toString().trim());
                values.put("user_sex",et_compile_sex.getText().toString().trim());
                values.put("user_college",et_compile_college.getText().toString().trim());
                values.put("user_class",et_compile_class.getText().toString().trim());
                values.put("user_tel",et_compile_tel.getText().toString().trim());

                db.update("User",values,"user_account = ?",new String[]{user_account});
                values.clear();

                Intent intent = new Intent(CompileActivity.this,UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",user_account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
