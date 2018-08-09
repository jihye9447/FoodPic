package com.example.sinjihye.foodpic.AnalisticPackage;

import android.support.annotation.NonNull;

import com.example.sinjihye.foodpic.PojoPackage.ResultUserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class AnalyticsModel implements OnSuccessListener<AuthResult>, OnFailureListener {

    CalcResult calcResult;
    private AnalyticsModel.onCompleteListener onCompleteListener;

    public AnalyticsModel(AnalyticsModel.onCompleteListener onCompleteListener1){
        this.onCompleteListener = onCompleteListener1;
    }


    

    public void updateResult(ResultUserData resultUserData){
        calcResult.setResultUserData(resultUserData);
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        onCompleteListener.onComplete(false);
    }

    @Override
    public void onSuccess(AuthResult authResult) {
        onCompleteListener.onComplete(true);
    }

    interface onCompleteListener{
        void onComplete(Boolean isSuccess);
    }

}
