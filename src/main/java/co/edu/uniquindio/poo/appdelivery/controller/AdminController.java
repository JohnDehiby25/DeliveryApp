package co.edu.uniquindio.poo.appdelivery.controller;

import co.edu.uniquindio.poo.appdelivery.HelloApplication;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.*;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.TipoDisponibilidad;
import co.edu.uniquindio.poo.appdelivery.service.facade.AdministradorFacade;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.RepartidorMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminController {

    @FXML private TableView<UsuarioDTO> tablaUsuarios;
    @FXML private TableColumn<UsuarioDTO, String> colIdUsuario;
    @FXML private TableColumn<UsuarioDTO, String> colNombreUsuario;
    @FXML private TableColumn<UsuarioDTO, String> colEmailUsuario;
    @FXML private TableColumn<UsuarioDTO, String> colTelefonoUsuario;

    @FXML private TextField txtIdUsuario;
    @FXML private TextField txtNombreUsuario;
    @FXML private TextField txtTelefonoUsuario;
    @FXML private TextField txtEmailUsuario;

    @FXML private TableView<RepartidorDTO> tablaRepartidores;
    @FXML private TableColumn<RepartidorDTO, String> colIdRepartidor;
    @FXML private TableColumn<RepartidorDTO, String> colNombreRepartidor;
    @FXML private TableColumn<RepartidorDTO, String> colDocumento;
    @FXML private TableColumn<RepartidorDTO, String> colDisponibilidad;
    @FXML private TableColumn<RepartidorDTO, Number> colZona;

    @FXML private TextField txtNombreRepartidor;
    @FXML private TextField txtIdRepartidor;
    @FXML private TextField txtTelefonoRepartidor;
    @FXML private TextField txtEmailRepartidor;
    @FXML private TextField txtDocumentoRepartidor;
    @FXML private TextField txtZonaCobertura;
    @FXML private ComboBox<String> cbDisponibilidad;

    @FXML private TableView<EnvioDTO> tablaEnviosPendientes;
    @FXML private TableColumn<EnvioDTO, Number> colIdEnvioPend;
    @FXML private TableColumn<EnvioDTO, String> colOrigenPend;
    @FXML private TableColumn<EnvioDTO, String> colDestinoPend;

    @FXML private TableView<RepartidorDTO> tablaRepartidoresDisp;
    @FXML private TableColumn<RepartidorDTO, String> colDocDisp;
    @FXML private TableColumn<RepartidorDTO, String> colNombreDisp;
    @FXML private TableColumn<RepartidorDTO, Number> colZonaDisp;

    @FXML private TextField txtFiltroZona;
    @FXML private ComboBox<String> cbFiltroDisponibilidad;
    @FXML private TableView<RepartidorDTO> tablaRepartidoresFiltrados;
    @FXML private TableColumn<RepartidorDTO, String> colDocFiltro;
    @FXML private TableColumn<RepartidorDTO, String> colNombreFiltro;
    @FXML private TableColumn<RepartidorDTO, String> colDispFiltro;
    @FXML private TableColumn<RepartidorDTO, Number> colZonaFiltro;

    @FXML private TextArea txtAreaReporte;

    @FXML private TextArea txtAreaPagos;
    @FXML private TextField txtIdPagoConsulta;

    private AdministradorFacade adminFacade;

    public void setAdministradorFacade(AdministradorFacade facade) {
        this.adminFacade = facade;
        cargarDatos();
    }

    @FXML
    private void initialize() {
        configurarTablasUsuarios();
        configurarTablasRepartidores();
        configurarTablasAsignaciones();
        configurarTablasFiltros();

        // Combos
        if (cbDisponibilidad != null) {
            cbDisponibilidad.setItems(FXCollections.observableArrayList(
                    "ACTIVO", "INACTIVO", "ENRUTA"
            ));
            cbDisponibilidad.setValue("ACTIVO");
        }

        if (cbFiltroDisponibilidad != null) {
            cbFiltroDisponibilidad.setItems(FXCollections.observableArrayList(
                    "ACTIVO", "INACTIVO", "ENRUTA"
            ));
        }
    }

    private void configurarTablasUsuarios() {
        if (colIdUsuario != null) colIdUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        if (colNombreUsuario != null) colNombreUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        if (colEmailUsuario != null) colEmailUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmail()));
        if (colTelefonoUsuario != null) colTelefonoUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
    }

    private void configurarTablasRepartidores() {
        if (colIdRepartidor != null) colIdRepartidor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        if (colNombreRepartidor != null) colNombreRepartidor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        if (colDocumento != null) colDocumento.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDocumento()));
        if (colDisponibilidad != null) colDisponibilidad.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getTipoDisponibilidad() != null ? c.getValue().getTipoDisponibilidad().toString() : "N/A"
        ));
        if (colZona != null) colZona.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getZonaCobertura()));
    }

    private void configurarTablasAsignaciones() {
        if (colIdEnvioPend != null) colIdEnvioPend.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIdEnvio()));
        if (colOrigenPend != null) colOrigenPend.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getOrigen() != null ? c.getValue().getOrigen().getCiudad() : ""
        ));
        if (colDestinoPend != null) colDestinoPend.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getDestino() != null ? c.getValue().getDestino().getCiudad() : ""
        ));

        if (colDocDisp != null) colDocDisp.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDocumento()));
        if (colNombreDisp != null) colNombreDisp.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        if (colZonaDisp != null) colZonaDisp.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getZonaCobertura()));
    }

    private void configurarTablasFiltros() {
        if (colDocFiltro != null) colDocFiltro.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDocumento()));
        if (colNombreFiltro != null) colNombreFiltro.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        if (colDispFiltro != null) colDispFiltro.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getTipoDisponibilidad() != null ? c.getValue().getTipoDisponibilidad().toString() : ""
        ));
        if (colZonaFiltro != null) colZonaFiltro.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getZonaCobertura()));
    }

    private void cargarDatos() {
        if (adminFacade == null) return;
        cargarUsuarios();
        cargarRepartidores();
        cargarEnviosPendientes();
        cargarRepartidoresDisponibles();
    }

    private void cargarUsuarios() {
        if (tablaUsuarios != null && adminFacade != null) {
            var lista = adminFacade.obtenerTodosUsuarios();
            tablaUsuarios.setItems(FXCollections.observableArrayList(lista));
        }
    }

    private void cargarRepartidores() {
        if (tablaRepartidores != null && adminFacade != null) {
            var lista = adminFacade.obtenerRepartidoresDisponibles();
            tablaRepartidores.setItems(FXCollections.observableArrayList(lista));
        }
    }

    private void cargarEnviosPendientes() {
        if (tablaEnviosPendientes != null) {
            var envioService = new co.edu.uniquindio.poo.appdelivery.service.EnvioService(
                    HelloApplication.getUsuarioService(),
                    HelloApplication.getAdminService()
            );
            var todos = envioService.obtenerTodosLosEnvios();
            var pendientes = todos.stream()
                    .filter(e -> e.getIdRepartidor() == null || e.getIdRepartidor().isEmpty())
                    .toList();
            tablaEnviosPendientes.setItems(FXCollections.observableArrayList(pendientes));
        }
    }

    private void cargarRepartidoresDisponibles() {
        if (tablaRepartidoresDisp != null && adminFacade != null) {
            var lista = adminFacade.obtenerRepartidoresDisponibles();
            tablaRepartidoresDisp.setItems(FXCollections.observableArrayList(lista));
        }
    }


    @FXML
    private void onRegistrarUsuario() {
        if (adminFacade == null) return;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(txtIdUsuario.getText());
        dto.setNombre(txtNombreUsuario.getText());
        dto.setTelefono(txtTelefonoUsuario.getText());
        dto.setEmail(txtEmailUsuario.getText());

        UsuarioDTO creado = adminFacade.registrarUsuario(dto);
        if (creado != null) {
            cargarUsuarios();
            limpiarCamposUsuario();
            mostrarInfo("Usuario registrado exitosamente");
        } else {
            mostrarError("No se pudo registrar el usuario");
        }
    }

    @FXML
    private void onEliminarUsuario() {
        UsuarioDTO seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un usuario");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar usuario " + seleccionado.getNombre() + "?",
                ButtonType.YES, ButtonType.NO);
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                boolean eliminado = HelloApplication.getAdminService()
                        .eliminarUsuario(seleccionado.getId());
                if (eliminado) {
                    cargarUsuarios();
                    mostrarInfo("Usuario eliminado");
                } else {
                    mostrarError("No se pudo eliminar el usuario");
                }
            }
        });
    }

    @FXML
    private void onActualizarUsuario() {
        UsuarioDTO seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un usuario");
            return;
        }

        String nuevoTelefono = txtTelefonoUsuario.getText();
        String nuevoEmail = txtEmailUsuario.getText();

        boolean actualizado = HelloApplication.getAdminService()
                .actualizarInfoUsuario(seleccionado.getId(), nuevoTelefono, nuevoEmail);

        if (actualizado) {
            cargarUsuarios();
            mostrarInfo("Usuario actualizado");
        } else {
            mostrarError("No se pudo actualizar");
        }
    }


    @FXML
    private void onRegistrarRepartidor() {
        if (adminFacade == null) return;

        try {
            RepartidorDTO dto = new RepartidorDTO();
            dto.setNombre(txtNombreRepartidor.getText());
            dto.setId(txtIdRepartidor.getText());
            dto.setTelefono(txtTelefonoRepartidor.getText());
            dto.setEmail(txtEmailRepartidor.getText());
            dto.setDocumento(txtDocumentoRepartidor.getText());
            dto.setZonaCobertura(Integer.parseInt(txtZonaCobertura.getText()));

            String disponibilidad = cbDisponibilidad.getValue();
            dto.setTipoDisponibilidad(TipoDisponibilidad.valueOf(disponibilidad));

            RepartidorDTO creado = adminFacade.registrarRepartidor(dto);
            if (creado != null) {
                cargarRepartidores();
                cargarRepartidoresDisponibles();
                limpiarCamposRepartidor();
                mostrarInfo("Repartidor registrado exitosamente");
            } else {
                mostrarError("No se pudo registrar el repartidor");
            }
        } catch (NumberFormatException e) {
            mostrarError("Zona de cobertura debe ser un número");
        }
    }

    @FXML
    private void onEliminarRepartidor() {
        RepartidorDTO seleccionado = tablaRepartidores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un repartidor");
            return;
        }

        boolean eliminado = HelloApplication.getAdminService()
                .eliminarRepartidor(seleccionado.getDocumento());

        if (eliminado) {
            cargarRepartidores();
            cargarRepartidoresDisponibles();
            mostrarInfo("Repartidor eliminado");
        } else {
            mostrarError("No se pudo eliminar (puede tener envíos asignados)");
        }
    }

    @FXML
    private void onCambiarDisponibilidad() {
        RepartidorDTO seleccionado = tablaRepartidores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un repartidor");
            return;
        }

        String nuevaDisp = cbDisponibilidad.getValue();
        boolean actualizado = HelloApplication.getAdminService()
                .actualizarEstadoRepartidor(
                        seleccionado.getDocumento(),
                        TipoDisponibilidad.valueOf(nuevaDisp)
                );

        if (actualizado) {
            cargarRepartidores();
            cargarRepartidoresDisponibles();
            mostrarInfo("Disponibilidad actualizada");
        }
    }


    @FXML
    private void onAsignarEnvio() {
        EnvioDTO envioSel = tablaEnviosPendientes.getSelectionModel().getSelectedItem();
        RepartidorDTO repSel = tablaRepartidoresDisp.getSelectionModel().getSelectedItem();

        if (envioSel == null || repSel == null) {
            mostrarError("Selecciona un envío y un repartidor");
            return;
        }

        boolean asignado = HelloApplication.getAdminService()
                .asignarEnvio(envioSel.getIdEnvio(), repSel.getDocumento());

        if (asignado) {
            cargarEnviosPendientes();
            mostrarInfo("Envío asignado a " + repSel.getNombre());
        } else {
            mostrarError("No se pudo asignar");
        }
    }

    @FXML
    private void onReasignarEnvio() {
        // Similar pero con un envío ya asignado
        mostrarInfo("Funcionalidad de reasignación disponible");
    }


    @FXML
    private void onFiltrarPorZona() {
        try {
            int zona = Integer.parseInt(txtFiltroZona.getText());
            var repartidores = HelloApplication.getAdminService().filtrarRepartidoresPorZona(zona);

            // USAR TU MAPPER EN LUGAR DE CONVERSIÓN MANUAL
            var dtos = repartidores.stream()
                    .map(RepartidorMapper::toDTO)  // ← Usa tu mapper
                    .toList();

            if (tablaRepartidoresFiltrados != null) {
                tablaRepartidoresFiltrados.setItems(FXCollections.observableArrayList(dtos));
            }
        } catch (NumberFormatException e) {
            mostrarError("Zona debe ser un número");
        }
    }

    @FXML
    private void onFiltrarPorDisponibilidad() {
        String disp = cbFiltroDisponibilidad.getValue();
        if (disp == null || disp.isEmpty()) {
            mostrarError("Selecciona una disponibilidad");
            return;
        }

        var repartidores = HelloApplication.getAdminService()
                .filtrarRepartidoresPorDisponibilidad(TipoDisponibilidad.valueOf(disp));

        // USAR TU MAPPER
        var dtos = repartidores.stream()
                .map(RepartidorMapper::toDTO)  // ← Usa tu mapper
                .toList();

        if (tablaRepartidoresFiltrados != null) {
            tablaRepartidoresFiltrados.setItems(FXCollections.observableArrayList(dtos));
        }
    }

    @FXML
    private void onGenerarReporteGeneral() {
        if (txtAreaReporte != null) {
            String reporte = HelloApplication.getAdminService().generarReporteGeneral();
            txtAreaReporte.setText(reporte);
        }
    }

    @FXML
    private void onGenerarReporteRepartidores() {
        if (txtAreaReporte != null) {
            String reporte = HelloApplication.getAdminService().generarReporteRepartidores();
            txtAreaReporte.setText(reporte);
        }
    }

    @FXML
    private void onReporteDisponibilidad() {
        if (txtAreaReporte != null) {
            String reporte = HelloApplication.getAdminService().reporteDisponibilidadRepartidores();
            txtAreaReporte.setText(reporte);
        }
    }

    @FXML
    private void onVerEnviosPendientes() {
        if (txtAreaReporte != null) {
            String reporte = HelloApplication.getAdminService().visualizarEnviosPendientes();
            txtAreaReporte.setText(reporte);
        }
    }


    @FXML
    private void onConsultarComprobante() {
        String idPago = txtIdPagoConsulta != null ? txtIdPagoConsulta.getText() : "";
        if (idPago.isEmpty()) {
            mostrarError("Ingresa un ID de pago");
            return;
        }

        // Mostrar comprobante en el TextArea
        if (txtAreaPagos != null) {
            HelloApplication.getAdminService().mostrarComprobantePago(idPago);
            txtAreaPagos.setText("Comprobante mostrado en consola (revisar logs)");
        }
    }


    @FXML
    private void onIrAUsuario() {
        UsuarioDTO usuarioDemo = new UsuarioDTO();
        usuarioDemo.setId("user001");
        usuarioDemo.setNombre("Usuario Demo");
        usuarioDemo.setTelefono("3009876543");
        usuarioDemo.setEmail("user@demo.com");

        HelloApplication.mostrarVistaUsuario(usuarioDemo);
    }

    @FXML
    private void onIrARepartidor() {
        HelloApplication.mostrarVistaRepartidor("REP001");
    }


    private void limpiarCamposUsuario() {
        txtIdUsuario.clear();
        txtNombreUsuario.clear();
        txtTelefonoUsuario.clear();
        txtEmailUsuario.clear();
    }

    private void limpiarCamposRepartidor() {
        txtNombreRepartidor.clear();
        txtIdRepartidor.clear();
        txtTelefonoRepartidor.clear();
        txtEmailRepartidor.clear();
        txtDocumentoRepartidor.clear();
        txtZonaCobertura.clear();
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
