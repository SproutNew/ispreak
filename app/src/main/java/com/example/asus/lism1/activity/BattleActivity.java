package com.example.asus.lism1.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.lism1.R;

public class BattleActivity extends Activity implements View.OnClickListener{

    //手势滑动
    private GestureDetector mDetector;
    private final static int MIN_MOVE = 200;   //最小距离
    private MyGestureListener mgListener;

    //底部导航栏
    private TextView fanchang;
    private TextView battle;
    private TextView linghun;
    private TextView kong;
    private TextView shu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        //顶部导航栏
        bindViews();
        battle.performClick();   //模拟一次点击，既进去后选择第一项

        //手势实现
        //实例化SimpleOnGestureListener与GestureDetector对象
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    //自定义一个GestureListener,这个是View类下的，别写错哦！！！
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            if(e1.getX() - e2.getX() > MIN_MOVE){
                startActivity(new Intent(BattleActivity.this, MainActivity.class));
                Toast.makeText(BattleActivity.this, "通过手势启动Activity", Toast.LENGTH_SHORT).show();
            }else if(e1.getX() - e2.getX()  < MIN_MOVE){
                finish();
                Toast.makeText(BattleActivity.this,"通过手势关闭Activity",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        fanchang = findViewById(R.id.fanchang);
        battle =  findViewById(R.id.battle);
        linghun =  findViewById(R.id.linghun);
        kong =  findViewById(R.id.kong);
        shu =  findViewById(R.id.shu);

        fanchang.setOnClickListener( this);
        battle.setOnClickListener(this);
        linghun.setOnClickListener(this);
        kong.setOnClickListener(this);
        shu.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        fanchang.setSelected(false);
        battle.setSelected(false);
        linghun.setSelected(false);
        kong.setSelected(false);
        shu.setSelected(false);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanchang:
                setSelected();
                fanchang.setSelected(true);
                break;
            case R.id.battle:
                setSelected();
                battle.setSelected(true);
                break;
            case R.id.linghun:
                setSelected();
                linghun.setSelected(true);
                break;
            case R.id.kong:
                setSelected();
                kong.setSelected(true);
                break;
            case R.id.shu:
                setSelected();
                shu.setSelected(true);
                break;
        }
    }
}
