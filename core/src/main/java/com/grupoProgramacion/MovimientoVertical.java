package com.grupoProgramacion;
import com.badlogic.gdx.math.Rectangle;

public class MovimientoVertical implements EstrategiaMovimiento {
    @Override
    public void mover(Rectangle hitbox, float velocidad, float delta) {
        hitbox.y -= velocidad * delta; // Permite la caida de forma vertical[cite: 7, 9]
    }
}