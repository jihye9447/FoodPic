package com.example.sinjihye.foodpic.LoginPackage;

public class LoginPresenter implements LoginTask.PresenterBridge, LoginModel.OnLoginListener {

    LoginTask.ViewBridge viewBridge;
    LoginModel loginModel = new LoginModel(this);

    public LoginPresenter(LoginTask.ViewBridge viewBridge) {
        this.viewBridge = viewBridge;
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
