package com.example.sinjihye.foodpic.SignUpPackage;

import com.example.sinjihye.foodpic.PojoPackage.SignUpData;


public class SignUpPresenter implements SignUpTask.PresenterBridge, SignUpModel.OnCompleteListener{

    SignUpTask.ViewBridge viewBridge;
    SignUpModel signUpModel = new SignUpModel(this);

    public SignUpPresenter(SignUpTask.ViewBridge viewBridge) {
        this.viewBridge = viewBridge;
        viewBridge.setPresenterBridge(this);
    }

    @Override
    public void login(SignUpData signUpData) {
        signUpModel.login(signUpData);
    }


    @Override
    public void start() {

    }

    @Override
    public void onComplete(boolean isSuccess) {
        viewBridge.complete(isSuccess);
    }
}
