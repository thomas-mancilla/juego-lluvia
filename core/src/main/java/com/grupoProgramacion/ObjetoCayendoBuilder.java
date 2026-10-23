package com.grupoProgramacion;
import com.badlogic.gdx.graphics.Texture;

public class ObjetoCayendoBuilder {
    private Texture textura;
    private float x;
    private float y;
    private float velocidad;
    private EstrategiaMovimiento estrategia;

    public ObjetoCayendoBuilder setTextura(Texture textura) {
        this.textura = textura;
        return this;
    }

    public ObjetoCayendoBuilder setPosicion(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ObjetoCayendoBuilder setVelocidad(float velocidad) {
        this.velocidad = velocidad;
        return this;
    }

    public ObjetoCayendoBuilder setEstrategia(EstrategiaMovimiento estrategia) {
        this.estrategia = estrategia;
        return this;
    }

    public GotaAgua buildGota() {
        return new GotaAgua(textura, x, y, velocidad, estrategia);
    }

    public EsponjaEnemiga buildEsponja() {
        return new EsponjaEnemiga(textura, x, y, velocidad, estrategia);
    }
}