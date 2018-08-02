package com.example.sinjihye.foodpic.SignUpPackage;

import android.content.Context;

import com.example.sinjihye.foodpic.PojoPackage.SignUpData;


public class SignUpPresenter implements SignUpTask.PresenterBridge, SignUpModel.OnCompleteListener{

    SignUpTask.ViewBridge viewBridge;
    SignUpModel signUpModel;

    public SignUpPresenter(Context context,SignUpTask.ViewBridge viewBridge) {
        this.viewBridge = viewBridge;
        signUpModel  = new SignUpModel(context,this);
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
