package vn.com.arilliance.amazingwallpapers.explore.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import vn.com.arilliance.amazingwallpapers.MainActivity;
import vn.com.arilliance.amazingwallpapers.R;

/**
 * Created by Hoang Tran on 12/2/2016.
 */

public class ExploreFragment extends Fragment{
    private static ExploreFragment singleton = new ExploreFragment();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context;
    private View view;
    private MainActivity appCompatActivity;

    public static ExploreFragment getInstance() {
        return singleton;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);

        setupPager();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appCompatActivity = (MainActivity) context;
        this.context = context;
    }

    private void setupPager(){
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_explore);
        viewPager.setAdapter(new ExplorePagerAdapter(((FragmentActivity) context).getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs_explore);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        // Attach the page change listener to tab strip and **not** the view pager inside the activity
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(context,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }
            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
                Toast.makeText(context, "Scroll offset: " + positionOffset, Toast.LENGTH_LONG).show();
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }
}
