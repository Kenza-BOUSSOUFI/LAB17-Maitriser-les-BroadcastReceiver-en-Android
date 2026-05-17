package com.example.lab17_maitriser_les_broadcastreceiver_en_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Toast.makeText(context, "Système démarré : L'observateur statique est en veille.", Toast.LENGTH_LONG).show();
        }
    }
}