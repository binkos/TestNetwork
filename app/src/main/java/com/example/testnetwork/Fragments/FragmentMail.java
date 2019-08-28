package com.example.testnetwork.Fragments;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.testnetwork.Fragments.Alarm.AlarmReceiver;
import com.example.testnetwork.R;

import java.text.DateFormat;
import java.util.Calendar;

import static android.support.v4.content.ContextCompat.getSystemService;

public class FragmentMail extends Fragment {


    public static FragmentMail context;
    AlarmManager am;
    PendingIntent pi;
    TextView alarmTV;
    Button setAlarmBTN;
    Button canAlarmBTN;



    int hour;
    int min;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mail_fragment_layout, container, false);
        setAlarmBTN = view.findViewById(R.id.setAlarm);
        canAlarmBTN = view.findViewById(R.id.closeAlarm);
        alarmTV = view.findViewById(R.id.AlarmInfo);
        Calendar c = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = (v,h,m)->{
            hour = h;
            min = m;
            alarmTV.setText(h+":"+m);

            Intent intent = new Intent(getContext(),AlarmReceiver.class);
            intent.putExtra("hours",h);
            intent.putExtra("minutes",m);

            pi = PendingIntent.getBroadcast(getContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        };




        setAlarmBTN.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),timeSetListener,c.get(Calendar.HOUR),c.get(Calendar.MINUTE), false);
            timePickerDialog.show();

            Toast.makeText(getContext(),System.currentTimeMillis()+"",Toast.LENGTH_SHORT).show();

            am = (AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC,System.currentTimeMillis()+400,pi);

        });

        canAlarmBTN.setOnClickListener(v -> {
            alarmTV.setText("");
            am.cancel(pi);
        });

        return view;
    }

}
