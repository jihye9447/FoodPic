package com.example.sinjihye.foodpic.SplashPackage;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.sinjihye.foodpic.Utils.SharedPreference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SplashModel implements OnSuccessListener<AuthResult>, OnFailureListener {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    interface OnAutoLoginListener{
        void loginResult(boolean isSuccess);
    }

    OnAutoLoginListener onAutoLoginListener;

    public SplashModel(OnAutoLoginListener onAutoLoginListener) {
        this.onAutoLoginListener = onAutoLoginListener;
    }

    public void autoLogin(Context context){
        SharedPreference sharedPreference = new SharedPreference();
        String email =sharedPreference.getValue(context,"email","");
        String pwd = sharedPreference.getValue(context,"pwd","");
        if (email.isEmpty()||pwd.isEmpty()){
            onAutoLoginListener.loginResult(false);
        } else {
            firebaseAuth.signInWithEmailAndPassword(email,pwd)
                    .addOnSuccessListener(this)
                    .addOnFailureListener(this);
        }
    }
    @Override
    public void onSuccess(AuthResult authResult) {
        onAutoLoginListener.loginResult(true);
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        onAutoLoginListener.loginResult(false);
    }

}
