package co.edu.uniquindio.poo.appdelivery.controller;

import co.edu.uniquindio.poo.appdelivery.HelloApplication;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.*;
import co.edu.uniquindio.poo.appdelivery.model.pago.*;
import co.edu.uniquindio.poo.appdelivery.service.facade.UsuarioFacade;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.Paths;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class UsuarioController {

    @FXML private TableView<DireccionDTO> tablaDirecciones;
    @FXML private TableColumn<DireccionDTO, String> colIdDir;
    @FXML private TableColumn<DireccionDTO, String> colAlias;
    @FXML private TableColumn<DireccionDTO, String> colCiudad;
    @FXML private TableColumn<DireccionDTO, String> colCalle;

    @FXML private TextField txtAlias;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtCalle;

    @FXML private TableView<EnvioDTO> tablaEnvios;
    @FXML private TableColumn<EnvioDTO, Number> colIdEnvio;
    @FXML private TableColumn<EnvioDTO, String> colEstado;
    @FXML private TableColumn<EnvioDTO, String> colOrigenEnvio;
    @FXML private TableColumn<EnvioDTO, String> colDestinoEnvio;
    @FXML private TableColumn<EnvioDTO, Number> colCosto;

    @FXML private ComboBox<DireccionDTO> cbOrigen;
    @FXML private ComboBox<DireccionDTO> cbDestino;
    @FXML private TextField txtPeso;
    @FXML private TextField txtAlto;
    @FXML private TextField txtAncho;
    @FXML private TextField txtLargo;

    @FXML private TableView<EnvioDTO> tablaEnviosPago;
    @FXML private TableColumn<EnvioDTO, Number> colIdEnvioPago;
    @FXML private TableColumn<EnvioDTO, Number> colCostoPago;
    @FXML private TableColumn<EnvioDTO, String> colEstadoPago;

    @FXML private ComboBox<String> cbMetodoPago;
    @FXML private TextField txtMontoPago;
    @FXML private TextArea txtAreaComprobante;

    @FXML private Label lblNombreUsuario;
    @FXML private Label lblTotalEnvios;

    private UsuarioFacade usuarioFacade;

    public void setUsuarioFacade(UsuarioFacade facade) {
        this.usuarioFacade = facade;
        cargarDatos();
        mostrarInfoUsuario();
    }

    @FXML
    private void initialize() {
        configurarTablas();

        if (cbMetodoPago != null) {
            cbMetodoPago.setItems(FXCollections.observableArrayList(
                    "Efectivo", "Tarjeta Crédito", "Nequi"
            ));
            cbMetodoPago.setValue("Efectivo");
        }
    }

    private void configurarTablas() {

        if (colIdDir != null) {
            colIdDir.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getIdDireccion())));
        }
        if (colAlias != null) {
            colAlias.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAlias()));
        }
        if (colCiudad != null) {
            colCiudad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCiudad()));
        }
        if (colCalle != null) {
            colCalle.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCalle()));
        }

        if (colIdEnvio != null) {
            colIdEnvio.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIdEnvio()));
        }
        if (colEstado != null) {
            colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado()));
        }
        if (colOrigenEnvio != null) {
            colOrigenEnvio.setCellValueFactory(c -> new SimpleStringProperty(
                    c.getValue().getOrigen() != null ? c.getValue().getOrigen().getCiudad() : ""));
        }
        if (colDestinoEnvio != null) {
            colDestinoEnvio.setCellValueFactory(c -> new SimpleStringProperty(
                    c.getValue().getDestino() != null ? c.getValue().getDestino().getCiudad() : ""));
        }
        if (colCosto != null) {
            colCosto.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getCosto()));
        }

        if (colIdEnvioPago != null) {
            colIdEnvioPago.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIdEnvio()));
        }
        if (colCostoPago != null) {
            colCostoPago.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getCosto()));
        }
        if (colEstadoPago != null) {
            colEstadoPago.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado()));
        }
    }

    private void cargarDatos() {
        if (usuarioFacade == null) return;
        cargarDirecciones();
        cargarEnvios();
        poblarCombos();
    }

    private void mostrarInfoUsuario() {
        if (usuarioFacade == null) return;
        UsuarioDTO usuario = usuarioFacade.getUsuarioActual();

        if (lblNombreUsuario != null) {
            lblNombreUsuario.setText("Usuario: " + usuario.getNombre());
        }
        if (lblTotalEnvios != null) {
            lblTotalEnvios.setText("Total envíos: " + usuario.getTotalEnvios());
        }
    }

    private void cargarDirecciones() {
        if (tablaDirecciones != null && usuarioFacade != null) {
            var lista = usuarioFacade.obtenerMisDirecciones();
            tablaDirecciones.setItems(FXCollections.observableArrayList(lista));
        }
    }

    private void cargarEnvios() {
        if (usuarioFacade == null) return;

        var lista = usuarioFacade.obtenerMisEnvios();

        if (tablaEnvios != null) {
            tablaEnvios.setItems(FXCollections.observableArrayList(lista));
        }
        if (tablaEnviosPago != null) {
            tablaEnviosPago.setItems(FXCollections.observableArrayList(lista));
        }
    }

    private void poblarCombos() {
        if (cbOrigen != null && cbDestino != null && usuarioFacade != null) {
            var dirs = FXCollections.observableArrayList(usuarioFacade.obtenerMisDirecciones());
            cbOrigen.setItems(dirs);
            cbDestino.setItems(dirs);

            cbOrigen.setCellFactory(param -> new ListCell<DireccionDTO>() {
                @Override
                protected void updateItem(DireccionDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getAlias() + " - " + item.getCiudad());
                }
            });
            cbOrigen.setButtonCell(new ListCell<DireccionDTO>() {
                @Override
                protected void updateItem(DireccionDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getAlias() + " - " + item.getCiudad());
                }
            });

            cbDestino.setCellFactory(param -> new ListCell<DireccionDTO>() {
                @Override
                protected void updateItem(DireccionDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getAlias() + " - " + item.getCiudad());
                }
            });
            cbDestino.setButtonCell(new ListCell<DireccionDTO>() {
                @Override
                protected void updateItem(DireccionDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getAlias() + " - " + item.getCiudad());
                }
            });
        }
    }

    @FXML
    private void onAgregarDireccion() {
        if (usuarioFacade == null) return;

        if (txtAlias.getText().isEmpty() || txtCiudad.getText().isEmpty() || txtCalle.getText().isEmpty()) {
            mostrarError("Completa todos los campos de la dirección");
            return;
        }

        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion((int) (System.currentTimeMillis() % Integer.MAX_VALUE));
        dto.setAlias(txtAlias.getText());
        dto.setCiudad(txtCiudad.getText());
        dto.setCalle(txtCalle.getText());

        boolean ok = usuarioFacade.agregarDireccion(dto);
        if (ok) {
            cargarDirecciones();
            poblarCombos();
            txtAlias.clear();
            txtCiudad.clear();
            txtCalle.clear();
            mostrarInfo("Dirección agregada exitosamente");
        } else {
            mostrarError("No se pudo agregar la dirección");
        }
    }

    @FXML
    private void onEliminarDireccion() {
        DireccionDTO seleccionada = tablaDirecciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarError("Selecciona una dirección");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar dirección '" + seleccionada.getAlias() + "'?",
                ButtonType.YES, ButtonType.NO);

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                // Eliminar directamente de la lista del usuario
                usuarioFacade.getUsuarioActual().getDirecciones().remove(seleccionada);
                cargarDirecciones();
                poblarCombos();
                mostrarInfo("Dirección eliminada");
            }
        });
    }


    @FXML
    private void onSolicitarEnvio() {
        if (usuarioFacade == null) return;

        DireccionDTO origen = cbOrigen.getValue();
        DireccionDTO destino = cbDestino.getValue();

        if (origen == null || destino == null) {
            mostrarError("Selecciona origen y destino");
            return;
        }

        try {
            double peso = Double.parseDouble(txtPeso.getText());
            double ancho = Double.parseDouble(txtAncho.getText());
            double largo = Double.parseDouble(txtLargo.getText());

            if (peso <= 0|| ancho <= 0 || largo <= 0) {
                mostrarError("Todas las dimensiones deben ser mayores a 0");
                return;
            }

            PaqueteDTO paquete = new PaqueteDTO();
            paquete.setPeso(peso);
            paquete.setAncho((int) ancho);
            paquete.setLargo((int) largo);

            EnvioDTO solicitud = new EnvioDTO();
            solicitud.setOrigen(origen);
            solicitud.setDestino(destino);
            solicitud.setPaquete(paquete);

            EnvioDTO creado = usuarioFacade.solicitarEnvio(solicitud);
            if (creado != null) {
                cargarEnvios();
                mostrarInfoUsuario();
                limpiarCamposEnvio();
                mostrarInfo("Envío solicitado exitosamente\n\n" +
                        "ID Envío: " + creado.getIdEnvio() + "\n" +
                        "Costo: $" + String.format("%.2f", creado.getCosto()) + "\n" +
                        "Estado: " + creado.getEstado());
            } else {
                mostrarError("No se pudo crear el envío");
            }
        } catch (NumberFormatException e) {
            mostrarError("Todos los campos numéricos deben ser válidos");
        }
    }

    @FXML
    private void onCancelarEnvio() {
        EnvioDTO seleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un envío");
            return;
        }

        if (!"SOLICITADO".equals(seleccionado.getEstado())) {
            mostrarError("Solo se pueden cancelar envíos en estado SOLICITADO");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Cancelar envío #" + seleccionado.getIdEnvio() + "?",
                ButtonType.YES, ButtonType.NO);

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                boolean cancelado = HelloApplication.getAdminService()
                        .eliminarEnvio(seleccionado.getIdEnvio());

                if (cancelado) {
                    cargarEnvios();
                    mostrarInfo("Envío cancelado exitosamente");
                } else {
                    mostrarError("No se pudo cancelar el envío");
                }
            }
        });
    }

    @FXML
    private void onRealizarPago() {
        EnvioDTO seleccionado = tablaEnviosPago.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un envío para pagar");
            return;
        }

        try {
            double monto = Double.parseDouble(txtMontoPago.getText());
            if (monto < seleccionado.getCosto()) {
                mostrarError("El monto debe cubrir el costo del envío ($" +
                        String.format("%.2f", seleccionado.getCosto()) + ")");
                return;
            }

            String metodoPago = cbMetodoPago.getValue();
            String idPago = "PAG-" + System.currentTimeMillis();

            Pago pago = null;

            if (metodoPago.equals("Efectivo")) {
                pago = new PagoEfectivo();
            } else if (metodoPago.equals("Tarjeta Crédito")) {
                pago = new PagoTarjetaCredito();
            } else if (metodoPago.equals("Nequi")) {
                pago = new PagoNequi();
            }

            if (pago == null) {
                mostrarError("Método de pago no válido");
                return;
            }

            pago.setIdPago(idPago);
            pago.setMonto(monto);
            pago.setEstadoPago(EstadoPago.APROBADO);

            String comprobante = pago.mostrarComprobantePago();

            if (txtAreaComprobante != null) {
                txtAreaComprobante.setText(comprobante);
            }

            HelloApplication.getAdminService().getAdministrador().getListPagos().add(pago);

            mostrarInfo("Pago procesado exitosamente\n\nID Pago: " + idPago +
                    "\nMétodo: " + metodoPago);
            txtMontoPago.clear();

        } catch (NumberFormatException e) {
            mostrarError("Monto inválido. Ingresa un número válido.");
        }
    }

    @FXML
    private void onConsultarIncidencias() {
        if (usuarioFacade == null) return;

        String reporte = HelloApplication.getAdminService()
                .consultarIncidenciasUsuario(usuarioFacade.getUsuarioActual().getId());

        if (txtAreaComprobante != null) {
            txtAreaComprobante.setText(reporte);
        }
    }


    @FXML
    private void onVolverAdmin() {
        HelloApplication.cambiarVista(Paths.ADMIN_VIEW);
    }


    private void limpiarCamposEnvio() {
        txtPeso.clear();
        txtAlto.clear();
        txtAncho.clear();
        txtLargo.clear();
        cbOrigen.setValue(null);
        cbDestino.setValue(null);
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

