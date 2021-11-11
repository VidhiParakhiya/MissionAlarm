package com.example.missionalarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

import static android.app.AlarmManager.RTC_WAKEUP;
import static java.util.Calendar.getInstance;

public class Target_Alarm_Ring extends AppCompatActivity {
    Button cancel1,dismissed;
    public Ringtone ringtone;

    TextView snoozed,current_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target__alarm__ring);
        dismissed= findViewById(R.id.dismiss);
        snoozed=findViewById(R.id.snooze);
        AnimateBell();

        dismissed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Myservice.class);
                stopService(intent);


            }

        });


        snoozed.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Myservice.class);// stop alarm
                stopService(intent);
                /*
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        1000 * 60 * 20, alarmIntent);
*/

            }
        });

    }

    private void AnimateBell() {

        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shakeanimation);
        ImageView imgBell= (ImageView) findViewById(R.id.imgBell);
        imgBell.setImageResource(R.drawable.ic_alarmmring);
        imgBell.setAnimation(shake);
    }


}