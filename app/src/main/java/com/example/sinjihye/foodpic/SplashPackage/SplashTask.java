package com.example.sinjihye.foodpic.SplashPackage;

import android.content.Context;

import com.example.sinjihye.foodpic.BasePresenterBridge;
import com.example.sinjihye.foodpic.BaseViewBridge;

public interface SplashTask {
    interface PresenterBride extends BasePresenterBridge{
        void autoLogin(Context context);
    }
    interface ViewBridge extends BaseViewBridge<PresenterBride>{
        void loginResult(boolean isSuccess);
    }
}
