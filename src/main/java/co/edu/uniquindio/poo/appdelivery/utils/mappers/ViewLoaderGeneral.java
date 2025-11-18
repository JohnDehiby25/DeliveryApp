package co.edu.uniquindio.poo.appdelivery.utils.mappers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewLoaderGeneral {
    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void load(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewLoaderGeneral.class.getResource("/co/edu/uniquindio/poo/appdelivery/view/" + rutaFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
