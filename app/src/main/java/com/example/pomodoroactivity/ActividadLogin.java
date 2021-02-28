package com.example.pomodoroactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pomodoroactivity.utilidades.Utilidades;

public class ActividadLogin extends AppCompatActivity {

    private EditText et1; //Nombre en actividades

    EditText campoUsuario, campoPassword;
    TextView campoError;

    String nombre, pass;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_login);


        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_promodoro",null,1);

        campoUsuario = (EditText) findViewById(R.id.txt_UsuarioIngreso);
        campoPassword = (EditText) findViewById(R.id.txt_UsuarioPassword);
        campoError = (TextView) findViewById(R.id.txt_error);
    }

    //Método para regresar a principal
    public void AnteriorLogin (View view){
        Intent AnteriorLogin = new Intent(this, MainActivity.class);
        startActivity(AnteriorLogin);
    }

    //Método para boton login
    public void mostrarNombre(View view){
        if(campoUsuario.getText().toString().isEmpty()|| campoPassword.getText().toString().isEmpty()) {
            campoError.setText("No ha diligenciado todos los campos.");
        }else {
            campoError.setText("");
            consultar();
        }

    }

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoUsuario.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_PASSWORD};
        try{
            Cursor cursor=db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_USUARIO+"=?", parametros, null, null, null);
            cursor.moveToFirst();
            nombre = cursor.getString(0);
            pass = cursor.getString(1);
            if(pass.equals(campoPassword.getText().toString())){
                Intent mostrarNombre = new Intent(this, ActividadesUsuario.class);
                mostrarNombre.putExtra("nombrePersona", nombre);
                mostrarNombre.putExtra("usuarioPersona", campoUsuario.getText().toString());
                startActivity(mostrarNombre);
                cursor.close();
            }else{
                campoError.setText("Contraseña incorrecta.");
                Toast.makeText(getApplicationContext(), "Contraseña incorrecta.", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            campoError.setText("El usuario no existe.");
            Toast.makeText(getApplicationContext(), "El usuario no existe.", Toast.LENGTH_LONG).show();
        }

    }

}