package com.example.missionalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.missionalarm.R.id.cancel_button;
import static com.example.missionalarm.R.id.minutes;
import static com.example.missionalarm.R.id.textView;

public class Myservice extends Service {

    public static final Object CHANNEL_ID2 ="101" ;
    private Integer alarmHour;
    private Integer alarmMinute;
    private String ampm;
    public Ringtone ringtone;
    private Timer t = new Timer();

    public static  final  String CHANNEL_ID ="MyNotificationChannelID";//set notification channel


    @Nullable
    @Override
    //service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        alarmHour = intent.getIntExtra("alarmHour", 0);//keyword should be same as it is in setalarm.java for hour and minute
        alarmMinute = intent.getIntExtra("alrmminute", 0);

        ampm = String.format(String.valueOf(intent.getIntExtra("ampm",0)));


        ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }


        //foregroundservice
        try{
            // notification if bg app is closed thn also it should works
            Intent notificationIntent =new Intent(this,SetAlarm.class);
            PendingIntent pendingIntent= PendingIntent.getActivity(this,0,notificationIntent,0);//used so that if app is closed from backgeound than also notification should come

            //to open new activity when notication is clicked
            Intent intentt=new Intent(getApplicationContext(),Target_Alarm_Ring.class);
            intentt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntentt= PendingIntent.getActivity(getApplicationContext(),1,intentt,PendingIntent.FLAG_ONE_SHOT);

//first action button create objects SNOOZE
            Intent snoozeintent = new Intent(this,Snooze.class);
            snoozeintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent snoozependingintent = PendingIntent.getActivity(this,20,snoozeintent,PendingIntent.FLAG_ONE_SHOT);



//second action button create objects DISMISS
            Intent dismissintent = new Intent(this,Dismiss.class);
            snoozeintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent dismisspendingintent = PendingIntent.getActivity(this,0,dismissintent,PendingIntent.FLAG_ONE_SHOT);

//set notification info
            Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)//set notifications contents
                    .setContentTitle("Upcoming Alarm")
                    .setContentText("Set for "+alarmHour.toString() +" : " +alarmMinute.toString()+" "+ampm.toString())
                    .setSmallIcon(R.drawable.ic_alarm_clock)
                    .setContentIntent(pendingIntent)
                    .addAction(R.drawable.ic_left_arrow,"SNOOZE",snoozependingintent) // set text of button event
                  .addAction(R.drawable.ic_clock,"DISMISS",dismisspendingintent)  // set text of button event
                    .setContentIntent(pendingIntentt) // set pending event do wehn you click on notification new activity shows
                    .setLights(Color.YELLOW,200,200)
                    .setAutoCancel(true)
                    .build();


            startForeground(1,notification); //start the service

            //creating notification channel
            NotificationChannel notificationChannel =new NotificationChannel(CHANNEL_ID,"My alarm clock service", NotificationManager.IMPORTANCE_LOW);// you can keep hih low or default
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);




        }
        catch(Exception e){
                e.printStackTrace();
        }




        //service
        t.scheduleAtFixedRate(new TimerTask() { //to see hour and minute is equal than ringtone should play else ringtone stop
            @Override
            public void run() {   // set hour min value


                if (Calendar.getInstance().getTime().getHours() == alarmHour &&
                        Calendar.getInstance().getTime().getMinutes() == alarmMinute)


                {
                    Myservice.this.ringtone.play();
                }
                else {
                    Myservice.this.ringtone.stop();
                }

            }
        }, 0, 2000);

        return super.onStartCommand(intent, flags, startId);
    }





    @Override
    //on destroy ringtone stops
    public void onDestroy() {
        ringtone.stop();
        t.cancel();
        super.onDestroy();
    }

    private class RINGER_MODE_VIBRATE {
    }


}
