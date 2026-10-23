package com.grupoProgramacion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoCayendo implements Colisionable {
    private Rectangle hitbox;
    private Texture textura;
    private float velocidad;
    private EstrategiaMovimiento estrategiaMovimiento; 

    public ObjetoCayendo(Texture textura, float x, float y, float velocidad, EstrategiaMovimiento estrategia) {
        this.textura = textura;
        this.velocidad = velocidad;
        this.estrategiaMovimiento = estrategia;
        this.hitbox = new Rectangle(x, y, 64, 64);
    }

    public final void ejecutarCiclo(float delta, Tarro tarro, SpriteBatch batch) {
        moverse(delta);
        verificarLimites();
        dibujar(batch);
        
        if (hitbox.overlaps(tarro.getHitbox())) {
            reproducirSonidoImpacto();
            alImpactar(tarro);
        }
    }

    private void moverse(float delta) {
        if (estrategiaMovimiento != null) {
            estrategiaMovimiento.mover(hitbox, velocidad, delta);
        }
    }

    private void verificarLimites() {
        if (hitbox.y < -64) {
            
        }
    }
    
    private void reproducirSonidoImpacto() {
        
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
    
    public Rectangle getHitbox() { return hitbox; }
}
