package com.grupoProgramacion;
import com.badlogic.gdx.graphics.Texture;

public class GotaAgua extends ObjetoCayendo {

    public GotaAgua(Texture textura, float x, float y, float velocidad) {
        super(textura, x, y, velocidad);
    }

    @Override
    public void alImpactar(Tarro tarro) {
        // Logica de mecanicas: suman puntos
        tarro.sumarPuntos(10); 
    }
}