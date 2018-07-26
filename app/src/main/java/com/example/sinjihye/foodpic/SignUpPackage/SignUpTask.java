package com.example.sinjihye.foodpic.SignUpPackage;

import com.example.sinjihye.foodpic.BasePresenterBridge;
import com.example.sinjihye.foodpic.BaseViewBridge;

public interface SignUpTask {
    //엑티비티가 할 일 정리.
    //엑티비티가 결과를 어떻게 받을지.
    //View 가 Presenter한테 데이터 줌.
    interface PresenterBridge extends BasePresenterBridge{
        void login();

        void uploadUserData();
    }

    //presenter가 View 한테 데이터 줌
    interface  ViewBridge extends BaseViewBridge<PresenterBridge> {
        Boolean complete();

    }

}
