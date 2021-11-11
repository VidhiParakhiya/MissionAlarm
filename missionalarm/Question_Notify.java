package com.example.missionalarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class Question_Notify extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CardView maths_que,science_que,gk_que,it_que;

        View v = inflater.inflate(R.layout.activity_question__notify, container, false);
        science_que = v.findViewById(R.id.science_que);
        maths_que = v.findViewById(R.id.maths_que);
        gk_que= v.findViewById(R.id.gk_que);
        it_que = v.findViewById(R.id.it_que);

        maths_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Maths_que Maths_que=new Maths_que();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,Maths_que);
                transaction.commit();
            }
        });

 science_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Science_que Science_que=new Science_que();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,Science_que);
                transaction.commit();
            }
        });

 gk_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gk_que Gk_que=new Gk_que();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,Gk_que);
                transaction.commit();
            }
        });

 it_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                It_que It_que=new It_que();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,It_que);
                transaction.commit();
            }
        });


        return v;

    }


}