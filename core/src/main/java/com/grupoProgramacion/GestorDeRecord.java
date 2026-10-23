package com.grupoProgramacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GestorDeRecord {
    private static GestorDeRecord instancia;
    private int recordGlobal;
    private final Preferences preferencias;

    private GestorDeRecord() {
        preferencias = Gdx.app.getPreferences("juego-lluvia");
        recordGlobal = preferencias.getInteger("recordGlobal", 0);
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
            preferencias.putInteger("recordGlobal", recordGlobal);
            preferencias.flush();
        }
    }

    public int getRecordGlobal() {
        return recordGlobal;
    }
}
