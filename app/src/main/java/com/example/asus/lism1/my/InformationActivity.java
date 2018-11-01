package com.example.asus.lism1.my;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asus.lism1.R;
import com.example.asus.lism1.utils.WiperSwitch;

public class InformationActivity extends Activity implements WiperSwitch.OnChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //实例化WiperSwitch
        WiperSwitch wiperSwitch = (WiperSwitch)findViewById(R.id.wiperSwitch1);
        //设置初始状态为false
         wiperSwitch.setChecked(false);
         //设置监听
         wiperSwitch.setOnChangedListener(this);
    }

    @Override
    public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
        if (checkState){
            Toast.makeText(InformationActivity.this,"开关开启了",Toast.LENGTH_SHORT).show();
        }else if (!checkState){
            Toast.makeText(InformationActivity.this,"开关关闭了",Toast.LENGTH_SHORT).show();
        }

    }
}
