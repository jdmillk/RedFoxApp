package com.joan.ra1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class AnadirOpinion extends AppCompatActivity implements View.OnClickListener {

    String cadena = "";

    String URL_SERVIDOR = "http://192.168.1.42:8082";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_opinion);


        EditText etTitulo = (EditText) findViewById(R.id.etTitulo);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etTitulo, InputMethodManager.SHOW_IMPLICIT);
        System.out.println(URL_SERVIDOR);
        Button btGuardar = (Button) findViewById(R.id.btGuardar);
        btGuardar.setOnClickListener(this);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registropinion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.action_ajustes:
                intent = new Intent(AnadirOpinion.this, Settings_Server.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btGuardar:
                boolean error = true;
                EditText etTitulo = (EditText) findViewById(R.id.etTitulo);
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                EditText etPuntuacion = (EditText) findViewById(R.id.etPuntuacion);
                String titulos = getResources().getString(R.string.lb_titulo);
                String textos = getResources().getString(R.string.lb_Opinion);
                String falta = getResources().getString(R.string.lb_falta);
                String mal = getResources().getString(R.string.lb_mal);
                if(etTitulo.getText().toString().equals("")){
                    cadena+=titulos + " , ";
                    error=false;
                }
                if(etTexto.getText().toString().equals("")){
                    cadena+=textos + " , ";
                    error = false;
                }

                if (etPuntuacion.getText().toString().equals(""))
                    etPuntuacion.setText("-1");

                if(Integer.parseInt(String.valueOf(etPuntuacion.getText())) > 10 || Integer.parseInt(String.valueOf(etPuntuacion.getText())) < 0){
                    cadena+=mal;
                    error=false;
                }
                if(error){
                    String titulo = etTitulo.getText().toString();
                    String texto = etTexto.getText().toString();
                    String puntuacion = etPuntuacion.getText().toString();


                    WebService webService = new WebService();
                    webService.execute(titulo, texto, puntuacion);

                    Intent intent = new Intent(AnadirOpinion.this, Perfil.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, falta + " " + cadena , Toast.LENGTH_SHORT).show();
                    cadena= "";
                }


                break;
            default:
                break;
        }
    }

    private class WebService extends AsyncTask<String, Void, Void> {

        private ProgressDialog dialog;

        @Override
        protected Void doInBackground(String... params) {


            System.out.println(URL_SERVIDOR + "holllalllalalla");
            // Llamada a un Servicio Web con paso de parámetros
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getForObject(URL_SERVIDOR + "/add_opinion?titulo=" + params[0] + "&texto=" + params[1] + "&puntuacion=" + params[2], Void.class);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AnadirOpinion.this);
            dialog.setTitle("Enviando Datos");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            EditText etTitulo = (EditText) findViewById(R.id.etTitulo);
            etTitulo.setText("");
            EditText etTexto = (EditText) findViewById(R.id.etTexto);
            etTexto.setText("");
            EditText etPuntuacion = (EditText) findViewById(R.id.etPuntuacion);
            etPuntuacion.setText("");

            if (dialog != null)
                dialog.dismiss();
        }


    }

}
