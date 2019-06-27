package com.example.kajali;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class registroUsuario extends AppCompatActivity {
    // creacion de la BD
    private static SQLiteDatabase db; // se define la BD
    private static final String NOMBRE_DB = "appkajali";//Se difine el nombre de la base
    public final String TABLA_Usuario = "usuario";//Se define la tabla usuario

    EditText txtNombre, txtEmail, txtPassword;
    Button btnRegistrar;
    ImageButton btnRegresar;

    String nombre, email, password;

    //  Crear la columna de la BD
    public static final String tbUsuario = "CREATE TABLE IF NOT EXISTS usuario(" +
            "nombre String NOT NULL," +
            "email String PRIMARY KEY," +
            "password String NOT NULL);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registro_usuario);
        abrirDatabase();


        // declaracion de los txt
        txtNombre = findViewById(R.id.txtNombre);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        //declaracion de los botones
        btnRegistrar = findViewById(R.id.btnRegistrar);
        //btnRegresar = findViewById(R.id.btnRegresar);

        // METODO REGISTRAR
        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nombre = txtNombre.getText().toString();
                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();

                if (registrarUsuario(nombre, email, password)) {
                    Toast.makeText(getApplicationContext(), "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
                    finish();
                    limpiar();
                    Intent intent = new Intent(registroUsuario.this,LoginUser.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Error al reqistrar el Usuario", Toast.LENGTH_SHORT).show();
                }// fin del else

            }// fin del onclick


        });// fin del btn
    }// fin del ONCREATE

//============================================================================================================================================

    public void abrirDatabase() {
        try {
            db = openOrCreateDatabase(NOMBRE_DB, MODE_PRIVATE, null);
            db.execSQL(tbUsuario);
        } catch (SQLiteOutOfMemoryException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error el crear la base de datos", Toast.LENGTH_SHORT).show();
        }

    }//Fin del metodo para abrir la base de datos

    //===========================================================================

    public boolean registrarUsuario(String nombre, String email, String password) {
        ContentValues content = new ContentValues();
        content.put("nombre", (nombre));
        content.put("email", (email));
        content.put("password", (password));
        return db.insert(TABLA_Usuario, null, content) > 0;
    } // fin del metodo registrar

    public void limpiar() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }
    public Cursor consultarUsuarios(String usu, String pas, SQLiteDatabase db)throws SQLException{
        Cursor mcursor=null;
        mcursor=db.query("usuario", new String[]{"nombre","email","password"},"email like '"+usu+"'"+"and password like '"+pas+"'",null,null,null,null);
        return mcursor;
    }

}//fin de la class
