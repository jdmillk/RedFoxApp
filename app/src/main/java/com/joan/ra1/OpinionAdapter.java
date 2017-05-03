package com.joan.ra1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Joan on 19/12/2016.
 */

public class OpinionAdapter extends ArrayAdapter<Opinion>{

    private Context contexto;
    private int layoutId;
    private List<Opinion> datos;

    public OpinionAdapter(Context contexto, int layoutId, List<Opinion> datos) {
        super(contexto, layoutId, datos);
        this.contexto = contexto;
        this.layoutId = layoutId;
        this.datos = datos;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup padre) {

        View fila = view;
        ItemOpinion item = null;

        if (fila == null) {
            LayoutInflater inflater = ((Activity) contexto).getLayoutInflater();
            fila = inflater.inflate(layoutId, padre, false);

            item = new ItemOpinion();
            item.imagen = (ImageView) fila.findViewById(R.id.ivImagen);
            item.titulo = (TextView) fila.findViewById(R.id.tvTitulo);

            fila.setTag(item);
        }
        else {
            item = (ItemOpinion) fila.getTag();
        }

        Opinion opinion = datos.get(posicion);
        item.imagen.setImageDrawable(contexto.getResources().getDrawable(R.drawable.revisar));
        item.titulo.setText(opinion.getTitulo());

        return fila;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Opinion getItem(int posicion) {

        return datos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    static class ItemOpinion {

        ImageView imagen;
        TextView titulo;
    }
}
