package di.portafoliou6;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorMenu implements Initializable {

    @FXML
    private CheckBox nivel1;

    @FXML
    private CheckBox nivel2;

    @FXML
    private Button empezar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nivel1.selectedProperty().addListener((observable, oldValue, newValue) -> iniciar());
        nivel2.selectedProperty().addListener((observable, oldValue, newValue) -> iniciar());


    }


    public void iniciar() {
        if (nivel1.isSelected()) {
            cambiarEscenaNivel1();
        } else {
            cambiarEscenaNivel2();
        }
    }

    private void cambiarEscenaNivel1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mastermind.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));


            stage.show();

            ((Stage) nivel1.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void cambiarEscenaNivel2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mastermind2.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));


            stage.show();

            ((Stage) nivel2.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
