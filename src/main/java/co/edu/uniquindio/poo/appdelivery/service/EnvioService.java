package co.edu.uniquindio.poo.appdelivery.service;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.EnvioDTO;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.envio.EstadoEnvio;
import co.edu.uniquindio.poo.appdelivery.model.envio.INotificacionObserver;
import co.edu.uniquindio.poo.appdelivery.model.envio.ServicioAdicional;
import co.edu.uniquindio.poo.appdelivery.model.incidencia.Incidencia;
import co.edu.uniquindio.poo.appdelivery.model.pago.Pago;

import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.EnvioMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class EnvioService {
    private List<Envio> envios;
    private UsuarioService usuarioService;
    private AdministradorService administradorService;

    public EnvioService(UsuarioService usuarioService,
                        AdministradorService administradorService) {
        this.envios = new ArrayList<>();
        this.usuarioService = usuarioService;
        this.administradorService = administradorService;
    }
    public List<EnvioDTO> obtenerTodosLosEnvios() {
        List<EnvioDTO> enviosDTO = new ArrayList<>();
        for (Envio envio : envios) {
            enviosDTO.add(EnvioMapper.toDTO(envio));
        }
        return enviosDTO;
    }
    /**
     * Busca un envío por ID y lo devuelve como DTO
     */
    public EnvioDTO buscarEnvioPorId(int idEnvio) {
        Envio envio = buscarEnvioEntity(idEnvio);
        return envio != null ? EnvioMapper.toDTO(envio) : null;
    }
    private Envio buscarEnvioEntity(int idEnvio) {
        for (Envio envio : envios) {
            if (envio.getIdEnvio() == idEnvio) {
                return envio;
            }
        }
        return null;
    }
    public boolean agregarEnvio(EnvioDTO envioDTO) {
        if (buscarEnvioEntity(envioDTO.getIdEnvio()) != null) {
            return false;
        }

        Usuario usuario = null;
        Repartidor repartidor = null;

        if (envioDTO.getIdUsuario() != null && !envioDTO.getIdUsuario().isEmpty()) {
            usuario = administradorService.buscarUsuarioPorId(envioDTO.getIdUsuario());
        }

        if (envioDTO.getIdRepartidor() != null && !envioDTO.getIdRepartidor().isEmpty()) {
            repartidor = administradorService.buscarRepartidorPorDocumento(envioDTO.getIdRepartidor());
        }

        Envio envio = EnvioMapper.toEntity(envioDTO, usuario, repartidor);
        envios.add(envio);
        return true;
    }

    public EnvioDTO actualizarEstado(int idEnvio, EstadoEnvio nuevoEstado) {
        Envio envio = buscarEnvioEntity(idEnvio);

        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser nulo");
        }
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }

        envio.setEstado(nuevoEstado);
        envio.notificarObserversNotificacion();

        return EnvioMapper.toDTO(envio);
    }



    // Métodos de Cálculo de Costos

    public double calcularCostoTotal(int idEnvio) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null) return 0.0;

        double costoTotal = envio.getCosto();

        if (envio.getServiciosAdicionales() != null) {
            for (ServicioAdicional servicio : envio.getServiciosAdicionales()) {
                costoTotal += servicio.getValorAsegurado();
            }
        }

        return costoTotal;
    }
    public boolean agregarServicioAdicional(int idEnvio, ServicioAdicional servicio) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null || servicio == null) {
            return false;
        }

        envio.getServiciosAdicionales().add(servicio);
        return true;
    }
    public boolean agregarIncidencia(int idEnvio, Incidencia incidencia) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null || incidencia == null) {
            return false;
        }

        envio.obtenerIncidencias().add(incidencia);
        return true;
    }

    public String obtenerDetallesEnvio(int idEnvio) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null) {
            return "Envío no encontrado";
        }

        StringBuilder detalles = new StringBuilder();
        detalles.append("=== DETALLES DEL ENVÍO ===\n");
        detalles.append("ID Envío: ").append(envio.getIdEnvio()).append("\n");
        detalles.append("Origen: ").append(envio.getOrigen() != null ? envio.getOrigen().toString() : "N/A").append("\n");
        detalles.append("Destino: ").append(envio.getDestino() != null ? envio.getDestino().toString() : "N/A").append("\n");
        detalles.append("Estado: ").append(envio.getEstado()).append("\n");
        detalles.append("Costo: $").append(envio.getCosto()).append("\n");
        detalles.append("Fecha Creación: ").append(envio.getFechaCreacion()).append("\n");
        detalles.append("Fecha Estimada Entrega: ").append(envio.getFechaEstimadaEntrega()).append("\n");
        detalles.append("Usuario: ").append(envio.getUsuario() != null ? envio.getUsuario().getNombre() : "N/A").append("\n");
        detalles.append("Repartidor: ").append(envio.getRepartidor() != null ? envio.getRepartidor().getNombre() : "No asignado").append("\n");

        if (envio.getServiciosAdicionales() != null && !envio.getServiciosAdicionales().isEmpty()) {
            detalles.append("\nServicios Adicionales:\n");
            for (ServicioAdicional servicio : envio.getServiciosAdicionales()) {
                detalles.append("  - ").append(servicio.getIdServicio()).append(": $").append(servicio.getValorAsegurado()).append("\n");
            }
        }

        detalles.append("\nCosto Total: $").append(calcularCostoTotal(idEnvio)).append("\n");

        return detalles.toString();
    }

    public Pago obtenerPagoPorFecha(int idEnvio, LocalDateTime fechaObtener) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null || envio.getPago() == null) {
            return null;
        }

        if (envio.getPago().getFechaPago().equals(fechaObtener)) {
            return envio.getPago();
        }
        return null;
    }

    // Métodos de Pago
    public boolean registrarPago(int idEnvio, Pago pago) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null || pago == null) {
            return false;
        }
        envio.setPago(pago);
        pago.setEnvio(envio);
        return true;
    }

    public void consultarComprobantePago(int idEnvio) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null) {
            System.out.println("Envío no encontrado.");
            return;
        }

        if (envio.getPago() != null) {
            System.out.println("=== COMPROBANTE DE PAGO ===");
            System.out.println("ID Envío: " + envio.getIdEnvio());
            System.out.println("Monto: $" + envio.getPago().getMonto());
            System.out.println("Método de Pago: " + envio.getPago().getMetodoPago());
            System.out.println("Fecha: " + envio.getPago().getFechaPago());
            System.out.println("Estado: " + envio.getPago().getEstadoPago());
            System.out.println("===========================");
        } else {
            System.out.println("No hay pago registrado para este envío.");
        }
    }

    // Métodos de Notificaciones (Patrón Observer)
    public boolean agregarObserver(int idEnvio, INotificacionObserver observer) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null || observer == null) {
            return false;
        }

        if (!envio.getListNotificaciones().contains(observer)) {
            envio.getListNotificaciones().add(observer);
        }
        return true;
    }

    private void notificarObservers(Envio envio) {
        if (envio.getListNotificaciones() != null) {
            for (INotificacionObserver observer : envio.getListNotificaciones()) {
                observer.actualizar(envio);
            }
        }
    }
    public String obtenerResumenIncidencias(int idEnvio) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null || envio.obtenerIncidencias().isEmpty()) {
            return "No hay incidencias registradas para este envío.";
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("=== RESUMEN DE INCIDENCIAS ===\n");
        resumen.append("Total de incidencias: ").append(envio.obtenerIncidencias().size()).append("\n\n");

        for (int i = 0; i < envio.obtenerIncidencias().size(); i++) {
            Incidencia inc = envio.obtenerIncidencias().get(i);
            resumen.append("Incidencia #").append(i + 1).append(":\n");
            resumen.append(inc.obtenerResumen()).append("\n\n");
        }

        return resumen.toString();
    }

    public Incidencia obtenerIncidenciaActual(int idEnvio) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null || envio.obtenerIncidencias().isEmpty()) {
            return null;
        }

        return envio.obtenerIncidencias().get(envio.obtenerIncidencias().size() - 1);
    }
    public boolean eliminarEnvio(int idEnvio) {
        Envio envio = buscarEnvioEntity(idEnvio);
        if (envio == null) {
            return false;
        }

        envios.remove(envio);
        return true;
    }
    public boolean existeEnvio(int idEnvio) {
        return buscarEnvioEntity(idEnvio) != null;
    }

}
