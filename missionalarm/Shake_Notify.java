package com.example.missionalarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Objects;

public class Shake_Notify extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final TextView selectedNum,selectedNum2;
        NumberPicker numberPicker ,numberPicker2;

        View v = inflater.inflate(R.layout.activity_shake__notify, container, false);
        selectedNum = v.findViewById(R.id.selectNumber);
        numberPicker = v.findViewById(R.id.numPicker);
        selectedNum2 = v.findViewById(R.id.selectNumber2);
        numberPicker2 = v.findViewById(R.id.numPicker2);



        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setWrapSelectorWheel(true);
        //
        numberPicker2.setMinValue(1);
        numberPicker2.setMaxValue(10);
        numberPicker2.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                selectedNum.setText("TIME LIMIT-"+i1);

            }
        });
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                selectedNum2.setText("SHAKES-"+i1);

            }
        });

        //return inflater.inflate(R.layout.fragment_shake__notify, container, false);
        return v;
    }


    }
