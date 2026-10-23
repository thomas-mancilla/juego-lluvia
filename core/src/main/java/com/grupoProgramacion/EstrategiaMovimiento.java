package com.grupoProgramacion;
import com.badlogic.gdx.math.Rectangle;

public interface EstrategiaMovimiento {
    void mover(Rectangle hitbox, float velocidad, float delta);
}