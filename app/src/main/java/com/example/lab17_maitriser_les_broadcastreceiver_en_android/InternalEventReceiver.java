package com.example.lab17_maitriser_les_broadcastreceiver_en_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InternalEventReceiver extends BroadcastReceiver {
    public static final String CUSTOM_ACTION = "com.example.lab17.INTERNAL_NOTIFICATION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (CUSTOM_ACTION.equals(intent.getAction())) {
            String data = intent.getStringExtra("payload");
            Toast.makeText(context, "Notification Interne : " + data, Toast.LENGTH_SHORT).show();
        }
    }
}