package com.example.asus.lism1.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.asus.lism1.R;

public class MyActivity extends Activity {

    private TextView my10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        my10=findViewById(R.id.my10);
        my10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MyActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
