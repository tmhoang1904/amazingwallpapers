package vn.com.arilliance.amazingwallpapers;

import android.os.Bundle;

import vn.com.arilliance.amazingwallpapers.explore.controller.ExploreFragment;
import vn.com.arilliance.amazingwallpapers.utils.Utils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolbarTitle("Explore");

        Utils.getInstance().replaceFragment(new ExploreFragment(), this);
    }
}
