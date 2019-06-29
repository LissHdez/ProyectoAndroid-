package com.example.kajali;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kajali.Modelo.CustomAdapter;
import com.example.kajali.Modelo.Registro_Restaurantes;
import com.example.kajali.Modelo.Restaurantes;

import java.util.ArrayList;

public class busquedaRestaurantes extends AppCompatActivity {

    private Spinner busquedaProvincia, busquedaCategoria;
    Button btn_Buscar;

    Registro_Restaurantes registroR= new Registro_Restaurantes();
    ArrayList<Restaurantes> listaRestaurantes;
    ArrayAdapter<Restaurantes> adapterRestaurantes;
    ListView listaProvincia;

    private static final String NOMBRE_DB="appkajali";
    private static SQLiteDatabase db;
    public static final String tRestaurantes= "CREATE TABLE IF NOT EXISTS appRestaurantes("+"id INTEGER PRIMARY KEY AUTOINCREMENT," +" nombreR VARCHAR NOT NULL,"+" telefonoR VARCHAR NOT NULL,"+"descripcionR VARCHAR NOT NULL,"+
            "horarioR VARCHAR NOT NULL,"+"imgR VARCHAR,"+"platoD VARCHAR,"+"provinciaR VARCHAR NOT NULL,"+"categoriaR VARCHAR NOT NULL);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_busqueda);
        createDatabase();


        //  creamos la relacion grafica/logica
        listaProvincia=findViewById(R.id.listaResultadosBusqueda);
        busquedaProvincia=findViewById(R.id.spProvinicia);
        busquedaCategoria=findViewById(R.id.spCategoria);
        btn_Buscar=findViewById(R.id.btnBuscar);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // creacion del arreglo para cada provincia del spinnerProvincias
        String  [] provincias ={ "Alajuela", "Cartago","Guanacaste","Heredia",
                "Limon", "Puntarenas", "San Jose" };

        // Mostrar los campos del arreglo en el desplegable del spinner
        ArrayAdapter<String>adapterProvincia = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, provincias);
        busquedaProvincia.setAdapter(adapterProvincia);
        //String seleccionProvincia = busquedaProvincia.getSelectedItem().toString();



        btn_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getInw(db).isEmpty()){
                    Toast.makeText(getApplicationContext(),"No hay restaurantes registrado",Toast.LENGTH_SHORT).show();
                }else {
                    listaRestaurantes = getInw(db);
                    CustomAdapter custonAdapter = new CustomAdapter(getApplicationContext(), listaRestaurantes);
                    listaProvincia.setAdapter(custonAdapter);
                }

            }
        });

    }// fin del oncreate


    public void createDatabase(){
        try {
            db = openOrCreateDatabase(NOMBRE_DB, MODE_PRIVATE, null);
            //deleteDatabase(NOMBRE_DB);
            db.execSQL(tRestaurantes);
        } catch (SQLiteOutOfMemoryException e){
            e.printStackTrace();
        }
    }// fin del create database

    public ArrayList<Restaurantes> getInw(SQLiteDatabase db )throws SQLException {
        String seleccionProvincia = busquedaProvincia.getSelectedItem().toString();

        Cursor c= db.query("appRestaurantes", new String[]{"id", "nombreR","telefonoR","descripcionR","horarioR","imgR","platoD","provinciaR","categoriaR"},"provinciaR LIKE '"+ seleccionProvincia +"'",null , null, null, null,null );

        c.moveToFirst();

        ArrayList<Restaurantes> list = new ArrayList<>();

        while (!c.isAfterLast()) {

            Restaurantes restaurante = new Restaurantes();


            restaurante.setId(c.getInt(0));
            restaurante.setNombreR(c.getString(1));
            restaurante.setTelefonoR(c.getString(2));
            restaurante.setDescripcionR(c.getString(3));
            restaurante.setHorarioR(c.getString(4));
            restaurante.setImgR(Uri.parse(c.getString(5)));
            restaurante.setPlatoD(Uri.parse(c.getString(6)));
            restaurante.setProvinciaR(c.getString(7));
            restaurante.setCategoriaR(c.getString(8));



            list.add(restaurante);

            c.moveToNext();
        }
        c.close();
        return list;

    }// fin de metodo getInw
}//  fin de la clase  busqueda de restaurante
