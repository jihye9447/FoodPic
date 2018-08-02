package com.example.sinjihye.foodpic.AnalisticPackage;

import android.support.annotation.NonNull;

import com.example.sinjihye.foodpic.PojoPackage.ResultUserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class AnalyticsModel implements OnSuccessListener<AuthResult>, OnFailureListener {

    CalcResult calcResult;
    int average_weight;
    private AnalyticsModel.onCompleteListener onCompleteListener;

    public AnalyticsModel(AnalyticsModel.onCompleteListener onCompleteListener1){
        this.onCompleteListener = onCompleteListener1;
    }


    

    public void updateResult(ResultUserData resultUserData){
        calcResult.setResultUserData(resultUserData);
    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    @Override
    public void onSuccess(AuthResult authResult) {

    }

    interface onCompleteListener{
        void onComplete(Boolean isSuccess);
    }

}
