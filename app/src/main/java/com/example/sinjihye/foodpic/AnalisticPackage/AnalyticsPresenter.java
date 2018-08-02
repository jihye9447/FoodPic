package com.example.sinjihye.foodpic.AnalisticPackage;

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
    public void save_ResultOfuserData() {
    }

    @Override
    public void onComplete(Boolean isSuccess) {

    }
}
