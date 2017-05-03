package com.joan.ra1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings_Server extends AppCompatActivity implements View.OnClickListener {


    public static String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__server);

        EditText etIp = (EditText) findViewById(R.id.etIp);
        ip = etIp.getText().toString();

        System.out.println(ip);

        Button btAceptar = (Button) findViewById(R.id.btAceptar);
        btAceptar.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Settings_Server.this, AnadirOpinion.class);
        intent.putExtra("ip", ip);
        startActivity(intent);
    }
}
