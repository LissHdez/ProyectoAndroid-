package com.example.kajali;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kajali.Modelo.CustomAdapter;
import com.example.kajali.Modelo.MapsActivity;
import com.example.kajali.Modelo.Registro_Restaurantes;
import com.example.kajali.Modelo.Restaurantes;

import java.util.ArrayList;

public class detalle_Restaurante extends AppCompatActivity {

    ArrayList<Restaurantes> list;
    Restaurantes rest;
    int position;
    String nombreS,telefonoS,horarioS,descripcionS, provinciaS,categoriaS;
    Uri imagen01, imagen02;
    Registro_Restaurantes registroR= new Registro_Restaurantes();
    private static final String NOMBRE_DB="appkajali";
    private static SQLiteDatabase db;
    Button btn_verMapa;

    public static final String tRestaurantes= "CREATE TABLE IF NOT EXISTS appRestaurantes("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nombreR VARCHAR NOT NULL,"+
            " telefonoR VARCHAR NOT NULL,"+
            "descripcionR VARCHAR NOT NULL,"+
            "horarioR VARCHAR NOT NULL,"+
            "imgR VARCHAR,"+
            "platoD VARCHAR,"+
            "provinciaR VARCHAR NOT NULL,"+
            "categoriaR VARCHAR NOT NULL);";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_detalle__restaurante);
        createDatabase();
        list=registroR.getRestaurante(db);


        ImageView imagen1=findViewById(R.id.dimgR);
        ImageView imagen2=findViewById(R.id.dimgP);
        TextView nombre= findViewById(R.id.dNombre);
        TextView telefono= findViewById(R.id.dTelefono);
        TextView horario=findViewById(R.id.dHorario);
        Button btn_verMapa=findViewById(R.id.btnVerMap);


        Intent intent = getIntent();
        nombreS=intent.getStringExtra("nombreR");
        telefonoS=intent.getStringExtra("telefonoR");
        descripcionS=intent.getStringExtra("descripcionR");
        horarioS=intent.getStringExtra("horarioR");

        provinciaS=intent.getStringExtra("provinciaR");
        categoriaS=intent.getStringExtra("categoriaR");
        imagen01=Uri.parse(intent.getStringExtra("imgR"));
        imagen02=Uri.parse(intent.getStringExtra("platoD"));
        position=intent.getIntExtra("id", 0);

        rest= new Restaurantes(position,nombreS,descripcionS,telefonoS,horarioS,provinciaS,categoriaS,imagen01,imagen02);



        nombre.setText(rest.getNombreR());
        telefono.setText(rest.getTelefonoR());
        horario.setText(rest.getHorarioR());

        if (rest.getImgR().toString().equals("mull"))
        {
            imagen1.setImageResource(R.mipmap.ic_launcher);
        }else{
            imagen1.setImageURI(rest.getImgR());
        }
        if (rest.getPlatoD().toString().equals("mull"))
        {
            imagen2.setImageResource(R.mipmap.ic_launcher);
        }else{
            imagen2.setImageURI(rest.getPlatoD());
        }


        ///////////////////////////////////////////////////////

        btn_verMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);


            }// fin del onclick
        });// fin del boton
    }//fin del oncreate


    public void createDatabase(){
        try {
            db = openOrCreateDatabase(NOMBRE_DB, MODE_PRIVATE, null);
            //deleteDatabase(NOMBRE_DB);
            db.execSQL(tRestaurantes);
        } catch (SQLiteOutOfMemoryException e){
            e.printStackTrace();
        }
    }
}//fin de la class
