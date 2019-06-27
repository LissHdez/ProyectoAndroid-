package com.example.kajali;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kajali.Modelo.CustomAdapter;
import com.example.kajali.Modelo.Registro_Restaurantes;
import com.example.kajali.Modelo.Restaurantes;

import java.util.ArrayList;

public class lista_Restaurantes extends AppCompatActivity {

    Registro_Restaurantes registroR= new Registro_Restaurantes();

    ////// Base de Datos //////////////
    private static final String NOMBRE_DB="appkajali";
    private static SQLiteDatabase db;
    public static final String tRestaurantes= "CREATE TABLE IF NOT EXISTS appRestaurantes("+"id INTEGER PRIMARY KEY AUTOINCREMENT," +" nombreR VARCHAR NOT NULL,"+" telefonoR VARCHAR NOT NULL,"+"descripcionR VARCHAR NOT NULL,"+
            "horarioR VARCHAR NOT NULL,"+"imgR VARCHAR,"+"platoD VARCHAR,"+"provinciaR VARCHAR NOT NULL,"+"categoriaR VARCHAR NOT NULL);";

    ////////////// Array /////////////
    ArrayList<Restaurantes> list;
    ListView lista;
  ImageButton btnLogin, btnMain, btnDetalle;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista__restaurantes);

        createDatabase();
        lista=findViewById(R.id.lv_listaR);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
     // btnLogin=findViewById(R.id.btnLogin);
      //btnMain=findViewById(R.id.btnMain);
      //btnDetalle=findViewById(R.id.btnDetalle);

         list=registroR.getRestaurante(db);


        CustomAdapter custonAdapter = new CustomAdapter(getApplicationContext(), list);
        lista.setAdapter(custonAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( lista_Restaurantes.this,detalle_Restaurante.class);


                intent.putExtra("nombreR",list.get(position).getNombreR());
                intent.putExtra("telefonoR",list.get(position).getTelefonoR());
                intent.putExtra("descripcionR",list.get(position).getDescripcionR());
                intent.putExtra("horarioR",list.get(position).getHorarioR());
                intent.putExtra("imgR", list.get(position).getImgR().toString());
                intent.putExtra("platoD", list.get(position).getPlatoD().toString());
                intent.putExtra("provinciaR",list.get(position).getProvinciaR());
                intent.putExtra("categoriaR",list.get(position).getCategoriaR());
                intent.putExtra("id", list.get(position).getId());
                startActivity(intent);
            }
        });


        /// menu navegacion inferior
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.inicioItem) {

                } else if (item.getItemId() == R.id.buscarLupaItem) {
                    Intent intent = new Intent(lista_Restaurantes.this,busquedaRestaurantes.class);
                    startActivity(intent);
                }  else if (item.getItemId() == R.id.busquedaItem) {
                    Toast.makeText(getApplicationContext(), "No esta disponible", Toast.LENGTH_SHORT).show();

                } else if (item.getItemId() == R.id.registroItem) {
                    Intent intent = new Intent(lista_Restaurantes.this,LoginUser.class);
                    startActivity(intent);
                }

                return true;
            }
        });
    }//fin de oncreate

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
