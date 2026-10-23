package com.grupoProgramacion;
import com.badlogic.gdx.graphics.Texture;

public class GotaAgua extends ObjetoCayendo {

    public GotaAgua(Texture textura, float x, float y, float velocidad, EstrategiaMovimiento estrategia) {
        super(textura, x, y, velocidad, estrategia);
    }

    @Override
    public void alImpactar(Tarro tarro) {
        tarro.sumarPuntos(10); 
    }
}