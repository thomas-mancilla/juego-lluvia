package com.grupoProgramacion;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    
    // Texturas
    private Texture texturaTarro;
    private Texture texturaGota;
    private Texture texturaEsponja;
    
    // Objetos del juego
    private Tarro tarro;
    private Array<ObjetoCayendo> objetosEnPantalla;
    private ObjetoCayendoBuilder builder;
    
    // Control de tiempo para generar caidas
    private float tiempoUltimoObjeto;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // 
        
        // Cargar imagenes desde la carpeta assets
        texturaTarro = new Texture("tarro.png");
        texturaGota = new Texture("gota.png");
        texturaEsponja = new Texture("esponja.png");
        
        // Inicializar el tarro en el centro de la parte inferior
        tarro = new Tarro(texturaTarro, 800 / 2 - 32, 20);
        
        objetosEnPantalla = new Array<>();
        builder = new ObjetoCayendoBuilder();
        
        generarObjetoCayendo();
    }

    private void generarObjetoCayendo() {
        float xAleatorio = MathUtils.random(0, 800 - 64);
        float velocidadProgresiva = 200;
        builder.setPosicion(xAleatorio, 600);
        builder.setVelocidad(velocidadProgresiva);

        if (MathUtils.random(1, 10) <= 8) {
            builder.setTextura(texturaGota);
            // Alternar estrategia vertical o diagonal aleatoriamente
            builder.setEstrategia(MathUtils.randomBoolean() ? new MovimientoVertical() : new MovimientoDiagonal());
            objetosEnPantalla.add(builder.buildGota());
        } else {
            builder.setTextura(texturaEsponja);
            builder.setEstrategia(new MovimientoVertical());
            objetosEnPantalla.add(builder.buildEsponja());
        }
        
        tiempoUltimoObjeto = 0;
    }

    @Override
    public void render() {
        //Logica 
        float delta = Gdx.graphics.getDeltaTime();
        
        if (!tarro.isGameOver()) {
            tarro.actualizarPosicion(delta);
            tiempoUltimoObjeto += delta;
            
            // Generar un nuevo objeto cada segundo
            if (tiempoUltimoObjeto > 1f) {
                generarObjetoCayendo();
            }
        }

        // Limpiar pantalla con color azul oscuro
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        tarro.dibujar(batch);
        
        // Iterar objetos en reversa para poder eliminarlos de la lista sin causar errores
        for (int i = objetosEnPantalla.size - 1; i >= 0; i--) {
            ObjetoCayendo obj = objetosEnPantalla.get(i);
            
            if (!tarro.isGameOver()) {
                obj.ejecutarCiclo(delta, tarro, batch);
            } else {
                // Si pierde, solo se dibujan congelados
                batch.draw(obj.textura, obj.getHitbox().x, obj.getHitbox().y);
            }
            
            // Eliminar si choca o si sale por debajo de la pantalla
            if (obj.getHitbox().y < -64 || obj.getHitbox().overlaps(tarro.getHitbox())) {
                objetosEnPantalla.removeIndex(i);
            }
        }
        
        // Dibujar interfaz
        font.draw(batch, "Puntos: " + tarro.getPuntuacion(), 20, 580);
        font.draw(batch, "Récord Global: " + GestorDeRecord.getInstancia().getRecordGlobal(), 20, 560);
        
        if (tarro.isGameOver()) {
            GestorDeRecord.getInstancia().actualizarRecord(tarro.getPuntuacion());
            font.draw(batch, "GAME OVER! El agua se secó", 300, 300);
        }
        
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        texturaTarro.dispose();
        texturaGota.dispose();
        texturaEsponja.dispose();
    }
}