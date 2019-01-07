package com.apolis.lanny.alarm1application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button start, stop;
    int hour, minute;
    AlarmManager alarmMgr;
    Intent receiverIntent;
    PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        editText = findViewById(R.id.editText);
        start = findViewById(R.id.buttonStart);
        stop = findViewById(R.id.buttonStop);
        TimePicker timePicker = findViewById(R.id.simpleTimePicker);

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
//        int hour = 23;
//        int minute = 35;
//        time = timePicker.getCurrentHour().toString() + ":" +timePicker.getCurrentMinute().toString();

        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                int time = 1;
//                Intent intent = new Intent(MainActivity.this, SecActivity.class);
//                // RequestCode - unique code , flag - check point
//                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 123, intent, 0);
//                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(time * 1000), pi);
//                //alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+(time * 1000), pi);

                alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                receiverIntent = new Intent(getApplicationContext(), SecActivity.class);
                alarmIntent = PendingIntent.getActivity(getApplicationContext(), 0, receiverIntent, 0); //The second parameter is unique to this PendingIntent,
                //if you want to make more alarms,
                //make sure to change the 0 to another integer

                Calendar alarmCalendarTime = Calendar.getInstance(); //Convert to a Calendar instance to be able to get the time in milliseconds to trigger the alarm
                alarmCalendarTime.set(Calendar.HOUR_OF_DAY, hour);
                alarmCalendarTime.set(Calendar.MINUTE, minute);
                alarmCalendarTime.set(Calendar.SECOND, 0); //Must be set to 0 to start the alarm right when the minute hits 30


                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, alarmCalendarTime.getTimeInMillis(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS), alarmIntent);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, alarmCalendarTime.getTimeInMillis(), 1000 * 60 * 5, alarmIntent);

            }
        });

        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmMgr!= null) {
                    alarmMgr.cancel(alarmIntent);
                }
            }
        });
    }
}
