package com.example.sinjihye.foodpic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sinjihye.foodpic.AlarmPackage.AlarmFragment;
import com.example.sinjihye.foodpic.AnalisticPackage.AnalyticsFragment;
import com.example.sinjihye.foodpic.CalendarPackage.CalendarFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    BottomNavigationView bottomNavigationView;
    Menu menu;
    ViewPager viewPager;
    FragAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        viewPager = findViewById(R.id.view_pager);
        adapter = new FragAdapter(getSupportFragmentManager());
        adapter.addFragment(new AnalyticsFragment());
        adapter.addFragment(new CalendarFragment());
        adapter.addFragment(new AlarmFragment());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        menu = bottomNavigationView.getMenu();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.analystic:
                viewPager.setCurrentItem(0);
                break;

            case R.id.calendar:
                viewPager.setCurrentItem(1);
                break;

            case R.id.alarm:
                viewPager.setCurrentItem(2);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        menu.getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
