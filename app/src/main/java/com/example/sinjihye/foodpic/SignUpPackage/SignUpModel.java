package com.example.sinjihye.foodpic.SignUpPackage;

import android.support.annotation.NonNull;

import com.example.sinjihye.foodpic.PojoPackage.SignUpData;
import com.example.sinjihye.foodpic.PojoPackage.UserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpModel implements OnSuccessListener<AuthResult>, OnFailureListener {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private OnCompleteListener onCompleteListener;
    private SignUpData signUpData;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firestore.collection("User");

    public SignUpModel(SignUpModel.OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    public void login(SignUpData signUpData) {
        this.signUpData = signUpData;
        firebaseAuth.createUserWithEmailAndPassword(signUpData.getEmail(), signUpData.getPwd())
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
    }

    private void uploadUserData(UserData userData) {
        String uid = firebaseAuth.getCurrentUser().getUid();
        collectionReference.document(uid).set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        onCompleteListener.onComplete(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        onCompleteListener.onComplete(false);
                    }
                });
    }

    @Override
    public void onSuccess(AuthResult authResult) {
        uploadUserData(signUpData.getUserData());
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        onCompleteListener.onComplete(false);
    }

    interface OnCompleteListener {
        void onComplete(boolean isSuccess);
    }
}
