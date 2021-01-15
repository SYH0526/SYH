package com.example.knowballbrother3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends AppCompatActivity {

    private List<Rank> rankList =new ArrayList<>();

    private TextView tv_list_rank,tv_list_college,tv_list_name,tv_list_number;
    private TextView tv_back_title;
    private ImageView iv_back;

    private void init(){
        tv_list_rank = findViewById(R.id.tv_list_rank);
        tv_list_college = findViewById(R.id.tv_list_college);
        tv_list_name = findViewById(R.id.tv_list_name);
        tv_list_number = findViewById(R.id.tv_list_number);
        iv_back = findViewById(R.id.iv_back);
        tv_back_title = findViewById(R.id.tv_back_title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        init();

        Bundle bundle = getIntent().getExtras();
        String ranktitle = bundle.getString("ranktitle");
        tv_back_title.setText(ranktitle);

        initRnak();
        RankAdapter adapter = new RankAdapter(RankActivity.this,R.layout.rank_item,rankList);
        ListView lv_list = findViewById(R.id.lv_list);
        lv_list.setAdapter(adapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RankActivity.this.finish();
            }
        });
    }

    private void initRnak(){
        Rank r1 = new Rank("1","管工学院","赵四","12");
        rankList.add(r1);
        Rank r2 = new Rank("2","管工学院","李三","10");
        rankList.add(r2);
        Rank r3 = new Rank("2","管工学院","黄二","8");
        rankList.add(r3);
        Rank r4 = new Rank("4","管工学院","王五","6");
        rankList.add(r4);
        Rank r5 = new Rank("5","管工学院","钱六","4");
        rankList.add(r5);
        Rank r6 = new Rank("6","管工学院","何七","2");
        rankList.add(r6);
    }
}
