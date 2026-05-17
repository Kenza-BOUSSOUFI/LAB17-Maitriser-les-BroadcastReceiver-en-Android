package com.example.lab17_maitriser_les_broadcastreceiver_en_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FlightModeObserver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            boolean active = intent.getBooleanExtra("state", false);
            
            String info = active 
                ? "Connectivité coupée : Mode Avion ON" 
                : "Connectivité rétablie : Mode Avion OFF";
            
            Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
        }
    }
}