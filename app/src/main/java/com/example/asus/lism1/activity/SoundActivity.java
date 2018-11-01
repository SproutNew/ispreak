package com.example.asus.lism1.activity;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.asus.lism1.R;
import com.example.asus.lism1.utils.CircularImage;
import com.example.asus.lism1.utils.MyPagerAdapter;

import java.util.ArrayList;

public class SoundActivity extends Activity {

    private ViewPager vpager_two;
    private ArrayList<View> aList;
    private ArrayList<String> sList;
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        vpager_two = (ViewPager) findViewById(R.id.vpager_two);
        aList = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        //aList.add(li.inflate(R.id.one3),R.mipmap.pic0);
        aList.add(li.inflate(R.layout.view_one,null,false));
        aList.add(li.inflate(R.layout.view_two,null,false));
        aList.add(li.inflate(R.layout.view_three, null, false));
        sList = new ArrayList<String>();
        sList.add("方言翻唱");
        sList.add("方言battle");
        sList.add("浅棕");
        mAdapter = new MyPagerAdapter(aList,sList);
        vpager_two.setAdapter(mAdapter);

        //头像框
//        CircularImage img_round = (CircularImage) findViewById(R.id.one3);
//        img_round.setImageResource(R.mipmap.pic0);
    }
}
