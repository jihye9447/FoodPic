package com.example.sinjihye.foodpic.SignUpPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sinjihye.foodpic.R;

public class SignUp2Fragment extends android.support.v4.app.Fragment{

    View second_signUp_view;
    TextView user_age, user_weight, user_heght;
    RadioButton man, woman, walking, breathing, exercising;
    RadioGroup gender, activation;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_sign_up2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        second_signUp_view = view.findViewById(R.id.user_info);
        user_age = view.findViewById(R.id.user_age);
        user_heght = view.findViewById(R.id.user_height);
        user_weight = view.findViewById(R.id.user_weight);
        man = view.findViewById(R.id.man);
        woman = view.findViewById(R.id.woman);
        breathing = view.findViewById(R.id.breathing);
        walking = view.findViewById(R.id.walking);
        exercising = view.findViewById(R.id.exercising);
        gender = view.findViewById(R.id.gender);
        activation = view.findViewById(R.id.my_activation);
        gender.setOnCheckedChangeListener(radioGroupChangeListener);
        activation.setOnCheckedChangeListener(radioGroupChangeListener);

        }

    RadioGroup.OnCheckedChangeListener radioGroupChangeListener = new RadioGroup.OnCheckedChangeListener() {

        String temp_string ="";
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            //String 값을 presenter 로 보내기.

            if(checkedId == R.id.man){
                temp_string = String.valueOf(man);
            }else if(checkedId == R.id.woman){
                temp_string = String.valueOf(woman);
            }else if(checkedId == R.id.breathing){
                temp_string = String.valueOf(breathing);
            }else if(checkedId == R.id.walking){
                temp_string = String.valueOf(walking);
            }else if(checkedId == R.id.exercising){
                temp_string = String.valueOf(exercising);
            }else{
               //에러메세지 출
            }

        }
    };


}
