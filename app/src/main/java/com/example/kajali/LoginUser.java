package com.example.kajali;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUser extends AppCompatActivity {
    // creacion de la BD
    private static SQLiteDatabase db; // se define la BD
    private static final String NOMBRE_DB = "appkajali";//Se difine el nombre de la base

    EditText txtusu, txtPass;
    Button btnIngresar, btnRegistrar;
    String emailS, pasS;

    registroUsuario registroU= new registroUsuario();

    public static final String tbUsuario = "CREATE TABLE IF NOT EXISTS usuario(" +
            "nombre String NOT NULL," +
            "email String PRIMARY KEY," +
            "password String NOT NULL);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        abrirDatabase();
        txtusu=findViewById(R.id.txtEmail);
        txtPass=findViewById(R.id.txtPassword);
        btnIngresar=findViewById(R.id.btnIngresar);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUser.this,registroUsuario.class);
                startActivity(intent);
            }
        });
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailS=txtusu.getText().toString();
                pasS=txtPass.getText().toString();

              if (emailS.isEmpty()||pasS.isEmpty()){
                  Toast.makeText(getApplicationContext(),"Por favor llene todos los campos",Toast.LENGTH_SHORT).show();
              }else{
                  Cursor cursor=registroU.consultarUsuarios(emailS,pasS,db);
                  if (cursor.getCount()>0){
                      Intent intent = new Intent(LoginUser.this,MainActivity.class);
                      startActivity(intent);
                  }else{
                      Toast.makeText(getApplicationContext(),"Error usuario no permitido",Toast.LENGTH_SHORT).show();
                  }
              }
              txtusu.setText("");
              txtPass.setText("");
              txtusu.findFocus();
            }
        });

    }//fin del oncreate
    public void abrirDatabase() {
        try {
            db = openOrCreateDatabase(NOMBRE_DB, MODE_PRIVATE, null);
            db.execSQL(tbUsuario);
        } catch (SQLiteOutOfMemoryException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error el crear la base de datos", Toast.LENGTH_SHORT).show();
        }

    }//Fin del metodo para abrir la base de datos
}//fin de la class
