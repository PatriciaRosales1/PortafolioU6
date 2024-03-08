module di.portafoliou6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens di.portafoliou6 to javafx.fxml;
    exports di.portafoliou6;
}