package com.example.sinjihye.foodpic.AnalisticPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sinjihye.foodpic.PojoPackage.ResultUserData;
import com.example.sinjihye.foodpic.R;
import com.example.sinjihye.foodpic.Utils.TablayoutIndicator;

public class AnalyticsFragment extends Fragment implements AnalyticsTask.ViewBridge{

    CalcResult calcResult;
    ResultUserData resultUserData;

    ImageView first_picture;
    ImageView second_picture;
    TextView first_day;
    TextView second_day;
    TextView activation_kcal;
    TextView conference_comment;
    TextView correct_weight;
    TextView resultString;
    String resultOfweight;
    Float proper_weight;
    int act_kcal, basic_kcal;
    Float act_level;
    TextView warning_tips;
    String warning_msg;

    AnalyticsPresenter analyticsPresenter;
    AnalyticsTask.PresenterBridge presenterBridge;


    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        analyticsPresenter = new AnalyticsPresenter(this);
        View view = inflater.inflate(R.layout.frag_analytics, container, false);
        initView(view);
        return view;
    }


    private void initView(View view){

        tabLayout = view.findViewById(R.id.tablayout);
        TablayoutIndicator.wrapTabIndicatorToTitle(tabLayout,80,80);
        conference_comment= view.findViewById(R.id.message);
        activation_kcal= view.findViewById(R.id.actiation_kcal);
        correct_weight= view.findViewById(R.id.edit_weight);
        resultString= view.findViewById(R.id.result_string);
        warning_tips= view.findViewById(R.id.waring_tips);
        first_picture= view.findViewById(R.id.first_pic);
        second_picture= view.findViewById(R.id.second_pic);
        first_day= view.findViewById(R.id.first_day);
        second_day= view.findViewById(R.id.second_day);

//        act_level = resultUserData.getActivation_level();
//        basic_kcal = resultUserData.getBasic_kcal();
//        act_kcal = resultUserData.getActivation_kcal();
//        proper_weight = resultUserData.getProper_weight();
//        resultOfweight = resultUserData.getResult();
//        warning_msg = resultUserData.getWarning_msg();
//
//        activation_kcal.setText(act_kcal);
//        conference_comment.setText("= 기초대사량("+basic_kcal+")x활동레벨("+act_level+")");
//        resultString.setText(resultOfweight);
//        warning_tips.setText(warning_msg);


    }


    @Override
    public void setPresenterBridge(AnalyticsTask.PresenterBridge presenterBridge) {
        this.presenterBridge = presenterBridge;
    }


    @Override
    public void userData_result(Boolean isSuccess) {
        analyticsPresenter.onComplete(isSuccess);
    }
}

