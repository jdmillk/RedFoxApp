package com.joan.ra1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsuario = (EditText) findViewById(R.id.etUsuarioLogin);
        etUsuario.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etUsuario, InputMethodManager.SHOW_IMPLICIT);
        Button btIniciar = (Button) findViewById(R.id.btIniciar);
        btIniciar.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        boolean bien = false;
        int cont = 0;
        int cont2 = 0;
        EditText etUsuario = (EditText) findViewById(R.id.etUsuarioLogin);
        EditText etContrasena = (EditText) findViewById(R.id.etContrasenaLogin);
        DataBase db = new DataBase(this);
        ArrayList<String> vUsuario = db.usuario();
        ArrayList<String> vContrasena = db.contrasena();
        String usuario = etUsuario.getText().toString();
        String contrasena = etContrasena.getText().toString();
        for(int i = 0;i<vUsuario.size();i++){
            if(vUsuario.get(i).equals(usuario)){
                cont2 = i;
                System.out.println("usuario");
                etUsuario.setText(vUsuario.get(i));
                bien = true;
            }
        }
        for(int i = 0;i<vContrasena.size();i++){
            if(vContrasena.get(i).equals(contrasena)){
                cont = i;
                System.out.println("contrasena");
                etContrasena.setText(vContrasena.get(i));
                bien = true;
            }
        }
        if(bien == true && cont == cont2){
            System.out.println("bien");
            Intent intent = new Intent(Login.this, Perfil.class);
            //intent.putExtra("usuario", usuario);
            Constantes.USUARIO = usuario;
            startActivity(intent);
        }else{
            Toast.makeText(this, "No estas introduciendo bien los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
