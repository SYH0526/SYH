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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private List<Message> messageList =new ArrayList<>();

    private TextView tv_message_title;
    private TextView tv_list_1,tv_list_2,tv_list_3;
    private TextView tv_football,tv_basketball,tv_volleyball,tv_pingpong,tv_badminton;
    private ImageView iv_football,iv_basketball,iv_volleyball,iv_pingpong,iv_badminton;
    private ImageView iv_message_picture;
    private ListView list_message;
    private Button bt_message,bt_user;
    private String user;
    private DBHelper dbHelper;

    private void init(){
        tv_message_title = (TextView)findViewById(R.id.tv_message_title);
        iv_message_picture = (ImageView)findViewById(R.id.iv_message_picture);
        tv_list_1 = findViewById(R.id.tv_list_1);
        tv_list_2 = findViewById(R.id.tv_list_2);
        tv_list_3 = findViewById(R.id.tv_list_3);
        tv_football = findViewById(R.id.tv_football);
        tv_basketball = findViewById(R.id.tv_basketball);
        tv_volleyball = findViewById(R.id.tv_volleyball);
        tv_pingpong = findViewById(R.id.tv_pingpong);
        tv_badminton = findViewById(R.id.tv_badminton);
        iv_football = findViewById(R.id.iv_football);
        iv_basketball = findViewById(R.id.iv_basketball);
        iv_volleyball = findViewById(R.id.iv_volleyball);
        iv_pingpong = findViewById(R.id.iv_pingpong);
        iv_badminton = findViewById(R.id.iv_badminton);
        list_message = findViewById(R.id.list_message);
        bt_message = findViewById(R.id.bt_message);
        bt_user = findViewById(R.id.bt_user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();

        Bundle bundle = getIntent().getExtras();
        user = bundle.getString("user");

        dbHelper = new DBHelper(this,"KnowBall.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Message",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                if(kind.equals("football")){
                    String title = cursor.getString(cursor.getColumnIndex("message_title"));
                    String content = cursor.getString(cursor.getColumnIndex("message_content"));
                    Message message = new Message(R.drawable.picture,title,content);
                    messageList.add(message);
                }
            }while(cursor.moveToNext());
        }
        cursor.close();

        //initMessage_football();
        final MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
        final ListView list_message = (ListView)findViewById(R.id.list_message);
        list_message.setAdapter(adapter);

        list_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView)parent;
                Message message = (Message) listView.getItemAtPosition(position);
                Integer picture = message.getPicture();
                String title = message.getTitle();
                String content = message.getContent();

                Intent intent = new Intent(MessageActivity.this,EvaluateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("picture",picture);
                bundle.putString("title",title);
                bundle.putString("content",content);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        tv_list_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ranktitle = tv_list_1.getText().toString();
                Intent intent =new Intent(MessageActivity.this,RankActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ranktitle",ranktitle);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        bt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this,UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        tv_football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)1);
                iv_basketball.setAlpha((float)0);
                iv_volleyball.setAlpha((float)0);
                iv_pingpong.setAlpha((float)0);
                iv_badminton.setAlpha((float)0);
                tv_list_1.setText("得 分 榜");
                tv_list_2.setText("助 攻 榜");
                tv_list_3.setText("时 长 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("football")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });

        tv_basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)0);
                iv_basketball.setAlpha((float)1);
                iv_volleyball.setAlpha((float)0);
                iv_pingpong.setAlpha((float)0);
                iv_badminton.setAlpha((float)0);
                tv_list_1.setText("得 分 榜");
                tv_list_2.setText("助 攻 榜");
                tv_list_3.setText("时 长 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("basketball")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });

        tv_volleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)0);
                iv_basketball.setAlpha((float)0);
                iv_volleyball.setAlpha((float)1);
                iv_pingpong.setAlpha((float)0);
                iv_badminton.setAlpha((float)0);
                tv_list_1.setText("得 分 榜");
                tv_list_2.setText("助 攻 榜");
                tv_list_3.setText("时 长 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("volleyball")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });

        tv_pingpong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)0);
                iv_basketball.setAlpha((float)0);
                iv_volleyball.setAlpha((float)0);
                iv_pingpong.setAlpha((float)1);
                iv_badminton.setAlpha((float)0);
                tv_list_1.setText("场 次 榜");
                tv_list_2.setText("胜 率 榜");
                tv_list_3.setText("时 长 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("pingpong")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });

        tv_badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)0);
                iv_basketball.setAlpha((float)0);
                iv_volleyball.setAlpha((float)0);
                iv_pingpong.setAlpha((float)0);
                iv_badminton.setAlpha((float)1);
                tv_list_1.setText("场 次 榜");
                tv_list_2.setText("胜 率 榜");
                tv_list_3.setText("时 长 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("badminton")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });
    }
}
