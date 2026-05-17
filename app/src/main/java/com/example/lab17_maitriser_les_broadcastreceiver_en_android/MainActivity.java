package com.example.lab17_maitriser_les_broadcastreceiver_en_android;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FlightModeObserver flightModeObserver;
    private boolean isMonitoringActive = false;
    
    private Button toggleMonitorBtn;
    private Button emitBroadcastBtn;
    private TextView statusIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flightModeObserver = new FlightModeObserver();
        
        statusIndicator = findViewById(R.id.statusIndicator);
        toggleMonitorBtn = findViewById(R.id.toggleMonitorBtn);
        emitBroadcastBtn = findViewById(R.id.emitBroadcastBtn);

        toggleMonitorBtn.setOnClickListener(v -> handleMonitorToggle());
        emitBroadcastBtn.setOnClickListener(v -> triggerCustomEvent());
    }

    private void handleMonitorToggle() {
        if (!isMonitoringActive) {
            // Configuration du filtre pour l'action système
            IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            
            // Enregistrement dynamique du receiver
            registerReceiver(flightModeObserver, filter);
            
            isMonitoringActive = true;
            statusIndicator.setText("Statut : Surveillance active (Dynamique)");
            toggleMonitorBtn.setText("Arrêter Surveillance");
        } else {
            // Désenregistrement pour libérer les ressources
            unregisterReceiver(flightModeObserver);
            
            isMonitoringActive = false;
            statusIndicator.setText("Statut : Non actif");
            toggleMonitorBtn.setText("Activer Surveillance Avion");
        }
    }

    private void triggerCustomEvent() {
        Intent intent = new Intent(InternalEventReceiver.CUSTOM_ACTION);
        intent.putExtra("payload", "Message envoyé depuis l'interface utilisateur !");
        
        // Envoi du broadcast (limité à l'app via exported=false dans le manifest)
        sendBroadcast(intent);
        
        Toast.makeText(this, "Événement personnalisé déclenché", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Sécurité : on désinscrit le receiver si l'activité est détruite
        if (isMonitoringActive) {
            unregisterReceiver(flightModeObserver);
            isMonitoringActive = false;
        }
    }
}