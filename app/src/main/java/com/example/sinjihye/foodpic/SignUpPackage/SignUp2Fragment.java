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
import android.widget.Toast;

import com.example.sinjihye.foodpic.ListenerPackage.OnUserDataListener;
import com.example.sinjihye.foodpic.PojoPackage.UserData;
import com.example.sinjihye.foodpic.R;

public class SignUp2Fragment extends android.support.v4.app.Fragment{

    View second_signUp_view;
    TextView user_age, user_weight, user_heght;
    RadioButton man, woman, walking, breathing, exercising;
    RadioGroup gender, activation;
    int user_gender;
    float user_activation;
    OnUserDataListener onUserDataListener;

    public void setOnUserDataListener(OnUserDataListener onUserDataListener) {
        this.onUserDataListener = onUserDataListener;
    }

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
        activation.setOnCheckedChangeListener(radioGroupChangeListener2);

    }

    RadioGroup.OnCheckedChangeListener radioGroupChangeListener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if (checkedId == R.id.breathing) {
                user_activation = 1.5f;
            } else if (checkedId == R.id.walking) {
                user_activation = 1.75f;
            } else if (checkedId == R.id.exercising) {
                user_activation = 2;
            }else{
                //에러메세지
            }

        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupChangeListener = new RadioGroup.OnCheckedChangeListener() {

        String temp_string = "";

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            //String 값을 presenter 로 보내기.

            if (checkedId == R.id.man) {
                temp_string = String.valueOf(man);
                user_gender = 0;
            } else if (checkedId == R.id.woman) {
                temp_string = String.valueOf(woman);
                user_gender = 1;
            } else {
                //에러메세지 출
            }

        }
    };

    public void sendUserData() {

        String age = user_age.getText().toString();
        String gender = String.valueOf(user_gender);
        String weight = user_weight.getText().toString();
        String height = user_heght.getText().toString();
        String activation = String.valueOf(user_activation);

        if (age.isEmpty() || gender.isEmpty() || weight.isEmpty() || height.isEmpty() || activation.isEmpty()) {
            Toast.makeText(getActivity(), "입력되지 않은 사항이 있습니다.", Toast.LENGTH_SHORT).show();
            /*fixme
             * aeg,gender, weight, height, activation 별로 toast 해주기
             * switch case로 할 수 있나?*/
        } else {

            int u_age = Integer.parseInt(age);
            int u_gender = user_gender;
            float u_weight = Float.parseFloat(weight);
            float u_height = Float.parseFloat(height);
            float u_activation = user_activation;

            UserData userData = new UserData();
            userData.setAge(u_age);
            userData.setGender(u_gender);
            userData.setWeight(u_weight);
            userData.setHeight(u_height);
            userData.setActivity(u_activation);
            onUserDataListener.setUserData(1, userData);

        }

    }

}
