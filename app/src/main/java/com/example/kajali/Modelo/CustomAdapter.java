package com.example.kajali.Modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kajali.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Restaurantes> listaArray;

    public CustomAdapter(Context context, ArrayList<Restaurantes> listaArray) {
        this.context = context;
        this.listaArray = listaArray;
    }

    @Override
    public int getCount() {
        return listaArray.size();
    }

    @Override
    public Object getItem(int position) {
        return listaArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView= layoutInflater.inflate(R.layout.lyt_items,null);

        ImageView imageView = convertView.findViewById(R.id.tImgR);
        TextView nombre= convertView.findViewById(R.id.tNombre);
        TextView descripcion= convertView.findViewById(R.id.tDescripcion);
        TextView telefono= convertView.findViewById(R.id.tTelefono);
        TextView horario= convertView.findViewById(R.id.tHorario);

        if (listaArray.get(position).getImgR().toString().equals("mull"))
        {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }else{
            imageView.setImageURI(listaArray.get(position).getImgR());
        }
        nombre.setText(listaArray.get(position).getNombreR());
        descripcion.setText(listaArray.get(position).getDescripcionR());
        telefono.setText(listaArray.get(position).getTelefonoR());
        horario.setText(listaArray.get(position).getHorarioR());
        return convertView;
    }
}//fin de la class

