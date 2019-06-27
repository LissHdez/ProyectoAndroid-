package com.example.kajali.Modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;

public class Registro_Restaurantes {
    public final String INFO_Restaurantes ="appRestaurantes";

    public Registro_Restaurantes() {
    }
    public boolean addRestaurante(String nombre, String telefono, String descripcion, String horario, Uri img1, Uri img2, String provincia, String categoria, SQLiteDatabase db){
        String imgNULL = "null";
        ContentValues content= new ContentValues();
        content.put("nombreR", nombre);
        content.put("telefonoR",telefono);
        content.put("descripcionR",descripcion);
        content.put("horarioR",horario);
        content.put("provinciaR", provincia);
        content.put("categoriaR", categoria);

        if (img1==null||img2==null){
            content.put("imgR",imgNULL);
            content.put("platoD", imgNULL);

        }else{
            content.put("imgR",img1.toString());
            content.put("platoD",img2.toString());
        }


        return db.insert(INFO_Restaurantes, null,content)>0;
    }//fin del agregar

    public  ArrayList<Restaurantes> getRestaurante(SQLiteDatabase db)
    {
        Cursor cursor= db.query(INFO_Restaurantes, new String[]{"id", "nombreR","telefonoR","descripcionR","horarioR","imgR","platoD","provinciaR","categoriaR"}, null, null, null, null,"id desc" );
        cursor.moveToFirst();
        ArrayList<Restaurantes> lista= new ArrayList<>();
        while (!cursor.isAfterLast()){
            Restaurantes rest= new Restaurantes();
            rest.setId(cursor.getInt(0));
            rest.setNombreR(cursor.getString(1));
            rest.setTelefonoR(cursor.getString(2));
            rest.setDescripcionR(cursor.getString(3));
            rest.setHorarioR(cursor.getString(4));
            rest.setImgR(Uri.parse(cursor.getString(5)));
            rest.setPlatoD(Uri.parse(cursor.getString(6)));
            rest.setProvinciaR(cursor.getString(7));
            rest.setCategoriaR(cursor.getString(8));

            lista.add(rest);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }

}//fin de la class
