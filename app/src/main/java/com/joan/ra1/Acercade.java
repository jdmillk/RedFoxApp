package com.joan.ra1;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Acercade extends AppCompatActivity implements View.OnClickListener{
    boolean seleccionado = false;
    int posicion = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);

        //String idiomas[]={"-------", "Español", "English", "Català"};
        String language = getResources().getString(R.string.lb_language);
        List<String> idioma = new ArrayList<>();
        idioma.add("-------");
        idioma.add("Español");
        idioma.add("English");
        idioma.add("Català");

        Spinner spIdioma = (Spinner) findViewById(R.id.spIdioma);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, idioma);
        spIdioma.setAdapter(adapter);

        posicion = spIdioma.getSelectedItemPosition();

        spIdioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(i);
                System.out.println(posicion);
                if(posicion != i){
                    if(i == 2) {
                        Locale locale = new Locale("en");
                        Resources res = getResources();
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, null);
                        seleccionado = false;
                        Intent refresh = new Intent(Acercade.this, MainActivity.class);
                        startActivity(refresh);
                        finish();
                        //Toast.makeText(this, "English Language!", Toast.LENGTH_LONG).show();
                    }
                    if(i == 1){
                        Locale locale = new Locale("es");
                        Resources res = getResources();
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, null);
                        seleccionado = false;
                        Intent refresh = new Intent(Acercade.this, MainActivity.class);
                        startActivity(refresh);
                        finish();
                    }
                    if(i == 3){
                        Locale locale = new Locale("ca");
                        Resources res = getResources();
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, null);
                        seleccionado = false;
                        Intent refresh = new Intent(Acercade.this, MainActivity.class);
                        startActivity(refresh);
                        finish();
                    }
                }

                posicion = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Button btOpinion = (Button) findViewById(R.id.btOpinion);
        //spIdioma.setOnItemSelectedListener(this);
        btOpinion.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Acercade.this, ListadoOpiniones.class);
        startActivity(intent);
    }

    /*@Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spIdioma = (Spinner) findViewById(R.id.spIdioma);
        seleccionado = true;
        if(seleccionado) {
            if (spIdioma.getSelectedItem().equals("Español")) {
                Locale myLocale = new Locale("es");
                Resources res = getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                Configuration conf = res.getConfiguration();
                conf.locale = myLocale;
                res.updateConfiguration(conf, dm);
                seleccionado = false;
                Intent refresh = new Intent(Acercade.this, Acercade.class);
                startActivity(refresh);
                finish();

                Toast.makeText(this, "Idioma Español!", Toast.LENGTH_LONG).show();
            } else {
                Locale myLocale = new Locale("en");
                Resources res = getResources();
                DisplayMetrics dm = res.getDisplayMetrics();
                Configuration conf = res.getConfiguration();
                conf.locale = myLocale;
                res.updateConfiguration(conf, dm);
                seleccionado = false;
                Intent refresh = new Intent(Acercade.this, Acercade.class);
                startActivity(refresh);
                finish();
                Toast.makeText(this, "English Language!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    */
}
