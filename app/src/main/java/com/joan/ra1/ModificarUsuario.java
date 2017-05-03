package com.joan.ra1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ModificarUsuario extends AppCompatActivity implements View.OnClickListener {

    String cadena = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);

        String seleccion = getResources().getString(R.string.lb_seleccion);
        String spain = getResources().getString(R.string.lb_spain);
        String england = getResources().getString(R.string.lb_england);
        List<String> pais = new ArrayList<>();
        pais.add(seleccion);
        pais.add(spain);
        pais.add(england);

        Spinner spPais = (Spinner) findViewById(R.id.spPais);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pais);
        spPais.setAdapter(adapter);


        EditText etUsuario = (EditText) findViewById(R.id.etUsuario);
        EditText etContrasena = (EditText) findViewById(R.id.etCntrasena);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
       // Spinner spPais = (Spinner) findViewById(R.id.spPais);
        EditText etCiudad = (EditText) findViewById(R.id.etCiudad);
        etUsuario.setEnabled(false);
        DataBase db = new DataBase(this);
        ArrayList<Users> vUsuarios = db.obtenerUsuarios();
        for (int i = 0; i < vUsuarios.size();i++){
            etUsuario.setText(vUsuarios.get(i).getUsuario());
            etContrasena.setText(vUsuarios.get(i).getContrasena());
            etEmail.setText(vUsuarios.get(i).getEmail());
            etCiudad.setText(vUsuarios.get(i).getCiudad());
            System.out.println(vUsuarios.get(i).getPais());
            if(vUsuarios.get(i).getPais().equalsIgnoreCase("Espana")){
                spPais.setSelection(1);
            }else{
                spPais.setSelection(2);
            }

        }

        Button btModificar = (Button) findViewById(R.id.btModificar);
        btModificar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText etUsuario = (EditText) findViewById(R.id.etUsuario);
        EditText etContrasena = (EditText) findViewById(R.id.etCntrasena);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        Spinner spPais = (Spinner) findViewById(R.id.spPais);
        EditText etCiudad = (EditText) findViewById(R.id.etCiudad);


        boolean error = true;
        String usuario = getResources().getString(R.string.lb_Usuario);
        String contrasena = getResources().getString(R.string.lb_Contrasena);
        String email = getResources().getString(R.string.lb_email);
        String seleccion = getResources().getString(R.string.lb_seleccion);
        String pais = getResources().getString(R.string.lb_Pais);
        String ciudad = getResources().getString(R.string.lb_Ciudad);
        Users u = new Users();
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

        if (error){
            u.setUsuario(etUsuario.getText().toString());
            u.setContrasena(etContrasena.getText().toString());
            u.setEmail(etEmail.getText().toString());
            if (spPais.getSelectedItem().equals("España") || spPais.getSelectedItem().equals( "Spain")) {
                u.setPais("Espana");
            } else {
                u.setPais("Inglaterra");
            }
            u.setCiudad(etCiudad.getText().toString());
            DataBase db = new DataBase(this);
            db.modificarUsuario(u);
            Intent intent = new Intent(ModificarUsuario.this, Perfil.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Falta por rellenar " + cadena, Toast.LENGTH_SHORT).show();
            cadena = "";
        }

    }
}
