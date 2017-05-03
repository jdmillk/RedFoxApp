package com.joan.ra1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MascotaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Mascotas> listaMascotas;
    private LayoutInflater inflater;

    public MascotaAdapter(Activity context, ArrayList<Mascotas> listaAmigos) {
        this.context = context;
        this.listaMascotas = listaAmigos;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        TextView apodo;
        TextView mascota;
        ImageView foto;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        // Si la View es null se crea de nuevo
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_mascotas, null);

            holder = new ViewHolder();
            holder.foto = (ImageView) convertView.findViewById(R.id.ivFoto);
            holder.apodo = (TextView) convertView.findViewById(R.id.tvApodo);
            holder.mascota = (TextView) convertView.findViewById(R.id.tvMascota);

            convertView.setTag(holder);
        }
    /*
     * En caso de que la View no sea null se reutilizará con los
     * nuevos valores
     */
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Mascotas m = listaMascotas.get(position);
        if(m.getMascota().equalsIgnoreCase("gato") || m.getMascota().equalsIgnoreCase("cat")){
            holder.foto.setImageDrawable(context.getResources().getDrawable(R.drawable.gato));
        }else if(m.getMascota().equalsIgnoreCase("zorro") || m.getMascota().equalsIgnoreCase("fox")){
            holder.foto.setImageDrawable(context.getResources().getDrawable(R.drawable.zorro));
        }else{
            holder.foto.setImageDrawable(context.getResources().getDrawable(R.drawable.perro));
        }

        holder.apodo.setText(m.getApodo());
        holder.mascota.setText(m.getMascota());
        return convertView;
    }

    @Override
    public int getCount() {
        return listaMascotas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return listaMascotas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }


}
