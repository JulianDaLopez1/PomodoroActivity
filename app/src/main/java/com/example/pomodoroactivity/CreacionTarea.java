package com.example.pomodoroactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pomodoroactivity.utilidades.Utilidades;

public class CreacionTarea extends AppCompatActivity {

    EditText campoTiempo, campoNombreT;
    TextView campoPomodoro, campoError;
    int nPomodoros;
    String datoNombre, datoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_tarea);
        datoNombre = getIntent().getStringExtra("nombrePersona");
        datoUsuario = getIntent().getStringExtra("usuarioPersona");
        campoTiempo = (EditText) findViewById(R.id.editTextTime);
        campoNombreT = (EditText) findViewById(R.id.editTextTextPersonName);
        campoPomodoro = (TextView) findViewById(R.id.txt_pomodoros);
        campoError = (TextView) findViewById(R.id.txt_error3);

        campoTiempo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(campoTiempo.getText().toString()=="") {
                    campoPomodoro.setText("");
                }else{
                    try {
                        nPomodoros = Integer.parseInt(campoTiempo.getText().toString()) / 25;
                        campoPomodoro.setText(String.valueOf(nPomodoros));
                    }catch (Exception e){
                        campoPomodoro.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    public void RegistrarTareas (View view){
        if(campoTiempo.getText().toString().isEmpty() || campoNombreT.getText().toString().isEmpty()) {
            campoError.setText("Debe diligenciar todos los campos.");
        }else if(Integer.parseInt(campoTiempo.getText().toString())<25) {
            campoError.setText("El tiempo debe ser mayor a 25min.");
        }else{
            campoError.setText("");
                registrarTarea();
            Intent AnteriorTareas = new Intent(this, ActividadesUsuario.class);
            AnteriorTareas.putExtra("nombrePersona",datoNombre);
            AnteriorTareas.putExtra("usuarioPersona",datoUsuario);
            startActivity(AnteriorTareas);
            }

        }

    private void registrarTarea() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_promodoro", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRET, campoNombreT.getText().toString());
        values.put(Utilidades.CAMPO_TIEMPO, campoTiempo.getText().toString());
        values.put(Utilidades.CAMPO_POMODORO, campoPomodoro.getText().toString());
        values.put(Utilidades.CAMPO_USUARIOT, datoUsuario);

        Long nombreResultante = db.insert(Utilidades.TABLA_TAREA, Utilidades.CAMPO_NOMBRET, values);

        db.close();

    }

    //Método para regresar a Actividades
    public void AnteriorTareas (View view){
        Intent AnteriorTareas = new Intent(this, ActividadesUsuario.class);
        AnteriorTareas.putExtra("nombrePersona",datoNombre);
        AnteriorTareas.putExtra("usuarioPersona",datoUsuario);
        startActivity(AnteriorTareas);
    }
}