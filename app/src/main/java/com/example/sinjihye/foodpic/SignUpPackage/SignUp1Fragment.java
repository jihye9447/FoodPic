package com.example.sinjihye.foodpic.SignUpPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sinjihye.foodpic.ListenerPackage.OnUserDataListener;
import com.example.sinjihye.foodpic.PojoPackage.UserData;
import com.example.sinjihye.foodpic.R;

public class SignUp1Fragment extends android.support.v4.app.Fragment {

    View first_signUp_view;
    ImageView view_hide;
    TextView user_email, user_pw, check_pw;
    boolean isPwVisible = false;
    OnUserDataListener onUserDataListener;

//    public static SignUp1Fragment getInstance(OnUserDataListener onUserDataListener){
//        SignUp1Fragment signUp1Fragment = new SignUp1Fragment();
//
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("listener",onUserDataListener);
//        signUp1Fragment.setArguments(bundle);
//        return signUp1Fragment;
//    }


    public void setOnUserDataListener(OnUserDataListener onUserDataListener) {
        this.onUserDataListener = onUserDataListener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        onUserDataListener = getArguments().getParcelable("listener");
        View view = inflater.inflate(R.layout.frag_sign_up1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        first_signUp_view = view.findViewById(R.id.user);
        view_hide = view.findViewById(R.id.view_hide);
        user_email = view.findViewById(R.id.user_email);
        user_pw = view.findViewById(R.id.user_pw);
        check_pw = view.findViewById(R.id.check_pw);
        Glide.with(this).load(R.drawable.hide).into(view_hide);
        view_hide.setOnClickListener(onClickListener);

    }

    Button.OnClickListener onClickListener = new View.OnClickListener(){

        public void onClick(View v){
            if(!isPwVisible){
                Glide.with(v).load(R.drawable.view).into(view_hide);
                user_pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                check_pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                isPwVisible = true;
            }else{
                Glide.with(v).load(R.drawable.hide).into(view_hide);
                user_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                check_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                isPwVisible = false;
            }

        }

    };

    public void sendUserData(){
        String email = user_email.getText().toString().replace(" ","");
        String pwd = user_pw.getText().toString().replace(" ","");
        String pwdCheck = check_pw.getText().toString().replace(" ","");
        if(email.isEmpty()||pwd.isEmpty()||pwdCheck.isEmpty()){
            Toast.makeText(getActivity(), "공백을 확인해주세요.", Toast.LENGTH_SHORT).show();
        }else{
            if(!iseEmailValid(email)) {
                Toast.makeText(getActivity(), "이메일 양식을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }else{
                if(!pwd.equals(pwdCheck)){
                    Toast.makeText(getActivity(), "비밀번호 일치 여부를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    UserData userData = new UserData();
                    userData.setEmail(email);
                    userData.setPwd(pwd);
                    onUserDataListener.setUserData(0, userData);
                }
            }
        }


    }

    private boolean iseEmailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
