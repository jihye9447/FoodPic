package com.example.sinjihye.foodpic.LoginPackage;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel implements OnSuccessListener<AuthResult>, OnFailureListener {
    OnLoginListener onLoginListener;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public LoginModel(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }

    @Override
    public void onSuccess(AuthResult authResult) {
        onLoginListener.onResult(true);
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        onLoginListener.onResult(false);
    }

    interface OnLoginListener{
        void onResult(boolean isSuccess);
    }

    public void login(String email, String pwd){
        firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnSuccessListener(this).addOnFailureListener(this);
    }
}
