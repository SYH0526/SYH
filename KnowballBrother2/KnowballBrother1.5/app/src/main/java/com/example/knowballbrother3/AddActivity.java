package com.example.knowballbrother3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private TextView tv_back_title;
    private ImageView iv_back;
    private Button bt_add_picture,bt_add_finish;
    private EditText et_add_title,et_add_content;
    private Spinner sp_kind;
    private String add_title,add_content;
    private String kind;
    private byte[] b ;
    private static final String[] list={"足  球","篮  球","排  球","乒乓球","羽毛球"};
    private ArrayAdapter<String> adapter;

    private DBHelper dbHelper;

    private void init(){
        tv_back_title = findViewById(R.id.tv_back_title);
        iv_back = findViewById(R.id.iv_back);
        bt_add_picture = findViewById(R.id.bt_add_picture);
        bt_add_finish = findViewById(R.id.bt_add_finish);
        et_add_title = findViewById(R.id.et_add_title);
        et_add_content = findViewById(R.id.et_add_content);
        sp_kind = findViewById(R.id.sp_kind);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbHelper = new DBHelper(this,"KnowBall.db",null,3);
        init();

        tv_back_title.setText("添加资讯");

        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_kind.setAdapter(adapter);
        sp_kind.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    kind = "football";
                }else if(position == 1){
                    kind = "basketball";
                }else if(position == 2){
                    kind = "volleyball";
                }else if(position == 3){
                    kind = "pingpong";
                }else if(position == 4){
                    kind = "badminton";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,ModifyActivity.class);
                startActivity(intent);
                AddActivity.this.finish();
            }
        });

        bt_add_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bt_add_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_title = et_add_title.getText().toString().trim();
                add_content = et_add_content.getText().toString().trim();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("message_title",add_title);
                values.put("message_content",add_content);
                values.put("message_picture",b);
                values.put("message_kind",kind);
                db.insert("Message",null,values);
                values.clear();
                Intent intent = new Intent(AddActivity.this,ModifyActivity.class);
                startActivity(intent);
                AddActivity.this.finish();
            }
        });
    }

}
