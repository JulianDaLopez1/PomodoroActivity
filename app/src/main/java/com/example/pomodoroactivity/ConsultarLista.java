package com.example.pomodoroactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pomodoroactivity.entidades.Tarea;
import com.example.pomodoroactivity.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarLista extends AppCompatActivity {

    ListView listView;

    TextView textContador;

    ArrayList<String> listaInfo;
    ArrayList<Tarea> listaTareas;

    ConexionSQLiteHelper conn;

    String datoNombre, datoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista);

        datoNombre = getIntent().getStringExtra("nombrePersona");
        datoUsuario = getIntent().getStringExtra("usuarioPersona");
        textContador = (TextView) findViewById(R.id.textContador);

        conn=new ConexionSQLiteHelper(getApplicationContext(), "bd_promodoro", null, 1);

        listView = (ListView) findViewById(R.id.listView);

        consultarLista();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInfo);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l1) {
                String tiemp= String.valueOf(listaTareas.get(pos).getTiempo());
                iniciarContar(tiemp);
            }
        });

    }

    private void iniciarContar(String tiemp) {
        int cont = Integer.valueOf(tiemp)/25;
        for(int i=0;i<cont;i++){
            int minu = Integer.valueOf(25)*60*1000;
            long valor = minu;
            CountDownTimer cuenta = new CountDownTimer(valor, 1000) {

                @Override

                public void onTick(long l) {
                    long tiempo = l/1000;
                    int minutos = (int) (tiempo/60);
                    long segundos = tiempo % 60;
                    String minutos_mostrar = String.format("%02d", minutos);
                    String segundos_mostrar = String.format("%02d", segundos);
                    textContador.setText(""+minutos_mostrar+" : "+segundos_mostrar);
                }

                @Override
                public void onFinish() {
                    textContador.setText("Descanso:");
                    CountDownTimer cuenta2 = new CountDownTimer(5*60*1000, 1000) {

                        @Override

                        public void onTick(long l) {
                            long tiempo2 = l/1000;
                            int minutos2 = (int) (tiempo2/60);
                            long segundos2 = tiempo2 % 60;
                            String minutos_mostrar2 = String.format("%02d", minutos2);
                            String segundos_mostrar2 = String.format("%02d", segundos2);
                            textContador.setText("Descanso: "+minutos_mostrar2+" : "+segundos_mostrar2);
                        }
                        @Override
                        public void onFinish() {
                        }
                    }.start();
                }
            }.start();
        }
    }

    private void consultarLista() {
        SQLiteDatabase db= conn.getReadableDatabase();

        Tarea tarea=null;
        listaTareas= new ArrayList<Tarea>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_TAREA+" WHERE "+Utilidades.CAMPO_USUARIOT+"= '"+datoUsuario+"'", null);
        while (cursor.moveToNext()){
            tarea=new Tarea();
            tarea.setNombre(cursor.getString(0));
            tarea.setTiempo(cursor.getInt(1));
            tarea.setPomodoros(cursor.getInt(2));
            listaTareas.add(tarea);
        }

        obtenerLista();
        db.close();
    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for (int i=0; i<listaTareas.size();i++){
            listaInfo.add(listaTareas.get(i).getNombre()+ " - "+listaTareas.get(i).getTiempo()+
                    " - "+listaTareas.get(i).getPomodoros());
        }
    }

    public void Regresar(View view) {
        Intent AnteriorTareas = new Intent(this, ActividadesUsuario.class);
        AnteriorTareas.putExtra("nombrePersona",datoNombre);
        AnteriorTareas.putExtra("usuarioPersona",datoUsuario);
        startActivity(AnteriorTareas);
    }
}