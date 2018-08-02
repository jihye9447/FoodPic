package com.example.sinjihye.foodpic.LoginPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sinjihye.foodpic.MainActivity;
import com.example.sinjihye.foodpic.R;
import com.example.sinjihye.foodpic.SignUpPackage.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginTask.ViewBridge {

    EditText emailEdit;
    EditText pwdEdit;
    Button login;
    Button join;
    LoginTask.PresenterBridge presenterBridge;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter(this,this);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        emailEdit = findViewById(R.id.email);
        pwdEdit = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        join = findViewById(R.id.signup);

        login.setOnClickListener(this);
        join.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String email = emailEdit.getText().toString().replace(" ","");
                String pwd = pwdEdit.getText().toString().replace(" ","");
                if(email.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(this, "빈곳을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    presenterBridge.login(email,pwd);
                }

                break;
            case R.id.signup:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void loginResult(boolean isSusccess) {
        if(isSusccess)
        {
            intent(MainActivity.class);
        }else{
            Toast.makeText(this, "로그인 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setPresenterBridge(LoginTask.PresenterBridge presenterBridge) {
        this.presenterBridge = presenterBridge;
    }

    private <T>void intent(Class<T> targetActivity){
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
        finish();
    }
}
