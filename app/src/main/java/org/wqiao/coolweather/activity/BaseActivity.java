package org.wqiao.coolweather.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

/**
 * Created by wQiao on 16-4-12.
 */
public class BaseActivity  extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
//        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));  // 使用icon font
        // Android-Iconics 与 Calligraphy 不兼容
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase)); //使用自定义字体
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("BaseActivity ---->",getClass().getSimpleName());

        /**
         * 开启 Iconics
         * Define IconicsLayoutInflater to enable automatic xml icons detection (optional)
         * This will enable automatic icon detection for TextViews,Buttons, and allow you to set icons on ImageView's via xml.
         */
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
//    }
//
//    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left); //动画效果
//    }

}
