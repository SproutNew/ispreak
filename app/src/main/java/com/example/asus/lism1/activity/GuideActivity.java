package com.example.asus.lism1.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.asus.lism1.R;
import com.example.asus.lism1.SplashActivity;
import com.example.asus.lism1.utils.CacheUtils;
import com.example.asus.lism1.utils.DensityUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;
    private int leftmax; //两点间距
    private int widthdpi; //转换像素工具

    private ArrayList<ImageView> imageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        btn_start_main= (Button) findViewById(R.id.btn_start_main);
        ll_point_group= (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point=(ImageView)findViewById(R.id.iv_red_point);

        //准备数据
        int[] ids = new int[]{
                R.drawable.guide1,
                R.drawable.guide2,
                R.drawable.guide3
        };

        widthdpi = DensityUtils.dip2px(this,10);    //将10像素适配

        imageViews = new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);
            //添加到集合中
            imageViews.add(imageView);

            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             * 像素单位
             * 把单位当成dp转成对应的像素
             */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if(i!=0){
                //第一个不用设置 其他距离左边10个像素
                params.leftMargin = widthdpi;
            }
            point.setLayoutParams(params);
            //添加到线性布局里面
            ll_point_group.addView(point);
        }

        //设置viewpager的适配器
        viewPager.setAdapter(new MyPagerAdapter());

        //根据我们的view的生命周期，当视图执行到layout或者onDraw的时候，视图的高和宽，边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalLayoutListener());

        //得到屏幕滑动的百分比
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        //设置按钮的点击事件
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存曾经进入主页面
                CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MIAN,true);
                //跳转到登录页面
                Intent intent =new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent);
                //关闭引导页面
                finish();
            }
        });
    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        /**
         * 页面滚动回调这个方法
         * @param position 滑动位置
         * @param positionOffset    滑动百分比
         * @param positionOffsetPixels  滑动像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点间移动的距离=屏幕百分比*间距
            //int leftmargin = (int) (positionOffset*leftmax);
            //两点间滑动距离对应的坐标 = 原来的初始位置 + 两点间移动的距离
            int leftmargin = (int) (position*leftmax+positionOffset*leftmax);
            //params.leftmargin = 两点间滑动距离对应的坐标
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);
        }

        /**
         * 当页面被选中的时候回调这个方法
         * @param position  被选中页面的对应位置
         */
        @Override
        public void onPageSelected(int position) {
            if(position==imageViews.size()-1){
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);
            }else {
                //其他页面
                btn_start_main.setVisibility(View.GONE);
            }
        }

        /**
         * 当viewpager页面滑动状态发生变化的时候
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    class MyGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {
            //执行不止一次
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            //计算间距=第一个点距离左边-第0个点距离左边
            leftmax=ll_point_group.getChildAt(1).getLeft()-ll_point_group.getChildAt(0).getLeft();
        }
    }
    class MyPagerAdapter extends PagerAdapter {

        //返回数据总个数
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         *
         * @param container viempager
         * @param position  要创建的页面
         * @return 返回和创建当前页面右关系的值
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);
            return imageView;
            //return super.instantiateItem(container, position);
        }

        //视图 上面方法返回
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //return view == imageViews.get(Integer.parseInt((String)object));
            return view==object;
        }

        /**
         * 销毁页面
         * @param container   ViewPager
         * @param position  要销毁页面的位置
         * @param object    要销毁的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
