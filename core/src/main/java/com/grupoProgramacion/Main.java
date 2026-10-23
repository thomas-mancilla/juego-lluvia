package com.grupoProgramacion;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;

    private Texture texturaTarro;
    private Texture texturaGota;
    private Texture texturaEsponja;

    private Tarro tarro;
    private Array<ObjetoCayendo> objetosEnPantalla;
    private ObjetoCayendoBuilder builder;

    private float tiempoUltimoObjeto;
    private float tiempoTotalJuego;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        texturaTarro = cargarTextura("tarro", Color.BROWN);
        texturaGota = cargarTextura("gota", Color.CYAN);
        texturaEsponja = cargarTextura("esponja", Color.YELLOW);

        builder = new ObjetoCayendoBuilder();
        iniciarPartida();
    }

    private Texture cargarTextura(String nombreBase, Color colorRespaldo) {
        String[] carpetas = {"", "assets/", "../assets/"};
        String[] extensiones = {".jpg", ".jpeg", ".png", ".jpg.jpg", ".png.jpg", ".png.png"};

        for (String carpeta : carpetas) {
            for (String ext : extensiones) {
                String ruta = carpeta + nombreBase + ext;
                FileHandle archivo = Gdx.files.internal(ruta).exists() ? Gdx.files.internal(ruta) : 
                                     (Gdx.files.local(ruta).exists() ? Gdx.files.local(ruta) : null);

                if (archivo != null) {
                    try {
                        Pixmap original = new Pixmap(archivo);
                        Pixmap escalado = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
                        escalado.drawPixmap(original, 0, 0, original.getWidth(), original.getHeight(), 0, 0, 64, 64);
                        Texture textura = new Texture(escalado);
                        original.dispose();
                        escalado.dispose();
                        return textura;
                    } catch (Exception e) {
                        System.out.println("Formato incompatible en: " + ruta + " -> " + e.getMessage());
                    }
                }
            }
        }

        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(colorRespaldo);
        pixmap.fill();
        Texture texturaGenerada = new Texture(pixmap);
        pixmap.dispose();
        return texturaGenerada;
    }

    private void iniciarPartida() {
        float anchoPantalla = Gdx.graphics.getWidth();
        tarro = new Tarro(texturaTarro, anchoPantalla / 2f - 32f, 20f);
        objetosEnPantalla = new Array<>();
        tiempoUltimoObjeto = 0f;
        tiempoTotalJuego = 0f;
        generarObjetoCayendo();
    }

    private void generarObjetoCayendo() {
        float anchoPantalla = Gdx.graphics.getWidth();
        float altoPantalla = Gdx.graphics.getHeight();

        float xAleatorio = MathUtils.random(0, anchoPantalla - 64);
        float velocidadProgresiva = 180f + (tiempoTotalJuego * 10f);

        builder.setPosicion(xAleatorio, altoPantalla);
        builder.setVelocidad(velocidadProgresiva);

        if (MathUtils.random(1, 10) <= 8) {
            builder.setTextura(texturaGota);
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
        float delta = Gdx.graphics.getDeltaTime();
        float altoPantalla = Gdx.graphics.getHeight();

        if (!tarro.isGameOver()) {
            tarro.actualizarPosicion(delta);
            tiempoUltimoObjeto += delta;
            tiempoTotalJuego += delta;

            if (tiempoUltimoObjeto > 0.8f) {
                generarObjetoCayendo();
            }
        } else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                iniciarPartida();
            }
        }

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        tarro.dibujar(batch);

        for (int i = objetosEnPantalla.size - 1; i >= 0; i--) {
            ObjetoCayendo obj = objetosEnPantalla.get(i);

            if (!tarro.isGameOver()) {
                obj.ejecutarCiclo(delta, tarro, batch);
            } else {
                batch.draw(obj.textura, obj.getHitbox().x, obj.getHitbox().y, obj.getHitbox().width, obj.getHitbox().height);
            }

            if (obj.getHitbox().y < -64 || obj.getHitbox().overlaps(tarro.getHitbox())) {
                objetosEnPantalla.removeIndex(i);
            }
        }

        font.draw(batch, "Puntos: " + tarro.getPuntuacion(), 20, altoPantalla - 20);
        font.draw(batch, "Record Global: " + GestorDeRecord.getInstancia().getRecordGlobal(), 20, altoPantalla - 40);

        if (tarro.isGameOver()) {
            GestorDeRecord.getInstancia().actualizarRecord(tarro.getPuntuacion());
            font.draw(batch, "GAME OVER! El agua se seco", 220, altoPantalla / 2f + 10);
            font.draw(batch, "Presiona ESPACIO para reintentar", 200, altoPantalla / 2f - 15);
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