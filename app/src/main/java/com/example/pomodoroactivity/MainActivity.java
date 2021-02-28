package com.example.pomodoroactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, "bd_promodoro", null, 1);
    }

    //Método del botón login
    public void login(View view){
        Intent pantallaLogin = new Intent(this, ActividadLogin.class);
        startActivity(pantallaLogin);
    }

    //Método del boton registro
    public void registro(View view){
        Intent pantallaRegistro = new Intent(this, ActividadRegistro.class);
        startActivity(pantallaRegistro);
    }

}