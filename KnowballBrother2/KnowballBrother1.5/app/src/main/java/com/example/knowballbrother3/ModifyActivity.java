package com.example.knowballbrother3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ModifyActivity extends AppCompatActivity {

    private List<Modify> modifyList =new ArrayList<>();

    private TextView tv_back_title;
    private ImageView iv_back;
    private Button bt_add,bt_delete;
    private DBHelper dbHelper;

    private void init(){
        tv_back_title = findViewById(R.id.tv_back_title);
        iv_back = findViewById(R.id.iv_back);
        bt_add = findViewById(R.id.bt_add);
        //bt_delete = findViewById(R.id.bt_delete);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        init();

        tv_back_title.setText("编辑资讯");

        dbHelper = new DBHelper(this,"KnowBall.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("Message",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex("message_title"));
                String content = cursor.getString(cursor.getColumnIndex("message_content"));
                Modify modify = new Modify(R.drawable.picture,title);
                modifyList.add(modify);
            }while(cursor.moveToNext());
        }
        cursor.close();

        final ModifyAdapter adapter = new ModifyAdapter(ModifyActivity.this,R.layout.modify_item,modifyList);
        final ListView list_modify = (ListView)findViewById(R.id.list_modify);
        list_modify.setAdapter(adapter);

        list_modify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView)parent;
                Modify modify = (Modify) listView.getItemAtPosition(position);
                String title = modify.getTitle();
                Toast.makeText(ModifyActivity.this,"已删除“" +title+"”", Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Message","message_title = ?",new String[]{title});
                Intent intent = new Intent(ModifyActivity.this,ModifyActivity.class);
                startActivity(intent);
                ModifyActivity.this.finish();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyActivity.this.finish();
            }
        });

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyActivity.this,AddActivity.class);
                startActivity(intent);
                ModifyActivity.this.finish();
            }
        });

    }

}
