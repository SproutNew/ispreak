package com.example.asus.lism1.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.lism1.R;
import com.example.asus.lism1.utils.CircularImage;

public class MessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //加载圆形头像框
        CircularImage message_1 = (CircularImage) findViewById(R.id.message_1);
        message_1.setImageResource(R.mipmap.pic0);

    }
}
