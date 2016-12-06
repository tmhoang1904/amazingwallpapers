package vn.com.arilliance.amazingwallpapers.explore.controller;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Toast;

import vn.com.arilliance.amazingwallpapers.explore.controller.tabfragment.CategoriesFragment;
import vn.com.arilliance.amazingwallpapers.explore.controller.tabfragment.FeaturedFragment;

/**
 * Created by Hoang Tran on 12/2/2016.
 */

public class ExplorePagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 5;
    private String tabTitles[] = new String[] { "CATEGORIES", "FEATURED", "RECENT", "DAILY POPULAR", "SHUFFLE" };

    public ExplorePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("Position", String.valueOf(position));
        switch (position){
            case 1:
                return CategoriesFragment.newInstance(position);
            case 2:
                return FeaturedFragment.newInstance(position);
            default:
                return CategoriesFragment.newInstance(position);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
