package com.example.sinjihye.foodpic.SignUpPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sinjihye.foodpic.FragAdapter;
import com.example.sinjihye.foodpic.ListenerPackage.OnUserDataListener;
import com.example.sinjihye.foodpic.MainActivity;
import com.example.sinjihye.foodpic.PojoPackage.UserData;
import com.example.sinjihye.foodpic.R;

public class SignUpActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnUserDataListener, View.OnClickListener, SignUpTask.ViewBridge{

    android.support.v7.widget.Toolbar toolbar;
    ViewPager viewPager;
    FragAdapter adapter1;
    TextView button;
    ImageView backbtn;
    UserData userData = new UserData();
    SignUp1Fragment signUp1Fragment = new SignUp1Fragment();
    SignUp2Fragment signUp2Fragment = new SignUp2Fragment();
    Boolean save_complete;
    SignUpTask.PresenterBridge presenterBridge;
    SignUpPresenter signUpPresenter;

    /*TODO List
    * 1.인터페이스 작성 -> 목적: 이 해당 엑티비티가 어떤 일을 할건지 전부 정리. 메소드로서
    * ex) 회원가입 처리 / 데이터베이스에 회원 정보 기입
    * 2.SignUpPresenterClass를 만들어준다.
    * 3.1번에서 만들어준 각각의 인터페이스를 프리젠터 브릿지는 프리젠터가 채택하고, 뷰브릿지는 엑티비티가 채택
    * (각각의 계획을 서로 가지고 있는 개념)
    * 4.각각의 계획을 서로에게 전달하여 둘 클래스간에 커넥션을 만든다.
    * 5.4번의 방법은 프리젠터에서 생성자를 통해 뷰브릿지를 받아오고, 뷰브릿지를 통해서 자신이 가지고 있는 프리젠터 브릿지를 넘겨준다.
    * 6.5번 중에서 뷰브릿지를 통해서 프리젠터브릿지를 넘기는 방법은 뷰브릿지가 베이스 뷰브릿지를 상속받고있기 때문에 베이스 뷰브릿지의 계획
    * ->프리젠터 브릿지를 넘겨주는 용도를 통해서 넘겨준다. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpPresenter = new SignUpPresenter(this);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar2);
        viewPager = findViewById(R.id.view_pager);
        button = findViewById(R.id.button);
        adapter1 = new FragAdapter(getSupportFragmentManager());
        signUp1Fragment.setOnUserDataListener(this);
        signUp2Fragment.setOnUserDataListener(this);
        adapter1.addFragment(signUp1Fragment);
        adapter1.addFragment(signUp2Fragment);
        viewPager.setAdapter(adapter1);
        viewPager.addOnPageChangeListener(this);
        viewPager.beginFakeDrag();
        backbtn = findViewById(R.id.back_btn);


        setSupportActionBar(toolbar);
        button.setOnClickListener(this);
        backbtn.setOnClickListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void setUserData(int type, UserData userData) {
        switch (type){
            case 0:
                this.userData.setEmail(userData.getEmail());
                this.userData.setPwd(userData.getPwd());
                break;
            case 1:
                this.userData.setAge(userData.getAge());
                this.userData.setGender(userData.getGender());
                this.userData.setWeight(userData.getWeight());
                this.userData.setHeight(userData.getHeight());
                this.userData.setActivity(userData.getActivity());
                //TODO 데이터 통신 코드 작성.
                presenterBridge.login(userData);
                //fixme 이 메소드 어디어디 수정하기.
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back_btn:
                if(viewPager.getCurrentItem()==0){
                    finish();
                }else{
                    button.setText("다음");
                    viewPager.setCurrentItem(0,true);

                }
                break;
            case R.id.button:
                if(viewPager.getCurrentItem()==1){
                    button.setText("완료");
                    viewPager.setCurrentItem(1,true);
                    signUp1Fragment.sendUserData();

                }else{

                    signUp2Fragment.sendUserData();
                }
                break;
        }
    }


    @Override
    public void complete(boolean isSuccess) {
        if(isSuccess){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "회원가입에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setPresenterBridge(SignUpTask.PresenterBridge presenterBridge) {

        this.presenterBridge = presenterBridge;
    }
}
