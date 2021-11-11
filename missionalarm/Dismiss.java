package com.example.missionalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Dismiss extends AppCompatActivity {
    TextView dismiss1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dismiss);
        dismiss1=findViewById(R.id.dismiss);
        NotificationManager manager=(NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(Integer.parseInt(Myservice.CHANNEL_ID));
    }
}