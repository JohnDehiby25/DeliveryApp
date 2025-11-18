package co.edu.uniquindio.poo.appdelivery;

import co.edu.uniquindio.poo.appdelivery.controller.AdminController;
import co.edu.uniquindio.poo.appdelivery.controller.UsuarioController;
import co.edu.uniquindio.poo.appdelivery.controller.RepartidorController;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.AdministradorDTO;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.UsuarioDTO;
import co.edu.uniquindio.poo.appdelivery.service.AdministradorService;
import co.edu.uniquindio.poo.appdelivery.service.EnvioService;
import co.edu.uniquindio.poo.appdelivery.service.UsuarioService;
import co.edu.uniquindio.poo.appdelivery.service.facade.AdministradorFacade;
import co.edu.uniquindio.poo.appdelivery.service.facade.UsuarioFacade;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage primaryStage;
    public static HelloApplication app;

    private static AdministradorService adminService;
    private static UsuarioService usuarioService;

    @Override
    public void start(Stage stage) throws Exception {
        app = this;
        primaryStage = stage;

        inicializarServicios();

        AdministradorDTO adminDemo = new AdministradorDTO(
                "Admin Principal",
                "admin001",
                "3001234567",
                "admin@delivery.com"
        );

        mostrarVistaAdmin(adminDemo);

        primaryStage.setTitle("AppDelivery - Sistema de Gestión");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    private void inicializarServicios() {
        adminService = new AdministradorService();
        usuarioService = new UsuarioService();
    }


    /**
     * Cambia a la vista de ADMINISTRADOR e inyecta la facade.
     */
    public static void mostrarVistaAdmin(AdministradorDTO adminDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(Paths.ADMIN_VIEW));
            Parent root = loader.load();

            AdminController controller = loader.getController();
            AdministradorFacade facade = new AdministradorFacade(adminDTO, adminService);
            controller.setAdministradorFacade(facade);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("AppDelivery - Panel Administrador");
        } catch (IOException e) {
            System.err.println("Error al cargar vista de Admin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Cambia a la vista de USUARIO e inyecta la facade.
     */
    public static void mostrarVistaUsuario(UsuarioDTO usuarioDTO) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(Paths.USUARIO_VIEW));
            Parent root = loader.load();

            UsuarioController controller = loader.getController();
            UsuarioFacade facade = new UsuarioFacade(usuarioDTO, adminService);
            controller.setUsuarioFacade(facade);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("AppDelivery - Panel Usuario");
        } catch (IOException e) {
            System.err.println("Error al cargar vista de Usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Cambia a la vista de REPARTIDOR.
     */
    public static void mostrarVistaRepartidor(String documentoRepartidor) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(Paths.REPARTIDOR_VIEW));
            Parent root = loader.load();

            RepartidorController controller = loader.getController();
            EnvioService envioService = new EnvioService(usuarioService, adminService);
            controller.setEnvioService(envioService, documentoRepartidor);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("AppDelivery - Panel Repartidor");
        } catch (IOException e) {
            System.err.println("Error al cargar vista de Repartidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void cambiarVista(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Error al cargar vista: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static AdministradorService getAdminService() {
        return adminService;
    }

    public static UsuarioService getUsuarioService() {
        return usuarioService;
    }
}

