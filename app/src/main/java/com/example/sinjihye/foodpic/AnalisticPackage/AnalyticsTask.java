package com.example.sinjihye.foodpic.AnalisticPackage;

import com.example.sinjihye.foodpic.BasePresenterBridge;
import com.example.sinjihye.foodpic.BaseViewBridge;
import com.example.sinjihye.foodpic.PojoPackage.ResultUserData;

public interface AnalyticsTask {

    //view가 presenter에게 데이터 줌.
    interface PresenterBridge extends BasePresenterBridge {
        void save_ResultOeeuserData(ResultUserData resultUserData);

    }

    //presenter가 View 한테 데이터 줌
    interface  ViewBridge extends BaseViewBridge<PresenterBridge>{
        void userData_result(Boolean isSuccess);
    }
}
