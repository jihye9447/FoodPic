package com.example.sinjihye.foodpic.AnalisticPackage;

import com.example.sinjihye.foodpic.ListenerPackage.OnResultUserDataListener;
import com.example.sinjihye.foodpic.PojoPackage.ResultUserData;
import com.example.sinjihye.foodpic.PojoPackage.UserData;

public class CalcResult implements OnResultUserDataListener{
    UserData userData;
    ResultUserData resultUserData;
    int calb, protein, fat;
    String waring_msg;
    int basic_kcal;
    int act_kcal;
    float proper_weight;


    @Override
    public void setResultUserData(ResultUserData resultUserData) {

        //firebase에서 정보 읽어와야 하나? userdata.get() 하면 되나?

        //TODo calb비중 비교해서 경고 메세지 작성(String 배열 초이스 해서 쓰는걸로)
        if(calb>=70&&(protein<20||fat<10)){
            waring_msg = "탄수화물 섭취가 너무 많습니다. 탄수화물 섭취를 줄이고 단백질 섭취를 늘리세요.";
        }
        basic_kcal = basic_kcal();
        act_kcal = neededEnergy();
        proper_weight = properWeight();
        resultUserData.setBasic_kcal(basic_kcal);
        resultUserData.setActivation_kcal(act_kcal);
        resultUserData.setActivation_level(userData.getActivity());
        resultUserData.setWarning_msg(waring_msg);
        resultUserData.setProper_weight(proper_weight);


        this.resultUserData = resultUserData;
    }

    public int basic_kcal(){
        //0man 1woman
        if(userData.getGender()==0){
            if(userData.getAge()>0&&userData.getAge()<=29){
                basic_kcal = Math.round(userData.getWeight()*24.0f);
            }else if(userData.getAge()>=30&&userData.getAge()<50){
                basic_kcal = Math.round(userData.getWeight()*22.3f);
            }else if(userData.getAge()>=50&&userData.getAge()<70){
                basic_kcal = Math.round(userData.getWeight()*21.5f);
            }else{
                //잘못된 값 에러메세지 출력
            }
        }else{
            if(userData.getAge()>0&&userData.getAge()<=29){
                basic_kcal = Math.round(userData.getWeight()*22.1f);
            }else if(userData.getAge()>=30&&userData.getAge()<50){
                basic_kcal = Math.round(userData.getWeight()*21.7f);
            }else if(userData.getAge()>=50&&userData.getAge()<70){
                basic_kcal = Math.round(userData.getWeight()*20.7f);
            }else{
                //잘못된 값 에러메세지 출력
            }
        }
        return basic_kcal;
    }

    public int neededEnergy(){
        act_kcal = Math.round(userData.getActivity()* basic_kcal);
        return act_kcal;
    }

    public Float properWeight(){
        proper_weight = (userData.getHeight()/100.f)*(userData.getHeight()/100.0f)*22.0f;
        return proper_weight;
    }
}
