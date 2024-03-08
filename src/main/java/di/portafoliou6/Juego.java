package di.portafoliou6;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego {
    int nivel;
    int colores;
    List<Color> combinacion;
    List<combinacionFila> intentos;
    int limite;
    boolean iniciado;

    //Constructor con valores
    public Juego() {
        this.nivel = 4;
        this.colores = 5;
        this.combinacion = new ArrayList<>();
        crearCombinacion();
        this.intentos = new ArrayList<>();
        this.limite = 6;
        this.iniciado = true;
    }

    public int intentoActual() {
        return this.intentos.size() + 1;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "nivel=" + nivel +
                ", colores=" + colores +
                ", combinacion=" + combinacion +
                ", intentos=" + intentos +
                '}';
    }

    //--------MÉTODOS--------

    //Para crear la combinación de manera aleatoria. Puede tener colores repetidos
    public void crearCombinacion() {
        Random aleatorio = new Random();
        for (int i = 0; i < this.nivel; i++) {
            int indiceColor = aleatorio.nextInt(this.colores);
            //this.combinacion.add(obtenerColorPorIndice(indiceColor));
        }
    }

    //Representación de una fila
    public static class combinacionFila {
        private List<Color> colores;
        private List<Color> coloresIncorrectos;

        public combinacionFila(List<Color> colores, List<Color> coloresIncorrectos) {
            this.colores = colores;
            this.coloresIncorrectos = coloresIncorrectos;
        }

        @Override
        public String toString() {
            return "Colores: " + colores +
                    "\nColores incorrectos: " + coloresIncorrectos;
        }
    }
}
