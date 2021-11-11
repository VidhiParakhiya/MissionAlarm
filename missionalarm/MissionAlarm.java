package com.example.missionalarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MissionAlarm extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         CardView shake,question,news,gallery;
        TextView back;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_set_alarm, container, false);

        View v = inflater.inflate(R.layout.activity_mission_alarm, container, false);
        shake = v.findViewById(R.id.shake);
        news=v.findViewById(R.id.news);
        question = v.findViewById(R.id.question);
        back = v.findViewById(R.id.back);
        gallery = v.findViewById(R.id.gallery);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetAlarm setAlarm=new SetAlarm();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,setAlarm);
                transaction.commit();
            }
        });



        shake.setOnClickListener(new View.OnClickListener() {
            //on click button navigate to next fragment of page.
            public void onClick(View v) {
                Shake_Notify shake_notify=new Shake_Notify();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                //change from main activity frame layout ID name to new fragment.
                transaction.replace(R.id.fragment_container,shake_notify);
                transaction.commit();

            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question_Notify question_notify=new Question_Notify();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,question_notify);
                transaction.commit();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery_notify gallery_notify=new gallery_notify();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,gallery_notify);
                transaction.commit();
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   String url = CardView.toString();
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.google.com"));
               // startActivity(intent);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/topstories?hl=en-IN&gl=IN&ceid=IN:en")));
            }
        });
        return v;

    }

}