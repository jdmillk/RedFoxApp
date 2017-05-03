package com.joan.ra1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends AppCompatActivity implements View.OnClickListener{

    String cadena = "";
    int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        String seleccion = getResources().getString(R.string.lb_seleccion);
        String spain = getResources().getString(R.string.lb_spain);
        String england = getResources().getString(R.string.lb_england);
        List<String> pais = new ArrayList<>();
        pais.add(seleccion);
        pais.add(spain);
        pais.add(england);

        Spinner spPais = (Spinner) findViewById(R.id.spPaisM);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pais);
        spPais.setAdapter(adapter);

        EditText etUsuario = (EditText) findViewById(R.id.etUsuarioM);
        etUsuario.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etUsuario, InputMethodManager.SHOW_IMPLICIT);

        Button btRegistrar = (Button) findViewById(R.id.btRegistrarse);
        btRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText etUsuario = (EditText) findViewById(R.id.etUsuarioM);
        EditText etContrasena = (EditText) findViewById(R.id.etCntrasenaM);
        EditText etEmail = (EditText) findViewById(R.id.etEmailM);
        Spinner spPais = (Spinner) findViewById(R.id.spPaisM);
        EditText etCiudad = (EditText) findViewById(R.id.etCiudadM);

        boolean error = true;
        String usuario = getResources().getString(R.string.lb_Usuario);
        String contrasena = getResources().getString(R.string.lb_Contrasena);
        String email = getResources().getString(R.string.lb_email);
        String seleccion = getResources().getString(R.string.lb_seleccion);
        String pais = getResources().getString(R.string.lb_Pais);
        String ciudad = getResources().getString(R.string.lb_Ciudad);
        String falta = getResources().getString(R.string.lb_falta);
        Users u = new Users();
        DataBase db = new DataBase(this);
        ArrayList<Users> vUsuarios = db.obtenerUsuarios();
        for(int i = 0;i<vUsuarios.size();i++){
            if(vUsuarios.get(i).getUsuario().equalsIgnoreCase(etUsuario.getText().toString())){
                error=false;
                Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
            }
        }

        if(etUsuario.getText().toString().equals("")){
            cadena+= usuario + " , ";
            error=false;
        }
        if(etContrasena.getText().toString().equals("")){
            cadena+=contrasena+ " , ";
            error=false;
        }
        if(etEmail.getText().toString().equals("")){
            cadena+=email+ " , ";
            error=false;
        }
        if(spPais.getSelectedItem() == seleccion){
            cadena+=pais+ " , ";
            error=false;
        }
        if(etCiudad.getText().toString().equals("")){
            cadena+=ciudad+ " , ";
            error=false;
        }

        if(error){
            u.setUsuario(etUsuario.getText().toString());
            u.setContrasena(etContrasena.getText().toString());
            u.setEmail(etEmail.getText().toString());
            if (spPais.getSelectedItem().equals("España") || spPais.getSelectedItem().equals( "Spain")) {
                u.setPais("Espana");
            } else {
                u.setPais("Inglaterra");
            }
            u.setCiudad(etCiudad.getText().toString());
            db.registrarUsuario(u);
            cont++;
            Intent intent = new Intent(Usuario.this, Registrado.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, falta + " " + cadena, Toast.LENGTH_SHORT).show();
            cadena = "";
        }

    }
}
