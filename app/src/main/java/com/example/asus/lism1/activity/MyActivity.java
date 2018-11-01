package com.example.asus.lism1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.asus.lism1.R;
import com.example.asus.lism1.my.IdeaActivity;
import com.example.asus.lism1.utils.CircularImage;

public class MyActivity extends Activity {

    private TextView my10;
    private TextView my4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //头像框
        CircularImage img_round = (CircularImage) findViewById(R.id.my1);
        img_round.setImageResource(R.mipmap.pic0);

        //退出登录
        my10=findViewById(R.id.my10);
        my10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MyActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //意见反馈
        my4=findViewById(R.id.my4);
        my4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MyActivity.this, IdeaActivity.class);
                startActivity(intent);
            }
        });
    }
}
