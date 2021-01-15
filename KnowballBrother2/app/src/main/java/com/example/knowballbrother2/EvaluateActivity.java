package com.example.knowballbrother2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EvaluateActivity extends AppCompatActivity {

    private TextView tv_evaluate_title,tv_evaluate_content;
    private ImageView iv_evaluate_picture;
    private ImageView iv_back;

    private void init(){
        tv_evaluate_title = findViewById(R.id.tv_evaluate_title);
        tv_evaluate_content = findViewById(R.id.tv_evaluate_content);
        iv_evaluate_picture = findViewById(R.id.iv_evaluate_picture);
        iv_back = findViewById(R.id.iv_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        init();

        Bundle bundle = getIntent().getExtras();
        Integer picture = bundle.getInt("picture");
        String title = bundle.getString("title");
        String content = bundle.getString("content");

        tv_evaluate_title.setText(title);
        tv_evaluate_content.setText(content);
        iv_evaluate_picture.setBackgroundResource(picture);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvaluateActivity.this.finish();
            }
        });
    }
}
