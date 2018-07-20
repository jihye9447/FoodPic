package com.example.sinjihye.foodpic.CalendarPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sinjihye.foodpic.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarFragment extends Fragment implements CompactCalendarView.CompactCalendarViewListener {
    CompactCalendarView calendarView;
    TextView dateTxt;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MMM");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_calendar,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){
        calendarView = view.findViewById(R.id.compactcalendar_view);
        calendarView.setListener(this);
        dateTxt = view.findViewById(R.id.date_txt);
        dateTxt.setText(simpleDateFormat.format(new Date()));


    }

    @Override
    public void onDayClick(Date dateClicked) {

    }

    @Override
    public void onMonthScroll(Date firstDayOfNewMonth) {
        dateTxt.setText(simpleDateFormat.format(firstDayOfNewMonth));

    }
}
