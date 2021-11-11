package com.example.missionalarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static android.os.Build.ID;

public class SetAlarm<dateString> extends Fragment  {
    private EditText text1,text2;
  private TimePicker timepickerr;
    private Button chanel1;
    TextView missionalarmbtn,repeatt;
    TextView ring;
    private TextView mTextView;
    Button setAlarmm;
    CheckBox vib_chck;
    String timeText;
    DatePickerDialog.OnDateSetListener setListener;
    final static int RQS_1 = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.fragment_alarm,null);

        View v = inflater.inflate(R.layout.activity_set_alarm, container, false);
        ring =v.findViewById(R.id.ringtone);
repeatt=v.findViewById(R.id.repeat);
        mTextView = v.findViewById(R.id.textView);
        vib_chck=v.findViewById(R.id.vibrate_chck);

        missionalarmbtn = v.findViewById(R.id.missionalarm);

        setAlarmm = v.findViewById(R.id.SETALARM);        //when button is clicked you set some action
        mTextView=v.findViewById(R.id.textView);

        timepickerr = v.findViewById(R.id.timepicker);
        timepickerr.setIs24HourView(false);

        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.FOREGROUND_SERVICE}, PackageManager.PERMISSION_GRANTED);//it is used for permission whenever the user runs app if permission is not there than it must be ,it will ask user to give permision when first time app is run

        final Intent intent = new Intent(getContext(), Myservice.class);
     //   Servicecaller(intent);        //whenever we open alarm ring starts


        Calendar now = Calendar.getInstance();

       /* DatePicker.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);
*/

        timepickerr.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        timepickerr.setCurrentMinute(now.get(Calendar.MINUTE));

        setAlarmm.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {



            Calendar current = Calendar.getInstance();
            Calendar cal = Calendar.getInstance();
            cal.set(/*DatePicker.getYear(),
                    DatePicker.getMonth(),
                    DatePicker.getDayOfMonth(),*/
                    timepickerr.getCurrentHour(),
                    timepickerr.getCurrentMinute(),
                    00);



            if(cal.compareTo(current) <= 0){
                //The set Date/Time already passed
                Toast.makeText(getContext(),
                        "Invalid Date/Time",
                        Toast.LENGTH_LONG).show();
            }else{
                setAlarm(cal);
            }

            Servicecaller(intent);


        }
    });






        missionalarmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MissionAlarm missionAlarm = new MissionAlarm();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, missionAlarm);
                transaction.commit();

            }
        });

vib_chck.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

        // Setting ringtone
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, "ringtone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI, "ringtone");
                startActivityForResult(intent , 1);

            }
        });


        repeatt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
               int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mTextView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                Calendar now = Calendar.getInstance();


            }
        });


        return v;


    }

    private void setAlarm(Calendar targetCal) {
        mTextView.setText("\n\n***\n"
                + "Alarm is set@ " + targetCal.getTime() + "\n"
                + "***\n");

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    Parcelable ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

                    // Toast.makeText(getBaseContext(),RingtoneManager.URI_COLUMN_INDEX,
                    // Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    }




    //service method is called
    @RequiresApi(api = Build.VERSION_CODES.M)
    private  void Servicecaller(Intent intent){
        String ampm;
        getActivity().stopService(intent);              //to stop the alarm tune of service
        Integer alarmHour= timepickerr.getCurrentHour();
        Integer alrmminute=timepickerr.getCurrentMinute();// set value of hour minute to intent
        intent.putExtra("alarmHour",alarmHour);
        intent.putExtra("alrmminute",alrmminute);


        if(alarmHour >12)
        { ampm= "PM";
        }
        else
        {
            ampm="AM";
        }

        mTextView.setText(alarmHour+":"+alrmminute+ ""+ampm);
        getActivity().startService(intent);             //to start the service



    }




}






