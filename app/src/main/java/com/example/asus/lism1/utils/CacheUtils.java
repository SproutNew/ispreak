package com.example.asus.lism1.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.asus.lism1.activity.GuideActivity;

/**
 * Created by asus on 2018/10/13.
 */

public class CacheUtils {
    /**
     * 得到缓存
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }

    /**
     * 保存软件参数
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
}
