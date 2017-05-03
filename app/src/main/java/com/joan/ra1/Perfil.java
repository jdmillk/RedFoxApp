package com.joan.ra1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity implements OnItemLongClickListener{


    String usuario = "";
    String position;

    ArrayList<Mascotas> vMascotas;

    private int posMascotaSelected;

    public String getPosition() {
        return position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        DataBase db = new DataBase(this);
        ListView lista = (ListView) findViewById(R.id.lista);
        vMascotas = db.obtenerMascota();
        MascotaAdapter adapter = new MascotaAdapter(this,vMascotas);
        lista.setAdapter(adapter);

        lista.setOnItemLongClickListener(this);
        registerForContextMenu(lista);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfil_mascota, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_Modificar_mascota:
                intent = new Intent(Perfil.this, ModificarMascota.class);
                intent.putExtra("pos", posMascotaSelected);
                startActivity(intent);
                return true;
            case R.id.action_eliminar:
                DataBase db = new DataBase(this);
                ArrayList<Mascotas> vMascotas = db.obtenerMascotas();
                String apodo = vMascotas.get(posMascotaSelected).getApodo();
                db.eliminarMascota(apodo);
                ListView lista = (ListView) findViewById(R.id.lista);
                vMascotas = db.obtenerMascota();
                MascotaAdapter adapter = new MascotaAdapter(this,vMascotas);
                lista.setAdapter(adapter);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){

            case R.id.action_Modificar_perfil:
                intent = new Intent(Perfil.this, ModificarUsuario.class);
                startActivity(intent);
                break;
            case R.id.action_Añadir_mascota:
                intent = new Intent(Perfil.this, Mascota.class);
                startActivity(intent);
                break;
            case R.id.action_Mapa:
                intent = new Intent(Perfil.this, Mapa.class);
                startActivity(intent);
                break;
            case R.id.action_anadir_opinion:
                intent = new Intent(Perfil.this, AnadirOpinion.class);
                startActivity(intent);
                break;

            case R.id.action_Desconectarse:
                intent = new Intent(Perfil.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        posMascotaSelected = i;
        return false;
    }

    @Override
    public void onBackPressed (){
        if(false)
            super.onBackPressed();

    }
}
