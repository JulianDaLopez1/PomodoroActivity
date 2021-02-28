package com.example.pomodoroactivity.entidades;

public class Tarea {
    private String nombre;
    private int tiempo;
    private int pomodoros;
    private String usuario;

    public Tarea() {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.pomodoros = pomodoros;
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getPomodoros() {
        return pomodoros;
    }

    public void setPomodoros(int pomodoros) {
        this.pomodoros = pomodoros;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
