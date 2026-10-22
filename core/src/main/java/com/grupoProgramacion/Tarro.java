package com.grupoProgramacion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tarro {
    // Encapsulamiento (GM-5): Atributos privados
    private Rectangle hitbox;
    private Texture textura;
    private int puntuacion;
    private boolean gameOver;

    public Tarro(Texture textura, float x, float y) {
        this.textura = textura;
        this.hitbox = new Rectangle(x, y, 64, 64); // Tamano estandar de la cubeta
        this.puntuacion = 0;
        this.gameOver = false;
    }

    public void actualizarPosicion(float delta) {
        if (gameOver) return; // Si es Game Over, no se mueve

        float velocidad = 400 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            hitbox.x -= velocidad;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            hitbox.x += velocidad;
        }

        // Evitar que el tarro salga de los limites de la pantalla (asumiendo 800px de ancho)
        if (hitbox.x < 0) hitbox.x = 0;
        if (hitbox.x > 800 - 64) hitbox.x = 800 - 64;
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, hitbox.x, hitbox.y);
    }

    // Metodos llamados por las clases hijas de ObjetoCayendo a traves de la interfaz Colisionable
    public void sumarPuntos(int puntos) {
        this.puntuacion += puntos; // Las gotas de lluvia suman puntos[cite: 4]
    }

    public void activarGameOver() {
        this.gameOver = true; // Si toca una esponja, el agua se seca y es Game Over inmediato[cite: 4]
    }

    // Getters
    public Rectangle getHitbox() { return hitbox; }
    public int getPuntuacion() { return puntuacion; }
    public boolean isGameOver() { return gameOver; }
}