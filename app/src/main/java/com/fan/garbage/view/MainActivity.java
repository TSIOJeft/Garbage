package com.fan.garbage.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fan.garbage.R;
import com.fan.garbage.adpater.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Objects;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
//Tablayout about
        TabLayout tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
//        TabLayout.Tab o = tabLayout.getTabAt(0);
//        TabLayout.Tab oo = tabLayout.getTabAt(1);
//        TabLayout.Tab ooo = tabLayout.getTabAt(2);
//        TabLayout.Tab oooo = tabLayout.getTabAt(3);
//        o.setIcon(R.drawable.ic_flag_white_24dp);
//        oo.setIcon(R.drawable.ic_package_variant);
//        ooo.setIcon(R.drawable.ic_hangouts);
//        oooo.setIcon(R.drawable.ic_leaf);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_add_shopping_cart_white_48dp);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_star_white_48dp);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_mood_white_48dp);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.ic_lightbulb_outline_white_48dp);
    }

    public void initData() {
        Bmob.initialize(this, getString(R.string.bmob_id));
//        BmobDataSet.dataSet("test", "test", "test", "test", "test", "10", 1);
        CrashReport.initCrashReport(this, getString(R.string.bugly_id), false);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_about) {
            startActivity(new Intent().setClass(this, AboutActivity.class));
        } else if (id == R.id.nav_pc) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_rb) {
            viewPager.setCurrentItem(3);
        } else if (id == R.id.nav_rc) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_share) {
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
