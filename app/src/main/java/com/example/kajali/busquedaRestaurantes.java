package com.example.kajali;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class busquedaRestaurantes extends AppCompatActivity {

    private Spinner busquedaProvincia, busquedaCategoria;
    Button btn_Buscar;


    private static final String NOMBRE_DB="appkajali";
    private static SQLiteDatabase db;
    public static final String tRestaurantes= "CREATE TABLE IF NOT EXISTS appRestaurantes("+"id INTEGER PRIMARY KEY AUTOINCREMENT," +" nombreR VARCHAR NOT NULL,"+" telefonoR VARCHAR NOT NULL,"+"descripcionR VARCHAR NOT NULL,"+
            "horarioR VARCHAR NOT NULL,"+"imgR VARCHAR,"+"platoD VARCHAR,"+"provinciaR VARCHAR NOT NULL,"+"categoriaR VARCHAR NOT NULL);";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_busqueda);

        //  creamos la relacion grafica/logica

        busquedaProvincia=findViewById(R.id.spProvinicia);
        busquedaCategoria=findViewById(R.id.spCategoria);
        btn_Buscar=findViewById(R.id.btnBuscar);


        // creacion del arreglo para spinnerProvincias
        String  [] provincias ={ "Alajuela", "Cartago","Guanacaste","Heredia",
                "Limon", "Puntarenas", "San Jose" };

        // Mostrar los campos del arreglo en el desplegable del spinner
        ArrayAdapter<String>adapterPovincia = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, provincias);
        busquedaProvincia.setAdapter(adapterPovincia);

        String seleccionProvincia = busquedaProvincia.getSelectedItem().toString();

        // array para traer la informacion

        String [] busqueda =

        }// fin del if Alajuela




        //

    }// fin del oncreate

    /////////////////////// CREACION DE LOS METODOS NECESARIOS ////////////////

    // metodo boton buscar
    public void Buscar (View view){

    }

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
