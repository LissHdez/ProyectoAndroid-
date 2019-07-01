package com.example.kajali;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
    private BottomNavigationView bottomNavigationView;
    public final String INFO_Restaurantes ="appRestaurantes";

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
        btn_Buscar=findViewById(R.id.btnBuscar);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // creacion del arreglo para cada provincia del spinnerProvincias
        String  [] provincias ={ "Alajuela", "Cartago","Guanacaste","Heredia",
                "Limon", "Puntarenas", "SanJosé" };

        // Mostrar los campos del arreglo en el desplegable del spinner
        ArrayAdapter<String>adapterProvincia = new ArrayAdapter<String>( this, R.layout.spinner_item_provincias, provincias);
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


        /// menu navegacion inferior
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.inicioItem) {
                    Intent intent = new Intent(busquedaRestaurantes.this,lista_Restaurantes.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.buscarLupaItem) {

                }  else if (item.getItemId() == R.id.busquedaItem) {
                    Toast.makeText(getApplicationContext(), "No esta disponible", Toast.LENGTH_SHORT).show();

                } else if (item.getItemId() == R.id.registroItem) {
                    Intent intent = new Intent(busquedaRestaurantes.this,LoginUser.class);
                    startActivity(intent);
                }

                return true;
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

        //Cursor c= db.query("appRestaurantes", new String[]{"id", "nombreR","telefonoR","descripcionR","horarioR","imgR","platoD","provinciaR","categoriaR"},"provinciaR LIKE '"+ seleccionProvincia +"'",null , null, null, null,null );
        Cursor c = db.rawQuery("select * from " + INFO_Restaurantes+" where provinciaR =?", new String[]{seleccionProvincia});
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
