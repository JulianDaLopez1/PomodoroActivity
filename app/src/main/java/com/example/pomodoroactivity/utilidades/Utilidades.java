package com.example.pomodoroactivity.utilidades;

public class Utilidades {

    //Constantes campos tabla USUARIO
    public static final String TABLA_USUARIO="usuarios";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_USUARIO="usuario";
    public static final String CAMPO_PASSWORD="password";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+"("+
            CAMPO_NOMBRE+" TEXT, "+CAMPO_USUARIO+" TEXT, "+CAMPO_PASSWORD+" TEXT)";

    public static final String TABLA_TAREA="tareas";
    public static final String CAMPO_NOMBRET="nombre";
    public static final String CAMPO_TIEMPO="tiempo";
    public static final String CAMPO_POMODORO="pomodoro";
    public static final String CAMPO_USUARIOT="usuario";

    public static final String CREAR_TABLA_TAREA="CREATE TABLE "+TABLA_TAREA+"("+
            CAMPO_NOMBRET+" TEXT, "+CAMPO_TIEMPO+" INTEGER, "+CAMPO_POMODORO+" INTEGER, "+CAMPO_USUARIOT+" TEXT," +
            "FOREIGN KEY ("+CAMPO_USUARIOT+") REFERENCES "+TABLA_USUARIO+"("+CAMPO_USUARIO+"))";

}
