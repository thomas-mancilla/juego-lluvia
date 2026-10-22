package com.grupoProgramacion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoCayendo implements Colisionable {
    // Encapsulamiento (GM-5): Atributos protegidos para las clases hijas
    protected Rectangle hitbox;
    protected Texture textura;
    protected float velocidad;

    public ObjetoCayendo(Texture textura, float x, float y, float velocidad) {
        this.textura = textura;
        this.velocidad = velocidad;
        this.hitbox = new Rectangle(x, y, 64, 64); // Tamano base asumido
    }

    // Comportamiento base de fisica y renderizado
    public void actualizarPosicion(float delta) {
        hitbox.y -= velocidad * delta;
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, hitbox.x, hitbox.y);
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
}