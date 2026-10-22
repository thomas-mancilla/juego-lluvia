package com.grupoProgramacion;
import com.badlogic.gdx.graphics.Texture;

public class EsponjaEnemiga extends ObjetoCayendo {

    public EsponjaEnemiga(Texture textura, float x, float y, float velocidad) {
        super(textura, x, y, velocidad);
    }

    @Override
    public void alImpactar(Tarro tarro) {
        // Logica de mecanicas: el agua se seca y es Game Over inmediato[cite: 4]
        tarro.activarGameOver(); 
    }
}