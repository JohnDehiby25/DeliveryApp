package co.edu.uniquindio.poo.appdelivery.service;

import co.edu.uniquindio.poo.appdelivery.model.admin.Administrador;
import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.envio.EstadoEnvio;
import co.edu.uniquindio.poo.appdelivery.model.incidencia.Incidencia;
import co.edu.uniquindio.poo.appdelivery.model.pago.Pago;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.TipoDisponibilidad;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AdministradorService {
    private Administrador administrador;

    public AdministradorService() {
        this.administrador = new Administrador();
    }

    public AdministradorService(Administrador administrador) {
        this.administrador = administrador;
    }

    public boolean eliminarUsuario(String idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return false;
        }

        administrador.getListUsuarios().remove(usuario);
        return true;
    }
    public boolean actualizarInfoUsuario(String idUsuario, String nuevoTelefono,
                                         String nuevoEmail) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return false;
        }

        if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
            usuario.setTelefono(nuevoTelefono);
        }
        if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
            usuario.setEmail(nuevoEmail);
        }

        return true;
    }

    public boolean eliminarDireccionUsuario(String idUsuario, int idDireccion) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return false;
        }

        ArrayList<Direccion> direcciones = usuario.getListDirecciones();
        for (int i = 0; i < direcciones.size(); i++) {
            if (direcciones.get(i).getIdDireccion() == idDireccion) {
                direcciones.remove(i);
                return true;
            }
        }
        return false;
    }

    public String consultarIncidenciasUsuario(String idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return "Usuario no encontrado";
        }

        StringBuilder resultado = new StringBuilder();
        resultado.append("=== INCIDENCIAS DEL USUARIO ").append(usuario.getNombre()).append(" ===\n");

        int totalIncidencias = 0;
        for (Envio envio : usuario.getListEnvios()) {
            if (!envio.obtenerIncidencias().isEmpty()) {
                resultado.append("\nEnvío ID: ").append(envio.getIdEnvio()).append("\n");
                for (Incidencia inc : envio.obtenerIncidencias()) {
                    resultado.append("  - ").append(inc.obtenerResumen()).append("\n");
                    totalIncidencias++;
                }
            }
        }

        if (totalIncidencias == 0) {
            return "El usuario no tiene incidencias registradas.";
        }

        resultado.insert(0, "Total de incidencias: " + totalIncidencias + "\n\n");
        return resultado.toString();
    }

    public boolean registrarRepartidor(String nombre, String id, String telefono, String email,
                                       String documento, TipoDisponibilidad disponibilidad, int zonaCobertura) {

        if (buscarRepartidorPorDocumento(documento) != null) {
            System.out.println("Ya existe un repartidor con ese documento");
            return false;
        }

        Repartidor nuevoRepartidor = new Repartidor(nombre, id, telefono, email,
                documento, disponibilidad, zonaCobertura);
        administrador.getListRepartidores().add(nuevoRepartidor);
        return true;
    }

    public boolean actualizarInfoRepartidor(String documento, String nuevoTelefono,
                                            String nuevoEmail, Integer nuevaZonaCobertura) {
        Repartidor repartidor = buscarRepartidorPorDocumento(documento);
        if (repartidor == null) {
            return false;
        }

        if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
            repartidor.setTelefono(nuevoTelefono);
        }
        if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
            repartidor.setEmail(nuevoEmail);
        }
        if (nuevaZonaCobertura != null && nuevaZonaCobertura > 0) {
            repartidor.setZonaCobertura(nuevaZonaCobertura);
        }

        return true;
    }

    public boolean actualizarEstadoRepartidor(String documento, TipoDisponibilidad nuevoEstado) {
        Repartidor repartidor = buscarRepartidorPorDocumento(documento);
        if (repartidor == null) {
            return false;
        }

        repartidor.setTipodisponibilidad(nuevoEstado);
        return true;
    }

    public boolean eliminarRepartidor(String documento) {
        Repartidor repartidor = buscarRepartidorPorDocumento(documento);
        if (repartidor == null) {
            return false;
        }

        if (!repartidor.getListEnvios().isEmpty()) {
            System.out.println("No se puede eliminar, el repartidor tiene envíos asignados");
            return false;
        }

        administrador.getListRepartidores().remove(repartidor);
        return true;
    }

    public boolean reasignarEnvioRepartidor(int idEnvio, String documentoNuevoRepartidor) {
        Envio envio = buscarEnvioPorId(idEnvio);
        if (envio == null) {
            System.out.println("Envío no encontrado");
            return false;
        }

        Repartidor nuevoRepartidor = buscarRepartidorPorDocumento(documentoNuevoRepartidor);
        if (nuevoRepartidor == null) {
            System.out.println("Repartidor no encontrado");
            return false;
        }

        if (nuevoRepartidor.getTipodisponibilidad() != TipoDisponibilidad.ACTIVO) {
            System.out.println("El repartidor no está disponible");
            return false;
        }
        if (envio.getRepartidor() != null) {
            envio.getRepartidor().getListEnvios().remove(envio);
        }
        envio.setRepartidor(nuevoRepartidor);
        nuevoRepartidor.getListEnvios().add(envio);

        return true;
    }

    public boolean asignarEnvio(int idEnvio, String documentoRepartidor) {
        Envio envio = buscarEnvioPorId(idEnvio);
        if (envio == null) {
            return false;
        }

        Repartidor repartidor = buscarRepartidorPorDocumento(documentoRepartidor);
        if (repartidor == null) {
            return false;
        }

        if (repartidor.getTipodisponibilidad() != TipoDisponibilidad.ACTIVO) {
            System.out.println("El repartidor no está disponible");
            return false;
        }

        envio.setRepartidor(repartidor);
        repartidor.getListEnvios().add(envio);

        return true;
    }

    public boolean eliminarEnvio(int idEnvio) {
        Envio envio = buscarEnvioPorId(idEnvio);
        if (envio == null) {
            return false;
        }

        if (envio.getRepartidor() != null) {
            envio.getRepartidor().getListEnvios().remove(envio);
        }

        if (envio.getUsuario() != null) {
            envio.getUsuario().getListEnvios().remove(envio);
        }

        administrador.getListEnvios().remove(envio);
        return true;
    }

    public String generarReporteGeneral() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========== REPORTE GENERAL DEL SISTEMA ==========\n\n");

        reporte.append("Total de Usuarios: ").append(administrador.getListUsuarios().size()).append("\n");
        reporte.append("Total de Repartidores: ").append(administrador.getListRepartidores().size()).append("\n");
        reporte.append("Total de Envíos: ").append(administrador.getListEnvios().size()).append("\n");
        reporte.append("Total de Pagos: ").append(administrador.getListPagos().size()).append("\n");
        reporte.append("Total de Incidencias: ").append(administrador.getListIncidencias().size()).append("\n");

        return reporte.toString();
    }

    public String generarReporteRepartidores() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("========== REPORTE DE REPARTIDORES ==========\n\n");

        if (administrador.getListRepartidores().isEmpty()) {
            return "No hay repartidores registrados.";
        }

        for (Repartidor r : administrador.getListRepartidores()) {
            reporte.append("Nombre: ").append(r.getNombre()).append("\n");
            reporte.append("Documento: ").append(r.getDocumento()).append("\n");
            reporte.append("Teléfono: ").append(r.getTelefono()).append("\n");
            reporte.append("Email: ").append(r.getEmail()).append("\n");
            reporte.append("Disponibilidad: ").append(r.getTipodisponibilidad()).append("\n");
            reporte.append("Zona Cobertura: ").append(r.getZonaCobertura()).append("\n");
            reporte.append("Envíos Asignados: ").append(r.getListEnvios().size()).append("\n");
            reporte.append("-------------------------------------------\n");
        }

        return reporte.toString();
    }


    public String reporteDisponibilidadRepartidores() {
        int disponibles = 0;
        int ocupados = 0;
        int noDisponibles = 0;

        for (Repartidor r : administrador.getListRepartidores()) {
            switch (r.getTipodisponibilidad()) {
                case ACTIVO:
                    disponibles++;
                    break;
                case INACTIVO:
                    ocupados++;
                    break;
                case ENRUTA:
                    noDisponibles++;
                    break;
            }
        }
        return String.format(
                "=== DISPONIBILIDAD DE REPARTIDORES ===\n" +
                        "Disponibles: %d\n" +
                        "Ocupados: %d\n" +
                        "No Disponibles: %d\n" +
                        "Total: %d",
                disponibles, ocupados, noDisponibles,
                administrador.getListRepartidores().size()
        );
    }

    public String visualizarEnviosPendientes() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("=== ENVÍOS PENDIENTES ===\n");

        int contador = 0;
        for (Envio envio : administrador.getListEnvios()) {
            if (envio.getRepartidor() == null) {
                resultado.append("\nEnvío ID: ").append(envio.getIdEnvio()).append("\n");
                resultado.append("Estado: ").append(envio.getEstado()).append("\n");
                resultado.append("Origen: ").append(envio.getOrigen()).append("\n");
                resultado.append("Destino: ").append(envio.getDestino()).append("\n");
                resultado.append("---\n");
                contador++;
            }
        }

        if (contador == 0) {
            return "No hay envíos pendientes de asignación.";
        }

        resultado.insert(0, "Total de envíos pendientes: " + contador + "\n\n");
        return resultado.toString();
    }

    /**
     * Muestra el comprobante de un pago específico
     */
    public void mostrarComprobantePago(String idPago) {
        Pago pago = buscarPagoPorId(idPago);
        if (pago == null) {
            System.out.println("Pago no encontrado");
            return;
        }

        pago.mostrarComprobantePago();
    }

    public Usuario buscarUsuarioPorId(String idUsuario) {
        for (Usuario usuario : administrador.getListUsuarios()) {
            if (usuario.getId().equalsIgnoreCase(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }


    public Repartidor buscarRepartidorPorDocumento(String documento) {
        for (Repartidor repartidor : administrador.getListRepartidores()) {
            if (repartidor.getDocumento().equalsIgnoreCase(documento)) {
                return repartidor;
            }
        }
        return null;
    }


    private Envio buscarEnvioPorId(int idEnvio) {
        for (Envio envio : administrador.getListEnvios()) {
            if (envio.getIdEnvio() == idEnvio) {
                return envio;
            }
        }
        return null;
    }

    private Pago buscarPagoPorId(String idPago) {
        for (Pago pago : administrador.getListPagos()) {
            if (pago.getIdPago().equalsIgnoreCase(idPago)) {
                return pago;
            }
        }
        return null;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return new ArrayList<>(administrador.getListUsuarios());
    }

    public List<Repartidor> obtenerTodosLosRepartidores() {
        return new ArrayList<>(administrador.getListRepartidores());
    }

    public List<Repartidor> obtenerRepartidoresDisponibles() {
        List<Repartidor> disponibles = new ArrayList<>();
        for (Repartidor r : administrador.getListRepartidores()) {
            if (r.getTipodisponibilidad() == TipoDisponibilidad.ACTIVO) {
                disponibles.add(r);
            }
        }
        return disponibles;
    }

    public Administrador getAdministrador() {
        return administrador;
    }
    //Metodo Adicionales del repartidor
    public Envio cambiarEstadoEnvio(int idEnvio, EstadoEnvio nuevoEstado) {
        Envio envio = buscarEnvioPorId(idEnvio);
        if (envio == null) {
            throw new IllegalArgumentException("Envío no encontrado");
        }

        envio.setEstado(nuevoEstado);
        envio.notificarObserversNotificacion();
        return envio;
    }
    public List<Envio> consultarEnviosAsignados(String documentoRepartidor) {
        Repartidor repartidor = buscarRepartidorPorDocumento(documentoRepartidor);
        if (repartidor == null) {
            System.out.println("Repartidor no encontrado");
            return new ArrayList<>();
        }

        return new ArrayList<>(repartidor.getListEnvios());
    }
    public List<Envio> consultarEnviosAsignados(Repartidor repartidor) {
        if (repartidor == null) {
            System.out.println("Repartidor no válido");
            return new ArrayList<>();
        }

        return new ArrayList<>(repartidor.getListEnvios());
    }
    public List<Repartidor> filtrarRepartidoresPorZona(int zona) {
        List<Repartidor> repartidoresEnZona = new ArrayList<>();
        for (Repartidor r : administrador.getListRepartidores()) {
            if (r.getZonaCobertura() == zona) {
                repartidoresEnZona.add(r);
            }
        }
        return repartidoresEnZona;
    }
    public List<Repartidor> filtrarRepartidoresPorDisponibilidad(TipoDisponibilidad disponibilidad) {
        List<Repartidor> repartidoresFiltrados = new ArrayList<>();
        for (Repartidor r : administrador.getListRepartidores()) {
            if (r.getTipodisponibilidad() == disponibilidad) {
                repartidoresFiltrados.add(r);
            }
        }
        return repartidoresFiltrados;
    }
}
