package com.example.kajali;

import android.database.Cursor;
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
        listaRestaurantes = registroR.getRestaurante(db);

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

        String seleccionProvincia = busquedaProvincia.getSelectedItem().toString();


        btn_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAdapter custonAdapter = new CustomAdapter(getApplicationContext(), listaRestaurantes);
                listaProvincia.setAdapter(custonAdapter);
            }
        });

    }// fin del oncreate

    /////////////////////// CREACION DE BUSQUEDA POR CATEGORIA ////////////////




    public void createDatabase(){
        try {
            db = openOrCreateDatabase(NOMBRE_DB, MODE_PRIVATE, null);
            //deleteDatabase(NOMBRE_DB);
            db.execSQL(tRestaurantes);
        } catch (SQLiteOutOfMemoryException e){
            e.printStackTrace();
        }
    }
}//  fin de la clase  busqueda de restaurante
