package vn.com.arilliance.amazingwallpapers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Toolbar toolbar;
    private TextView txtTitle;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerArrowDrawable arrowDrawable;

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtTitle = (TextView) findViewById(R.id.txt_toolbar_title);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);

        setupToolbar();

        setupNavigationMenu();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        arrowDrawable = new DrawerArrowDrawable(this);
        arrowDrawable.setProgress(0f); // normal position
        arrowDrawable.setProgress(1f); // back arrow position

        setupNavigationDrawer(toolbar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(TextView txtTitle) {
        this.txtTitle = txtTitle;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public void setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return actionBarDrawerToggle;
    }

    public void setActionBarDrawerToggle(ActionBarDrawerToggle actionBarDrawerToggle) {
        this.actionBarDrawerToggle = actionBarDrawerToggle;
    }

    public DrawerArrowDrawable getArrowDrawable() {
        return arrowDrawable;
    }

    public void setArrowDrawable(DrawerArrowDrawable arrowDrawable) {
        this.arrowDrawable = arrowDrawable;
    }

    public FrameLayout getFragmentContainer() {
        return fragmentContainer;
    }

    public void setFragmentContainer(FrameLayout fragmentContainer) {
        this.fragmentContainer = fragmentContainer;
    }

    public void setToolbarTitle(String title){
        if (txtTitle != null){
            txtTitle.setText(title);
        }
    }

    private void setupToolbar(){
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void setupNavigationMenu() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void setupNavigationDrawer(Toolbar toolbar) {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerArrowDrawable(arrowDrawable);
        actionBarDrawerToggle.syncState();
    }
}
