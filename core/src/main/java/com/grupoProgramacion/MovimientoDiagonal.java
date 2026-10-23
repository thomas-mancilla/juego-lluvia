package com.grupoProgramacion;
import com.badlogic.gdx.math.Rectangle;

public class MovimientoDiagonal implements EstrategiaMovimiento {
    @Override
    public void mover(Rectangle hitbox, float velocidad, float delta) {
        hitbox.y -= velocidad * delta;
        hitbox.x += (velocidad / 2) * delta; // Permite la caida de forma diagonal
    }
}