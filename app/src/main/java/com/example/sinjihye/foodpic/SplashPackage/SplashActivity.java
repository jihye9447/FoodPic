package com.example.sinjihye.foodpic.SplashPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sinjihye.foodpic.LoginPackage.LoginActivity;
import com.example.sinjihye.foodpic.MainActivity;
import com.example.sinjihye.foodpic.R;

public class SplashActivity extends AppCompatActivity implements SplashTask.ViewBridge{

    SplashPresenter splashPresenter;
    SplashTask.PresenterBride presenterBride;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashPresenter = new SplashPresenter(this);
        setContentView(R.layout.activity_splash);
        presenterBride.autoLogin(this);
    }

    @Override
    public void loginResult(boolean isSuccess) {
        if(isSuccess){
            intent(MainActivity.class);
        }else{
            intent(LoginActivity.class);
        }
    }

    @Override
    public void setPresenterBridge(SplashTask.PresenterBride presenterBridge) {
        this.presenterBride = presenterBridge;
    }
    private <T>void intent(Class<T> targetActivity){
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
        finish();
    }
}
