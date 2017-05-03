package com.joan.ra1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class Registrado extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrado);
        NotificationCompat.Builder nBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(Registrado.this)
                .setContentTitle("Usuario registrado correctamente!")
                .setContentText("Ahora puedes iniciar sesión dándole a esta notificación :P")
                .setSmallIcon(R.drawable.default_marker)
                .setContentIntent(PendingIntent.getActivity(Registrado.this, (int) System.currentTimeMillis(),
                        new Intent(Registrado.this, Login.class), 0));

        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(0, nBuilder.build());
        Button btAdelante = (Button) findViewById(R.id.btAdelante);
        btAdelante.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Registrado.this, Login.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed (){
        if(false)
            super.onBackPressed();

    }

}
