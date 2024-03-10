package di.portafoliou6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    private Juego juego = new Juego();

    @Override
    public void start(Stage stage) throws IOException {
        //Crea una variable llamada vista que lee el fichero fxml
        FXMLLoader vista = new FXMLLoader(Juego.class.getResource("menu.fxml"));
        //Carga la anterior vista
        Scene scene = new Scene(vista.load(), 530, 413);
        //

        juego.crearCombinacion();

        stage.setTitle("Mastermind");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
