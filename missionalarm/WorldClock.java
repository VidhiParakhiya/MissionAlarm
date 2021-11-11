package com.example.missionalarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class WorldClock extends Fragment {
Calendar current;
Spinner spinner;
TextView txttimezone,timezone,textcureenttime;
long miliseconds;
ArrayAdapter<String> idAdapter;
SimpleDateFormat sdf;
Date resultDate;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.activity_world_clock,null);
        View v = inflater.inflate(R.layout.activity_world_clock, container, false);
        spinner = v.findViewById(R.id.spinner);
        timezone=v.findViewById(R.id.timezone);
        textcureenttime = v.findViewById(R.id.textView);
        txttimezone=v.findViewById(R.id.txttimezone);
        String [] idArray= TimeZone.getAvailableIDs();
        sdf=new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");
        idAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,idArray);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(idAdapter);
        getGMTtime();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getGMTtime();
                String selectedID=(String) (parent.getItemAtPosition(position));
                TimeZone timeZone=TimeZone.getTimeZone(selectedID);
                String TimeZoneName=timeZone.getDisplayName();
                int TimeZoneOffset=timeZone.getRawOffset() / (60*1000);
                int hrs=TimeZoneOffset / 60;
                int mins=TimeZoneOffset % 60;
                miliseconds=miliseconds+timeZone.getRawOffset();
                resultDate=new Date(miliseconds);
                System.out.println(sdf.format(resultDate));
                timezone.setText(TimeZoneName +":GMT"+ hrs+":"+mins);
                txttimezone.setText(""+sdf.format(resultDate));
                miliseconds=0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;

    }
    private void getGMTtime()
    {
        current=Calendar.getInstance();
        textcureenttime.setText(""+current.getTime());
        miliseconds=current.getTimeInMillis();
        TimeZone tzcurrent=current.getTimeZone();
        int offset=tzcurrent.getRawOffset();
        if(tzcurrent.inDaylightTime(new Date()))
        {
            offset=offset+tzcurrent.getDSTSavings();
        }
        miliseconds=miliseconds-offset;
        resultDate=new Date(miliseconds);
        System.out.println(sdf.format(resultDate));
    }
}