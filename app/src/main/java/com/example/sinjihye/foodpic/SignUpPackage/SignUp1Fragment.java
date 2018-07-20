package com.example.sinjihye.foodpic.SignUpPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sinjihye.foodpic.R;

public class SignUp1Fragment extends android.support.v4.app.Fragment {

    View first_signUp_view;
    ImageView view_hide;
    TextView user_email, user_pw, check_pw;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_sign_up1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        first_signUp_view = view.findViewById(R.id.user);
        view_hide = view.findViewById(R.id.view_hide);
        view_hide.setOnClickListener(onClickListener);
        user_email = view.findViewById(R.id.user_email);
        user_pw = view.findViewById(R.id.user_pw);
        check_pw = view.findViewById(R.id.check_pw);
        user_pw.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        user_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        check_pw.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        check_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());

        Glide.with(this).load(R.drawable.hide).into(view_hide);

    }

    Button.OnClickListener onClickListener = new View.OnClickListener(){
        public void onClick(View v){
            switch (v.getId()){
                case R.drawable.hide:
                    Glide.with(v).load(R.drawable.view).into(view_hide);
                    user_pw.setInputType(InputType.TYPE_CLASS_TEXT);
                    check_pw.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case R.drawable.view:
                    Glide.with(v).load(R.drawable.hide).into(view_hide);
                    user_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    check_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    break;
            }

        }

    };
}
