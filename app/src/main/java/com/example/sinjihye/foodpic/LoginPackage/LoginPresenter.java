package com.example.sinjihye.foodpic.LoginPackage;

import android.content.Context;

public class LoginPresenter implements LoginTask.PresenterBridge, LoginModel.OnLoginListener {

    LoginTask.ViewBridge viewBridge;
    LoginModel loginModel;

    public LoginPresenter(Context context,LoginTask.ViewBridge viewBridge) {
        this.viewBridge = viewBridge;
        loginModel  = new LoginModel(context,this);
        viewBridge.setPresenterBridge(this);
    }


    @Override
    public void login(String email, String pwd) {
        loginModel.login(email, pwd);
    }

    @Override
    public void start() {

    }

    @Override
    public void onResult(boolean isSuccess) {
        viewBridge.loginResult(isSuccess);
    }
}
