package com.example.asus.lism1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;

import com.example.asus.lism1.activity.GuideActivity;
import com.example.asus.lism1.activity.LoginActivity;
import com.example.asus.lism1.activity.MainActivity;
import com.example.asus.lism1.utils.CacheUtils;

public class SplashActivity extends Activity {

        public static final String START_MIAN = "start_main";
        private RelativeLayout activity_splash;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            activity_splash = findViewById(R.id.activity_splash);

            //渐变动画 缩放动画 旋转动画
            AlphaAnimation aa = new AlphaAnimation(0, 1);
            //aa.setDuration(500);    //持续播放时间
            aa.setFillAfter(false);

            //ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
            //sa.setDuration(500);
            //sa.setFillAfter(false);

            //RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            //ra.setDuration(500);
            // ra.setFillAfter(false);

            AnimationSet set = new AnimationSet(false);
            //添加动画没有先后顺序
            set.addAnimation(aa);
            //set.addAnimation(ra);
            //set.addAnimation(sa);
            set.setDuration(2000);
            activity_splash.startAnimation(set);

            set.setAnimationListener(new MyAnimationListener());

        }

        class MyAnimationListener implements Animation.AnimationListener {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断是否进入主页面
                boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MIAN);
                Intent intent;
                if (isStartMain) {
                    //如果进入过引导页，直接进入登录界面
                    intent =new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                } else {
                    //如果没有进入过，进入引导页
                    intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                }
                //关闭splash页面
                finish();
                //Toast.makeText(SplashActivity.this,"欢迎来到绘声苑",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }
}
