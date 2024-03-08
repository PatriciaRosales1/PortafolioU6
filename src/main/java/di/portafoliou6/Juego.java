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


    //Para comprobar si es correcta
    public boolean esCombinacionCorrecta(List<Color> combinacionUsuario) {
        return combinacion.equals(combinacionUsuario);
    }


    //Para comprobar la combinación que introduce el usuario
    //Dice los que están bien y mal colocados
    //Muestra un mensaje de si has acertado o has fallado la combinación. Si la has fallado indica cual era la combinación correcta
    public String comprobarCombinacionUsuario(List<Color> combinacionUsuario) {
        int bienColocados = 0;
        int coloresIncorrectos = 0;

        List<Color> coloresNoBienColocados = new ArrayList<>(combinacion);
        List<Color> coloresBienColocados = new ArrayList<>();

        for (int i = 0; i < nivel; i++) {
            if (combinacion.get(i).equals(combinacionUsuario.get(i))) {
                bienColocados++;
                coloresBienColocados.add(combinacion.get(i));
                coloresNoBienColocados.remove(combinacion.get(i));
            } else if (coloresNoBienColocados.contains(combinacionUsuario.get(i))) {
                coloresNoBienColocados.remove(combinacionUsuario.get(i));
            }
        }

        coloresIncorrectos = coloresNoBienColocados.size();

        String resultadoComprobacion = "Bien colocados: " + bienColocados + "\nColores incorrectos: " + coloresIncorrectos;

        combinacionFila filaCombinacion = new combinacionFila(combinacionUsuario, coloresNoBienColocados);
        intentos.add(filaCombinacion);

        if (bienColocados == nivel) {
            resultadoComprobacion += "\n¡Felicidades! ¡Has acertado la combinación!";
            iniciado = false;

        } else if (intentoActual() >= limite) {
            resultadoComprobacion += "\n¡Lo siento! Has alcanzado el límite de intentos. La combinación correcta era: " + obtenerNombresColores(combinacion);
            iniciado = false;
        }

        return resultadoComprobacion;
    }
}
