package com.example.sinjihye.foodpic.SignUpPackage;

public class SignUpPresenter implements SignUpTask.PresenterBridge{

    SignUpTask.ViewBridge viewBridge;

    public SignUpPresenter(SignUpTask.ViewBridge viewBridge) {
        this.viewBridge = viewBridge;
        viewBridge.setPresenterBridge(this);
    }

    @Override
    public void login() {

    }

    @Override
    public void uploadUserData() {

    }

    @Override
    public void start() {

    }
}
