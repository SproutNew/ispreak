package com.example.asus.lism1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asus.lism1.R;
import java.util.ArrayList;

public class MainActivity extends Activity {

    //搜索功能
    private Button search1;
    private AutoCompleteTextView textView;
    public static final String[] CONTENTS = new String[]{"zg陕西","zg海南","zg新疆","zg西藏"};

    //轮播图实现
    private ViewPager viewPager;
    private LinearLayout point_group;
    private TextView image_desc;
    // 图片资源id
    private final int[] images = {R.mipmap.pic0, R.mipmap.pic1, R.mipmap.pic2};
    // 图片标题集合
    private final String[] imageDescriptions = {"第一张图片", "第二张图片", "第三张图片"};
    private ArrayList<ImageView> imageList;
    // 上一个页面的位置
    protected int lastPosition = 0;
    // 判断是否自动滚动viewPager
    private boolean isRunning = true;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 执行滑动到下一个页面
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                // 在发一个handler延时
                handler.sendEmptyMessageDelayed(0, 5000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //搜索功能实现
        search1 = findViewById(R.id.search1);
        textView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);

        //创建一个ArrayAdapter适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,CONTENTS);
        textView.setAdapter(adapter);

        //设置搜索点击按钮
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, textView.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        // 轮播图实现
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        point_group = (LinearLayout) findViewById(R.id.point_group);
        image_desc = (TextView) findViewById(R.id.image_desc);
        image_desc.setText(imageDescriptions[0]);

        // 初始化图片资源
        imageList = new ArrayList<ImageView>();
        for (int i : images) {
            // 初始化图片资源
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(i);
            imageList.add(imageView);

            // 添加指示小点
            ImageView point = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,
                    15);
            params.rightMargin = 20;
            params.bottomMargin = 10;
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.point_bg);
            if (i == R.mipmap.pic0) {
                //默认聚焦在第一张
                point.setBackgroundResource(R.drawable.point_bg_focus);
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            point_group.addView(point);
        }
        viewPager.setAdapter(new MyPageAdapter());
        // 设置当前viewPager的位置
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imageList.size()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 页面切换后调用， position是新的页面位置
                // 实现无限制循环播放
                position %= imageList.size();
                image_desc.setText(imageDescriptions[position]);
                // 把当前点设置为true,将上一个点设为false；并设置point_group图标
                point_group.getChildAt(position).setEnabled(true);
                point_group.getChildAt(position).setBackgroundResource(R.drawable.point_bg_focus);//设置聚焦时的图标样式
                point_group.getChildAt(lastPosition).setEnabled(false);
                point_group.getChildAt(lastPosition).setBackgroundResource(R.drawable.point_bg);//上一张恢复原有图标
                lastPosition = position;
            }
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 页面正在滑动时间回调
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                // 当pageView 状态发生改变的时候，回调
            }
        });
        /**
         * 自动循环： 1.定时器：Timer 2.开子线程：while true循环 3.ClockManger
         * 4.用Handler发送延时信息，实现循环，最简单最方便
         *
         */
        handler.sendEmptyMessageDelayed(0, 3000);

    }
    @Override
    protected void onDestroy() {
    // 停止滚动
        isRunning = false;
        super.onDestroy();
    }
    private class MyPageAdapter extends PagerAdapter {
        // 需要实现以下四个方法
        @Override
        public int getCount() {
            // 获得页面的总数
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            // 判断view和Object对应是否有关联关系
            if (view == object) {
                return true;
            } else {
                return false;
            }
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 获得相应位置上的view； container view的容器，其实就是viewpage自身,
            // position: viewpager上的位置
            // 给container添加内容
            //container.addView(imageList.get(position % imageList.size()));
             //return imageList.get(position % imageList.size());
            View ret = null;
            //对ViewPager页号求摸取出View列表中要显示的项
            ret = imageList.get(position % imageList.size());
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent viewParent = ret.getParent();
            if (viewParent != null) {
                ViewGroup parent = (ViewGroup) viewParent;
                parent.removeView(ret);
            }
            container.addView(ret);
            return ret;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 销毁对应位置上的Object
            // super.destroyItem(container, position, object);
            //container.removeView((View) object);
            //object = null;
        }
    }
}


