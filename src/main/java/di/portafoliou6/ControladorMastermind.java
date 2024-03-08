package di.portafoliou6;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class ControladorMastermind {
    private Juego juego = new Juego();
    private HBox[] filas;
    private HBox fila;
    private int indiceFilaActual;

    @FXML
    private ComboBox<Color> coloresComboBox;

    @FXML
    private HBox fila1;

    @FXML
    private HBox fila2;

    @FXML
    private HBox fila3;

    @FXML
    private HBox fila4;

    @FXML
    private HBox fila5;

    @FXML
    private TextArea resultado;


    //--------MÉTODOS--------

    @FXML
    void initialize() {
        filas = new HBox[]{fila1,fila2,fila3,fila4,fila5};
        for (HBox fila : filas) {
            configurarEventosCirculosEnFila(fila);
        }

        // Configuración del ComboBox
        coloresComboBox.getItems().addAll(
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.YELLOW,
                Color.PINK
        );

        coloresComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Color color, boolean empty) {
                super.updateItem(color, empty);

                if (empty || color == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(getColorName(color));
                    setGraphic(colorCelda(color));
                }
            }
        });

        coloresComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Color color, boolean empty) {
                super.updateItem(color, empty);

                if (empty || color == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(getColorName(color));
                    setGraphic(colorCelda(color));
                }
            }
        });

        // Configurar eventos para círculos en cada fila
        configurarEventosCirculosEnFila(fila1);
        configurarEventosCirculosEnFila(fila2);
        configurarEventosCirculosEnFila(fila3);
        configurarEventosCirculosEnFila(fila4);
        configurarEventosCirculosEnFila(fila5);

        resultado.setWrapText(true);

    }

    private String getColorName(Color color) {
        if (color.equals(Color.RED)) {
            return "Rojo";
        } else if (color.equals(Color.GREEN)) {
            return "Verde";
        } else if (color.equals(Color.BLUE)) {
            return "Azul";
        } else if (color.equals(Color.YELLOW)) {
            return "Amarillo";
        } else if (color.equals(Color.PINK)) {
            return "Rosa";
        } else {
            return "";
        }
    }

    //Para las opciones del ComboBox
    private javafx.scene.shape.Rectangle colorCelda(Color color) {
        javafx.scene.shape.Rectangle rect = new javafx.scene.shape.Rectangle(20, 20);
        rect.setFill(color);
        return rect;
    }


    //Para pintar el círculo del color que elija el usuario
    private void configurarEventosCirculo(Circle circulo, HBox fila) {
        circulo.setOnMouseClicked(event -> {
            Color color = coloresComboBox.getValue();
            pintarCirculo(color, circulo, fila);
            this.fila = fila;
        });
    }



    //Para avanzar por círculos y filas
    private void configurarEventosCirculosEnFila(HBox fila) {
        for (int i = 0; i < fila.getChildren().size(); i++) {
            Circle circulo = (Circle) fila.getChildren().get(i);
            configurarEventosCirculo(circulo, fila);
        }
    }



    //Para un círculo concreto
    private void pintarCirculo(Color color, Circle circulo, HBox fila) {
        circulo.setFill(color);
        circulo.setDisable(true);

        int indiceCirculo = fila.getChildren().indexOf(circulo);
        if (indiceCirculo < fila.getChildren().size() - 1) {
            Circle siguienteCirculo = (Circle) fila.getChildren().get(indiceCirculo + 1);
            siguienteCirculo.setDisable(false);
        }
    }

    //Para obtener la combinación que acaba de introducir el usuario
    private List<Color> obtenerCombinacionFila(HBox fila) {
        List<Color> combinacion = new ArrayList<>();
        for (int i = 0; i < fila.getChildren().size(); i++) {
            Circle circulo = (Circle) fila.getChildren().get(i);
            combinacion.add((Color) circulo.getFill());
        }
        return combinacion;
    }


    //Métodos para comprobar la combinación

    @FXML
    void comprobarCombinacion() {
        // Utilizar indiceFilaActual que ahora es un miembro de la clase
        List<Color> combinacionUsuario = obtenerCombinacionFila((HBox) fila.getParent().getChildrenUnmodifiable().get(indiceFilaActual));

        String resultadoComprobacion = juego.comprobarCombinacionUsuario(combinacionUsuario);
        resultado.setText(resultadoComprobacion);

        if (!juego.esCombinacionCorrecta(combinacionUsuario)) {
            // Pasa a la siguiente fila
            avanzarSiguienteFila();
        }
    }


    //Para cambiar de fila
    private void avanzarSiguienteFila() {
        // Deshabilitar los círculos de la fila actual
        for (int i = 0; i < fila.getChildren().size(); i++) {
            Circle circulo = (Circle) fila.getChildren().get(i);
            circulo.setDisable(true);
        }

        // Obtener la siguiente fila
        indiceFilaActual = (fila.getParent()).getChildrenUnmodifiable().indexOf(fila);
        if (indiceFilaActual < filas.length - 1) {
            // Si hay más filas disponibles, avanza a la siguiente
            fila = filas[indiceFilaActual + 1];
        }

        // Habilitar el primer círculo de la siguiente fila
        if (fila != null && !fila.getChildren().isEmpty()) {
            Circle primerCirculo = (Circle) fila.getChildren().get(0);
            primerCirculo.setDisable(false);
        }

    }
}
