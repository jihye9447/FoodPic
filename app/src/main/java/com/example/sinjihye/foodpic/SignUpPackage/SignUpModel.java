package com.example.sinjihye.foodpic.SignUpPackage;

import com.example.sinjihye.foodpic.PojoPackage.UserData;

public class SignUpModel {

    private OnCompleteListener onCompleteListener;

    public SignUpModel(SignUpModel.OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    public void login(UserData userData){
        onCompleteListener.onComplete(true);
    }

    interface OnCompleteListener{
        void onComplete(boolean isSuccess);
    }
}
