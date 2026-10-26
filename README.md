# Juego Lluvia

## Descripción del proyecto

Juego Lluvia es un videojuego desarrollado en Java utilizando la biblioteca libGDX. El jugador controla un tarro que debe recoger gotas de agua que caen desde la parte superior de la pantalla y evitar las esponjas enemigas. El objetivo es obtener la mayor puntuación posible sin perder la partida.

## Objetivo del juego

El jugador debe mover el tarro horizontalmente para recoger las gotas de agua y acumular puntos. Al mismo tiempo, debe evitar las esponjas, ya que colisionar con una de ellas provoca el fin de la partida.

## Controles

- **Flecha izquierda:** mover el tarro hacia la izquierda.
- **Flecha derecha:** mover el tarro hacia la derecha.
- **Barra espaciadora:** reiniciar la partida después de Game Over.

## Reglas del juego

- Cada gota de agua recogida suma **10 puntos**.
- Las esponjas son objetos enemigos que el jugador debe evitar.
- Si una esponja colisiona con el tarro, se activa el estado **Game Over**.
- Los objetos pueden caer verticalmente o desplazarse en diagonal.
- La velocidad de aparición y movimiento de los objetos aumenta conforme avanza el tiempo de juego, haciendo que la partida sea progresivamente más difícil.
- La pantalla muestra la puntuación actual y el récord global.

## Récord global

El juego mantiene un récord global que se actualiza cuando la puntuación de una partida supera el récord anterior. El valor se guarda utilizando `Preferences` de libGDX, de modo que puede recuperarse en futuras ejecuciones.

## Estructura del proyecto

Las principales clases y responsabilidades son:

- **Main:** inicializa el juego, administra el ciclo de actualización y dibujo, genera los objetos y controla el estado de la partida.
- **Tarro:** representa al jugador, controla su movimiento, su hitbox, la puntuación y el estado Game Over.
- **ObjetoCayendo:** clase abstracta que contiene el comportamiento común de los objetos que caen, como el movimiento, el dibujo y la comprobación de colisiones.
- **GotaAgua:** representa una gota y suma puntos al colisionar con el tarro.
- **EsponjaEnemiga:** representa una esponja y provoca Game Over al colisionar con el tarro.
- **Colisionable:** define el comportamiento que deben implementar los objetos que reaccionan a una colisión.
- **EstrategiaMovimiento:** permite definir distintas formas de movimiento.
- **MovimientoVertical:** implementa el desplazamiento vertical.
- **MovimientoDiagonal:** implementa el desplazamiento diagonal.
- **ObjetoCayendoBuilder:** facilita la construcción de gotas y esponjas mediante la configuración de sus propiedades.
- **GestorDeRecord:** administra el récord global utilizando el patrón Singleton.

## Tecnologías utilizadas

- Java.
- libGDX para el desarrollo del videojuego.
- Gradle para la configuración y construcción del proyecto.

## Ejecución del proyecto

Cómo ejecutar el proyecto

Requisitos previos

Antes de ejecutar el videojuego, se necesita:

Tener instalado un JDK compatible con la configuración de Gradle del proyecto. (JAVA 11)

Disponer de los archivos del proyecto Juego Lluvia, incluyendo el archivo gradlew.bat y los archivos de configuración de Gradle.

Utilizar Windows para seguir las instrucciones de terminal indicadas a continuación.

Ejecución en Windows

Descargar o clonar el repositorio del proyecto.

Descomprimir el archivo, si se descargó en formato ZIP.

Abrir una terminal dentro de la carpeta principal del proyecto, donde se encuentra el archivo gradlew.bat.

Ejecutar el siguiente comando para iniciar el videojuego:

gradlew.bat lwjgl3:run

Esperar a que Gradle complete las tareas necesarias y abra la ventana del videojuego.

Ejecución desde un entorno de desarrollo

También es posible importar el proyecto en un entorno compatible con Gradle, como NetBeans o IntelliJ IDEA. Una vez importado, se debe localizar la tarea run del módulo de escritorio lwjgl3 y ejecutarla.

## Patrones de diseño aplicados

- **Singleton:** centraliza la administración del récord global en `GestorDeRecord`.
- **Strategy:** permite utilizar distintas estrategias de movimiento mediante `EstrategiaMovimiento`.
- **Template Method:** define en `ObjetoCayendo` una secuencia común para mover, dibujar y comprobar colisiones.
- **Builder:** permite configurar y construir objetos mediante `ObjetoCayendoBuilder`.

## Estado del proyecto

Esta documentación describe las funcionalidades identificadas en el código fuente revisado. La ejecución y compilación deben comprobarse en el entorno de desarrollo antes de considerar el proyecto validado.
