package com.joan.ra1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.action_Iniciar_sesion:
                intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                break;
            case R.id.action_registrarse:
                intent = new Intent(MainActivity.this, Usuario.class);
                startActivity(intent);
                break;
            case R.id.action_acercade:
                intent = new Intent(MainActivity.this, Acercade.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    @Override
    public void onBackPressed (){
        if(false)
            super.onBackPressed();

    }
}
