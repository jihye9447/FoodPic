package com.example.sinjihye.foodpic.SplashPackage;

import android.content.Context;

public class SplashPresenter implements SplashTask.PresenterBride, SplashModel.OnAutoLoginListener {

    SplashTask.ViewBridge viewBridge;
    SplashModel splashModel = new SplashModel(this);

    public SplashPresenter(SplashTask.ViewBridge viewBridge) {
        this.viewBridge = viewBridge;
        viewBridge.setPresenterBridge(this);
    }

    @Override
    public void autoLogin(Context context) {
        splashModel.autoLogin(context);
    }

    @Override
    public void start() {

    }

    @Override
    public void loginResult(boolean isSuccess) {
        viewBridge.loginResult(isSuccess);
    }
}
