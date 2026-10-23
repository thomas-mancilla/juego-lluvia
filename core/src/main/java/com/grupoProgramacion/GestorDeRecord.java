package com.grupoProgramacion;

public class GestorDeRecord {
    private static GestorDeRecord instancia; //Aplicacion de Singleton
    private int recordGlobal;

    private GestorDeRecord() {
        this.recordGlobal = 0; 
    }

    public static GestorDeRecord getInstancia() {
        if (instancia == null) {
            instancia = new GestorDeRecord();
        }
        return instancia;
    }

    public void actualizarRecord(int nuevaPuntuacion) {
        if (nuevaPuntuacion > recordGlobal) {
            recordGlobal = nuevaPuntuacion;
        }
    }

    public int getRecordGlobal() {
        return recordGlobal;
    }
}