package com.example.testnetwork.Fragments.Alarm;

import android.app.IntentService;
import android.content.Intent;

public class AlarmService extends IntentService {
    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotofications("Wake up!");
    }

    void sendNotofications(String info){};
}
