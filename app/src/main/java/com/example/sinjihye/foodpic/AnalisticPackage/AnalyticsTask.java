package com.example.sinjihye.foodpic.AnalisticPackage;

import com.example.sinjihye.foodpic.BasePresenterBridge;
import com.example.sinjihye.foodpic.BaseViewBridge;

public interface AnalyticsTask {

    //view가 presenter에게 데이터 줌.
    interface PresenterBridge extends BasePresenterBridge {
        void save_ResultOfuserData();

    }

    //presenter가 View 한테 데이터 줌
    interface  ViewBridge extends BaseViewBridge<PresenterBridge>{
        void userData_result(Boolean isSuccess);
    }
}
