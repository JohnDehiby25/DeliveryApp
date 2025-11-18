module co.edu.uniquindio.poo.appdelivery {
    requires javafx.controls;
    requires javafx.fxml;
    requires co.edu.uniquindio.poo.appdelivery;
    requires java.desktop;


    opens co.edu.uniquindio.poo.appdelivery to javafx.fxml;
    exports co.edu.uniquindio.poo.appdelivery;
    exports co.edu.uniquindio.poo.appdelivery.controller;
    opens co.edu.uniquindio.poo.appdelivery.controller to javafx.fxml;

}