package org.wqiao.coolweather.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.mikepenz.iconics.Iconics;
import org.wqiao.coolweather.typeface.CustomFontIcon;

/**
 * Created by wQiao on 16-4-28.
 */
public class CoolApplication extends Application {

    private static final String  TAG = CoolApplication.class.getSimpleName();

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Log.d(TAG, "onCreate");

        //only required if you add a custom or generic font on your own
//        Iconics.init(getApplicationContext());
        //register custom fonts like this (or also provide a font definition file)
        Iconics.registerFont(new CustomFontIcon());
    }

    public static Context getContext() {
        return context;
    }
}
