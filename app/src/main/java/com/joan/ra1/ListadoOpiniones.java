package com.joan.ra1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListadoOpiniones extends AppCompatActivity {
    private final String URL_SERVIDOR = "http://192.168.1.42:8082";
    private OpinionAdapter adapter;
    private List<Opinion> listaOpiniones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_opiniones);

        System.out.println(URL_SERVIDOR);
        listaOpiniones = new ArrayList<Opinion>();
        adapter = new OpinionAdapter(this, R.layout.item_opinion, listaOpiniones);
        ListView lvOpiniones = (ListView) findViewById(R.id.lvOpiniones);
        lvOpiniones.setAdapter(adapter);

        WebService webService = new WebService();
        webService.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registropinion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    private class WebService extends AsyncTask<String, Void, Void> {

        private ProgressDialog dialog;

        @Override
        protected Void doInBackground(String... params) {


            // Llamada a un servicio web y recogida de los datos que devuelve
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Opinion[] opinionesArray = restTemplate.getForObject(URL_SERVIDOR + "/opiniones", Opinion[].class);
            listaOpiniones.addAll(Arrays.asList(opinionesArray));

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            listaOpiniones.clear();

            dialog = new ProgressDialog(ListadoOpiniones.this);
            dialog.setTitle("Cargando");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (dialog != null)
                dialog.dismiss();

            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.notifyDataSetChanged();
        }
    }
}
