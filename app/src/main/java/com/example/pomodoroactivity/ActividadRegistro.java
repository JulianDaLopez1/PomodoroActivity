package com.example.pomodoroactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pomodoroactivity.utilidades.Utilidades;

public class ActividadRegistro extends AppCompatActivity {

    EditText campoUsuario, campoNombre, campoPassword;
    TextView campoError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_registro);

        campoNombre = (EditText) findViewById(R.id.txt_Nombre);
        campoUsuario = (EditText) findViewById(R.id.txt_Usuario);
        campoPassword = (EditText) findViewById(R.id.txt_Password);

        campoError = (TextView) findViewById(R.id.txt_error2);
    }

    //Método del boton Registrarse
    public void Registro(View view){
        if(campoUsuario.getText().toString().isEmpty()|| campoPassword.getText().toString().isEmpty() || campoNombre.getText().toString().isEmpty()) {
            campoError.setText("No ha diligenciado todos los campos.");
        }else {
            campoError.setText("");
            if (consultar() == true) {
                campoError.setText("El usuario ya existe.");
            } else {
                registrarUsuarios();

                Intent Registro = new Intent(this, ActividadesUsuario.class);
                Registro.putExtra("nombrePersona", campoNombre.getText().toString());
                Registro.putExtra("usuarioPersona", campoUsuario.getText().toString());
                startActivity(Registro);
            }
        }
    }

    private void registrarUsuarios(){

            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_promodoro", null, 1);

            SQLiteDatabase db = conn.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
            values.put(Utilidades.CAMPO_USUARIO, campoUsuario.getText().toString());
            values.put(Utilidades.CAMPO_PASSWORD, campoPassword.getText().toString());

            Long nombreResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_NOMBRE, values);

            //Toast.makeText(getApplicationContext(), "Id Registro: "+nombreResultante,Toast.LENGTH_SHORT).show();
            db.close();

    }

    //Método para regresar a principal
    public void AnteriorRegistro (View view){
        Intent AnteriorRegistro = new Intent(this, MainActivity.class);
        startActivity(AnteriorRegistro);
    }

    private boolean consultar() {
        boolean existeUsuario = false;
        
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, "bd_promodoro", null, 1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoUsuario.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_PASSWORD};
        try{
            Cursor cursor=db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_USUARIO+"=?", parametros, null, null, null);
            if(cursor.moveToFirst()){
                existeUsuario =true;
            }else{
                existeUsuario=false;
            }
        }catch (Exception e){

        }
        db.close();
        return existeUsuario;

    }

}