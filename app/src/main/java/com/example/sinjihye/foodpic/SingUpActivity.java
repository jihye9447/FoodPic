package com.example.sinjihye.foodpic;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sinjihye.foodpic.SignUpPackage.SignUp1Fragment;
import com.example.sinjihye.foodpic.SignUpPackage.SignUp2Fragment;

public class SingUpActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    android.support.v7.widget.Toolbar toolbar;
    ViewPager viewPager;
    FragAdapter adapter1;
    TextView button;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar2);
        viewPager = findViewById(R.id.view_pager);
        button = findViewById(R.id.button);
        adapter1 = new FragAdapter(getSupportFragmentManager());
        adapter1.addFragment(new SignUp1Fragment());
        adapter1.addFragment(new SignUp2Fragment());
        viewPager.setAdapter(adapter1);
        viewPager.addOnPageChangeListener(this);
        backbtn = findViewById(R.id.back_btn);
        backbtn.setOnClickListener(onClickListener2);

        setSupportActionBar(toolbar);
        button.setOnClickListener(onClickListener);

    }

    TextView.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            button.setText("완료");
            viewPager.setCurrentItem(1);
        }
    };

    ImageView.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            button.setText("다음");
            viewPager.setCurrentItem(0);
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
