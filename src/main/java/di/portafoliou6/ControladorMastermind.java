package di.portafoliou6;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

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
}
