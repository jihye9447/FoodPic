package com.example.sinjihye.foodpic.AnalisticPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sinjihye.foodpic.R;
import com.example.sinjihye.foodpic.Utils.TablayoutIndicator;

public class AnalyticsFragment extends Fragment {

    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_analytics, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){

        tabLayout = view.findViewById(R.id.tablayout);
        TablayoutIndicator.wrapTabIndicatorToTitle(tabLayout,80,80);

    }

}

