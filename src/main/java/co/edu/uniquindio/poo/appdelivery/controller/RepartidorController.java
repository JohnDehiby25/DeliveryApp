package co.edu.uniquindio.poo.appdelivery.controller;

import co.edu.uniquindio.poo.appdelivery.HelloApplication;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.EnvioDTO;
import co.edu.uniquindio.poo.appdelivery.model.envio.EstadoEnvio;
import co.edu.uniquindio.poo.appdelivery.service.EnvioService;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.Paths;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RepartidorController {

    @FXML private TableView<EnvioDTO> tablaEnvios;
    @FXML private TableColumn<EnvioDTO, Number> colIdEnvio;
    @FXML private TableColumn<EnvioDTO, String> colEstado;
    @FXML private TableColumn<EnvioDTO, String> colOrigen;
    @FXML private TableColumn<EnvioDTO, String> colDestino;
    @FXML private TableColumn<EnvioDTO, Number> colCosto;

    @FXML private ComboBox<String> cbNuevoEstado;
    @FXML private TextArea txtAreaDetalles;


    @FXML private TextField txtTipoIncidencia;
    @FXML private TextField txtDescripcionIncidencia;

    @FXML private Label lblDocumentoRepartidor;
    @FXML private Label lblTotalEnvios;

    private EnvioService envioService;
    private String documentoRepartidorActual;

    public void setEnvioService(EnvioService envioService, String documentoRepartidor) {
        this.envioService = envioService;
        this.documentoRepartidorActual = documentoRepartidor;

        if (lblDocumentoRepartidor != null) {
            lblDocumentoRepartidor.setText("Repartidor: " + documentoRepartidor);
        }

        cargarEnviosAsignados();
    }

    @FXML
    private void initialize() {
        configurarTablas();

        // ComboBox de estados
        if (cbNuevoEstado != null) {
            cbNuevoEstado.setItems(FXCollections.observableArrayList(
                    "SOLICITADO", "ENRUTA", "ENTREGADO", "CANCELADO"
            ));
            cbNuevoEstado.setValue("ENRUTA");
        }
    }

    private void configurarTablas() {
        if (colIdEnvio != null) {
            colIdEnvio.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIdEnvio()));
        }
        if (colEstado != null) {
            colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado()));
        }
        if (colOrigen != null) {
            colOrigen.setCellValueFactory(c -> new SimpleStringProperty(
                    c.getValue().getOrigen() != null ? c.getValue().getOrigen().getCiudad() : ""));
        }
        if (colDestino != null) {
            colDestino.setCellValueFactory(c -> new SimpleStringProperty(
                    c.getValue().getDestino() != null ? c.getValue().getDestino().getCiudad() : ""));
        }
        if (colCosto != null) {
            colCosto.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getCosto()));
        }
    }

    private void cargarEnviosAsignados() {
        if (envioService == null || tablaEnvios == null) return;

        var todos = envioService.obtenerTodosLosEnvios();
        var asignados = todos.stream()
                .filter(e -> documentoRepartidorActual != null &&
                        documentoRepartidorActual.equals(e.getIdRepartidor()))
                .toList();

        tablaEnvios.setItems(FXCollections.observableArrayList(asignados));

        if (lblTotalEnvios != null) {
            lblTotalEnvios.setText("Total envíos asignados: " + asignados.size());
        }
    }

    // ========== MÉTODOS DE CAMBIO DE ESTADO ==========

    @FXML
    private void onCambiarEstado() {
        EnvioDTO seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un envío");
            return;
        }

        String nuevoEstadoStr = cbNuevoEstado.getValue();
        EstadoEnvio nuevoEstado = EstadoEnvio.valueOf(nuevoEstadoStr);

        // Usar el método del servicio que activa el Observer
        envioService.actualizarEstado(seleccionado.getIdEnvio(), nuevoEstado);
        cargarEnviosAsignados();
        mostrarInfo("Estado actualizado a " + nuevoEstado + "\n\n(Notificaciones enviadas a usuarios)");
    }

    @FXML
    private void onMarcarEnRuta() {
        cambiarEstadoSeleccionado(EstadoEnvio.ENRUTA);
    }

    @FXML
    private void onMarcarEntregado() {
        cambiarEstadoSeleccionado(EstadoEnvio.ENTREGADO);
    }

    private void cambiarEstadoSeleccionado(EstadoEnvio nuevoEstado) {
        EnvioDTO seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un envío");
            return;
        }

        // Usar el método del servicio que activa el patrón Observer
        envioService.actualizarEstado(seleccionado.getIdEnvio(), nuevoEstado);
        cargarEnviosAsignados();
        mostrarInfo("Estado actualizado a " + nuevoEstado + "\n\n✓ Notificaciones enviadas automáticamente");
    }

    // ========== MÉTODOS DE DETALLES ==========

    @FXML
    private void onVerDetalles() {
        EnvioDTO seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un envío");
            return;
        }

        String detalles = envioService.obtenerDetallesEnvio(seleccionado.getIdEnvio());
        if (txtAreaDetalles != null) {
            txtAreaDetalles.setText(detalles);
        }
    }


    @FXML
    private void onRegistrarIncidencia() {
        EnvioDTO seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un envío");
            return;
        }

        String tipo = txtTipoIncidencia != null ? txtTipoIncidencia.getText().trim() : "";
        String descripcion = txtDescripcionIncidencia != null ? txtDescripcionIncidencia.getText().trim() : "";

        if (tipo.isEmpty() || descripcion.isEmpty()) {
            mostrarError("Completa tipo y descripción de la incidencia");
            return;
        }

        try {
            // Obtener el envío real del servicio
            var adminService = HelloApplication.getAdminService();
            var envios = adminService.getAdministrador().getListEnvios();

            co.edu.uniquindio.poo.appdelivery.model.envio.Envio envioEntity = null;
            for (var e : envios) {
                if (e.getIdEnvio() == seleccionado.getIdEnvio()) {
                    envioEntity = e;
                    break;
                }
            }

            if (envioEntity != null) {
                co.edu.uniquindio.poo.appdelivery.model.incidencia.Incidencia incidencia =
                        new co.edu.uniquindio.poo.appdelivery.model.incidencia.Incidencia();

                incidencia.setIncidencia("INC-" + System.currentTimeMillis());
                incidencia.setDescripcion(descripcion);
                incidencia.setFecha(java.time.LocalDateTime.now());
                incidencia.setTipoIncidencia(tipo);
                incidencia.setEstado(true);
                incidencia.setEnvio(envioEntity);

                envioEntity.agregarIncidencias(incidencia);

                adminService.getAdministrador().getListIncidencias().add(incidencia);

                mostrarInfo("✓ Incidencia registrada exitosamente\n\n" +
                        "Tipo: " + tipo + "\n" +
                        "Descripción: " + descripcion + "\n" +
                        "Envío #" + seleccionado.getIdEnvio());

                if (txtTipoIncidencia != null) txtTipoIncidencia.clear();
                if (txtDescripcionIncidencia != null) txtDescripcionIncidencia.clear();

                onVerDetalles();
            } else {
                mostrarError("No se encontró el envío en el sistema");
            }

        } catch (Exception e) {
            mostrarError("Error al registrar incidencia:\n" + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void onVerIncidencias() {
        EnvioDTO seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un envío");
            return;
        }

        // Consultar incidencias del envío
        var adminService = HelloApplication.getAdminService();
        var envios = adminService.getAdministrador().getListEnvios();

        for (var e : envios) {
            if (e.getIdEnvio() == seleccionado.getIdEnvio()) {
                var incidencias = e.obtenerIncidencias();

                StringBuilder reporte = new StringBuilder();
                reporte.append("╔════════════════════════════════════════════╗\n");
                reporte.append("║  INCIDENCIAS DEL ENVÍO #").append(e.getIdEnvio()).append("\n");
                reporte.append("╚════════════════════════════════════════════╝\n\n");

                if (incidencias.isEmpty()) {
                    reporte.append("✓ No hay incidencias registradas.\n");
                    reporte.append("\nEl envío se encuentra sin problemas reportados.\n");
                } else {
                    reporte.append("Total de incidencias: ").append(incidencias.size()).append("\n\n");

                    for (int i = 0; i < incidencias.size(); i++) {
                        var inc = incidencias.get(i);
                        reporte.append("════════════════════════════════════════════\n");
                        reporte.append("Incidencia #").append(i + 1).append("\n");
                        reporte.append("════════════════════════════════════════════\n");
                        reporte.append(inc.obtenerResumen()).append("\n\n");
                    }
                }

                if (txtAreaDetalles != null) {
                    txtAreaDetalles.setText(reporte.toString());
                }
                break;
            }
        }
    }

    // ========== OTROS MÉTODOS ==========

    @FXML
    private void onRefrescar() {
        cargarEnviosAsignados();
        mostrarInfo("Lista de envíos actualizada");
    }

    @FXML
    private void onVolverAdmin() {
        HelloApplication.cambiarVista(Paths.ADMIN_VIEW);
    }


    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
        alert.showAndWait();
    }
}
