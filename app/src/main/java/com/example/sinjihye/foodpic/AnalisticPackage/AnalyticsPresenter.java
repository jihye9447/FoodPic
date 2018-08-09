package com.example.sinjihye.foodpic.AnalisticPackage;

import com.example.sinjihye.foodpic.PojoPackage.ResultUserData;

public class AnalyticsPresenter implements AnalyticsTask.PresenterBridge, AnalyticsModel.onCompleteListener{

    AnalyticsTask.ViewBridge viewBridge;
    AnalyticsModel analyticsModel = new AnalyticsModel(this);

    public AnalyticsPresenter(AnalyticsTask.ViewBridge viewBridge) {
        this.viewBridge = viewBridge;
        viewBridge.setPresenterBridge(this);
    }

    @Override
    public void start() {

    }




    @Override
    public void onComplete(Boolean isSuccess) {
        viewBridge.userData_result(isSuccess);
    }

    @Override
    public void save_ResultOeeuserData(ResultUserData resultUserData) {
        analyticsModel.updateResult(resultUserData);
    }
}
