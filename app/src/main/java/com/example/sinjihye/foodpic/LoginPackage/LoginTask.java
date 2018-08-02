package com.example.sinjihye.foodpic.LoginPackage;

import com.example.sinjihye.foodpic.BasePresenterBridge;
import com.example.sinjihye.foodpic.BaseViewBridge;

public interface LoginTask {
    interface PresenterBridge extends BasePresenterBridge{
        void login(String email, String pwd);
    }

    interface ViewBridge extends BaseViewBridge<PresenterBridge>{
        void loginResult(boolean isSusccess);
    }
}
