package org.wqiao.coolweather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import org.wqiao.coolweather.activity.MainActivityFragment;

/**
 * Created by wQiao on 16-5-5.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("SectionsPagerAdapter", "getItem: " + position);
        Fragment item;
        switch (position) {
            case 0:
                item = new MainActivityFragment();
                Log.d("SectionsPagerAdapter", "switch: 0");
                break;
            case 1:
                item = new Fragment();
                Log.d("SectionsPagerAdapter", "switch: 1");
                break;
            case 2:
                item = new Fragment();
                Log.d("SectionsPagerAdapter", "switch: 1");
                break;
            default:
                item = new Fragment();
        }
        Log.d("SectionsPagerAdapter", "switch: "+item.getClass().getSimpleName());
        return item;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "tab 1";
            case 1:
                return "tab 2";
            case 2:
                return "tab 3";
        }
        return null;
    }
}
