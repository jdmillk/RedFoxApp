package com.joan.ra1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Mascota extends AppCompatActivity implements View.OnClickListener{

    String cadena = "";
    private int RESULTADO_CARGA_IMAGEN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);
        String gato = getResources().getString(R.string.lb_gato);
        String perro = getResources().getString(R.string.lb_perro);
        String zorro = getResources().getString(R.string.lb_zorro);
        List<String> mascota = new ArrayList<>();
        mascota.add(gato);
        mascota.add(perro);
        mascota.add(zorro);

        Spinner spMascota = (Spinner) findViewById(R.id.spMascota);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mascota);
        spMascota.setAdapter(adapter);

        String grande = getResources().getString(R.string.lb_grande);
        String mediano = getResources().getString(R.string.lb_mediano);
        String pequeno = getResources().getString(R.string.lb_pequeno);
        List<String> tamano = new ArrayList<>();
        tamano.add(grande);
        tamano.add(mediano);
        tamano.add(pequeno);

        Spinner spTamano = (Spinner) findViewById(R.id.spTamano);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tamano);
        spTamano.setAdapter(adapter2);

        EditText etApodo = (EditText) findViewById(R.id.etApodo);
        etApodo.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etApodo, InputMethodManager.SHOW_IMPLICIT);
        Button btRegistrar = (Button) findViewById(R.id.btRegistrar);
        ImageButton ibImagen = (ImageButton) findViewById(R.id.ibImagen);
        btRegistrar.setOnClickListener(this);
        ibImagen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.ibImagen:

                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULTADO_CARGA_IMAGEN);
                break;
            case R.id.btRegistrar:
                EditText etApodo = (EditText) findViewById(R.id.etApodo);
                Spinner spMascota = (Spinner) findViewById(R.id.spMascota);
                Spinner spTamano = (Spinner) findViewById(R.id.spTamano);
                CheckBox cbAmistoso = (CheckBox) findViewById(R.id.cbAmistoso);
                ImageView imageView = (ImageView) findViewById(R.id.ivImagen);


                boolean error = true;
                Mascotas m = new Mascotas();
                String apodo = getResources().getString(R.string.lb_Apodo);
                String mascota = getResources().getString(R.string.lb_Mascota);
                String tamano = getResources().getString(R.string.lb_Tamano);
                String falta = getResources().getString(R.string.lb_falta);
                if(etApodo.getText().toString().equals("")){
                    cadena+=apodo;
                    error=false;
                }
                if(error){
                    m.setApodo(etApodo.getText().toString());
                    m.setMascota(spMascota.getSelectedItem().toString());
                    if(spTamano.getSelectedItem().equals( "Pequeño") || spTamano.getSelectedItem().equals("Little")){
                        m.setTamano("Pequeno");
                    }else{
                        m.setTamano(spTamano.getSelectedItem().toString());
                    }
                    if(cbAmistoso.isChecked() == true){
                        m.setAmistoso(1);
                    }else{
                        m.setAmistoso(0);
                    }
                    m.setImagen(((BitmapDrawable) imageView.getDrawable()).getBitmap());
                    DataBase db = new DataBase(this);
                    System.out.println("aqui");
                    db.registrarMascota(m, Constantes.USUARIO);
                    Intent intento = new Intent(Mascota.this, Perfil.class);
                    startActivity(intento);
                }else {
                    Toast.makeText(this, falta + " " + cadena, Toast.LENGTH_SHORT).show();
                    error=true;
                    cadena = "";
                }

                /*if(spMascota.getSelectedItem() == "Perro" || spMascota.getSelectedItem() == "Dog"){
                    m.setMascota("Perro");
                }else if(spMascota.getSelectedItem() == "Gato" || spMascota.getSelectedItem() == "Cat"){
                    m.setMascota("Gato");
                }

                if (spTamano.getSelectedItem() == "Pequeño" || spTamano.getSelectedItem() == ("Little")) {
                    m.setTamano("Pequeno");
                } else if(spTamano.getSelectedItem() == "Mediano" || spTamano.getSelectedItem().equals("Medium")){
                    m.setTamano("Mediano");
                }else{
                    m.setTamano("Grande");
                }*/

                break;
            default:
                break;
        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == RESULTADO_CARGA_IMAGEN) && (resultCode == RESULT_OK) && (data != null)) {
            Uri imagenSeleccionada = data.getData();
            String[] ruta = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(imagenSeleccionada, ruta, null, null, null);
            cursor.moveToFirst();

            int indice = cursor.getColumnIndex(ruta[0]);
            String picturePath = cursor.getString(indice);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.ivImagen);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
