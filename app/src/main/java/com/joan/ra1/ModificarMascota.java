package com.joan.ra1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ModificarMascota extends AppCompatActivity implements View.OnClickListener {

    String usuario;
    String cadena = "";
    private int RESULTADO_CARGA_IMAGEN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_mascota);

        int pos = getIntent().getIntExtra("pos",0);
        String gato = getResources().getString(R.string.lb_gato);
        String perro = getResources().getString(R.string.lb_perro);
        String zorro = getResources().getString(R.string.lb_zorro);
        List<String> mascota = new ArrayList<>();
        mascota.add(gato);
        mascota.add(perro);
        mascota.add(zorro);

        Spinner spMascota = (Spinner) findViewById(R.id.spMascotaM);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mascota);
        spMascota.setAdapter(adapter);

        String grande = getResources().getString(R.string.lb_grande);
        String mediano = getResources().getString(R.string.lb_mediano);
        String pequeno = getResources().getString(R.string.lb_pequeno);
        List<String> tamano = new ArrayList<>();
        tamano.add(grande);
        tamano.add(mediano);
        tamano.add(pequeno);

        Spinner spTamano = (Spinner) findViewById(R.id.spTamanoM);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tamano);
        spTamano.setAdapter(adapter2);

        EditText etApodo = (EditText) findViewById(R.id.etApodoM);
        CheckBox cbAmistoso = (CheckBox) findViewById(R.id.cbAmistosoM);
        ImageView imageView = (ImageView) findViewById(R.id.ivImagenM);

        DataBase db = new DataBase(this);
        ArrayList<Mascotas> vMascotas = db.obtenerMascotas();
        etApodo.setEnabled(false);
        etApodo.setText(vMascotas.get(pos).getApodo());
        if(vMascotas.get(getIntent().getIntExtra("pos", 0)).getMascota().equalsIgnoreCase("gato") || vMascotas.get(pos).getMascota().equalsIgnoreCase("cat")){
            spMascota.setSelection(0);
        }else if(vMascotas.get(pos).getMascota().equalsIgnoreCase("perro") || vMascotas.get(pos).getMascota().equalsIgnoreCase("dog")){
            spMascota.setSelection(1);
        }else{
            spMascota.setSelection(2);
        }
        if(vMascotas.get(pos).getTamano().equalsIgnoreCase("grande") || vMascotas.get(pos).getTamano().equalsIgnoreCase("big")){
            spTamano.setSelection(0);
        }else if(vMascotas.get(pos).getTamano().equalsIgnoreCase("mediano") || vMascotas.get(pos).getTamano().equalsIgnoreCase("medium")){
            spTamano.setSelection(1);
        }else{
            spTamano.setSelection(2);
        }
        if (vMascotas.get(pos).getAmistoso() == 1){
            cbAmistoso.setChecked(true);
        }else{
            cbAmistoso.setChecked(false);
        }

        imageView.setImageBitmap(vMascotas.get(pos).getImagen());
        Button btModificar = (Button) findViewById(R.id.btModificarM);
        btModificar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

       switch(view.getId()){
           case R.id.btModificarM:
               EditText etApodo = (EditText) findViewById(R.id.etApodoM);
               CheckBox cbAmistoso = (CheckBox) findViewById(R.id.cbAmistosoM);
               ImageView imageView = (ImageView) findViewById(R.id.ivImagenM);
               Spinner spTamano = (Spinner) findViewById(R.id.spTamanoM);
               Spinner spMascota = (Spinner) findViewById(R.id.spMascotaM);


               Mascotas m = new Mascotas();
               String apodo = etApodo.getText().toString();
               m.setApodo(apodo);
               m.setMascota(spMascota.getSelectedItem().toString());
               if (spTamano.getSelectedItem().equals( "Pequeño") || spTamano.getSelectedItem().equals("Little")) {
                   m.setTamano("Pequeno");
               } else {
                   m.setTamano(spTamano.getSelectedItem().toString());
               }
               if(cbAmistoso.isChecked() == true){
                   m.setAmistoso(1);
               }else{
                   m.setAmistoso(0);
               }
               m.setImagen(((BitmapDrawable) imageView.getDrawable()).getBitmap());
               DataBase db = new DataBase(this);
               db.modificarMascota(m, Constantes.USUARIO, apodo);
               Intent intent = new Intent(ModificarMascota.this, Perfil.class);
               startActivity(intent);
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

            ImageView imageView = (ImageView) findViewById(R.id.ivImagenM);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
