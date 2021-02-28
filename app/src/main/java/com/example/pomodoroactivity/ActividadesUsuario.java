package com.example.pomodoroactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActividadesUsuario extends AppCompatActivity {

    private TextView tv1;
    String datoNombre, datoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_usuario);

        tv1 = (TextView)findViewById(R.id.tv1);

        datoNombre = getIntent().getStringExtra("nombrePersona");
        datoUsuario = getIntent().getStringExtra("usuarioPersona");
        tv1.setText("Hola " + datoNombre);
    }

    //Método del boton Creacion Tarea
    public void NuevaTarea(View view){
        Intent NuevaTarea = new Intent(this, CreacionTarea.class);
        NuevaTarea.putExtra("nombrePersona",datoNombre);
        NuevaTarea.putExtra("usuarioPersona",datoUsuario);
        startActivity(NuevaTarea);
    }

    public void ConsultarTareas(View view){
        Intent ConsultarTarea = new Intent(this, ConsultarLista.class);
        ConsultarTarea.putExtra("nombrePersona",datoNombre);
        ConsultarTarea.putExtra("usuarioPersona",datoUsuario);
        startActivity(ConsultarTarea);
    }

    //Método para cerrar sesion
    public void CerrarSesion(View view){
        Intent CerrarSesion = new Intent(this, MainActivity.class);
        startActivity(CerrarSesion);
    }
}