package com.example.missionalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




       /* Intent intent = new Intent(MainActivity.this, BroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,23424243, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(1000), pendingIntent);
*/


        requestWindowFeature(Window.FEATURE_NO_TITLE);
   //     this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       getSupportActionBar().hide();
      setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);



        //when app opens first alarm page will b load
        loadFragment(new Alarm());


    }
    //method to load fragment
    private boolean loadFragment(Fragment fragment)
    {
        if(fragment !=null)

        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId())
        {
            case R.id.navigation_alarm:
                fragment=new Alarm();
                break;

            case R.id.navigation_worldclock:
                fragment=new WorldClock();
                break;

            case R.id.navigation_timer:
                fragment=new Timer();
                break;

            case R.id.navigation_stopwatch:
                fragment=new StopWatch();
                break;
        }
        return loadFragment(fragment);
    }
}
