package com.example.sinjihye.foodpic.LoginPackage;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.sinjihye.foodpic.Utils.SharedPreference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel implements OnSuccessListener<AuthResult>, OnFailureListener {
    OnLoginListener onLoginListener;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String email,pwd;
    Context context;

    public LoginModel(Context context,OnLoginListener onLoginListener) {
        this.context=context;
        this.onLoginListener = onLoginListener;
    }

    @Override
    public void onSuccess(AuthResult authResult) {
        onLoginListener.onResult(true);
        SharedPreference sharedPreference =new SharedPreference();
        sharedPreference.put(context,"email",email);
        sharedPreference.put(context,"pwd",pwd);
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        onLoginListener.onResult(false);
    }

    interface OnLoginListener{
        void onResult(boolean isSuccess);
    }

    public void login(String email, String pwd){
        this.email =email;
        this.pwd =pwd;
        firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnSuccessListener(this).addOnFailureListener(this);
    }
}
