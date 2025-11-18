package co.edu.uniquindio.poo.appdelivery.service.facade;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.DireccionDTO;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.EnvioDTO;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.UsuarioDTO;
import co.edu.uniquindio.poo.appdelivery.service.AdministradorService;
import co.edu.uniquindio.poo.appdelivery.service.EnvioService;
import co.edu.uniquindio.poo.appdelivery.service.UsuarioService;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.DireccionMapper;

import java.util.ArrayList;
import java.util.List;

public class UsuarioFacade {
    private UsuarioService usuarioService;
    private EnvioService envioService;
    private AdministradorService adminService;
    private UsuarioDTO usuarioActualDTO;

    public UsuarioFacade(UsuarioDTO usuarioDTO, AdministradorService adminService) {
        this.usuarioService = new UsuarioService();
        this.adminService = adminService;
        this.envioService = new EnvioService(usuarioService, adminService);
        this.usuarioActualDTO = usuarioDTO;
    }

    // Gestionar direcciones
    public boolean agregarDireccion(DireccionDTO direccionDTO) {
        var usuarioEntity = adminService.buscarUsuarioPorId(usuarioActualDTO.getId());
        if (usuarioEntity == null) return false;

        var direccionEntity = DireccionMapper.toEntity(direccionDTO);
        var resultado = usuarioService.crearDireccion(usuarioEntity,
                direccionEntity.getIdDireccion(),
                direccionEntity.getCiudad(),
                direccionEntity.getAlias(),
                direccionEntity.getCalle(),
                direccionEntity.getCoordenadas());

        if (resultado != null) {
            usuarioActualDTO.getDirecciones().add(direccionDTO);
            return true;
        }
        return false;
    }

    public List<DireccionDTO> obtenerMisDirecciones() {
        return usuarioActualDTO.getDirecciones();
    }

    // Gestionar envios
    public EnvioDTO solicitarEnvio(EnvioDTO solicitudDTO) {
        try {
            int idEnvio = generarIdEnvio();

            EnvioDTO envioDTO = new EnvioDTO();
            envioDTO.setIdEnvio(idEnvio);
            envioDTO.setIdUsuario(usuarioActualDTO.getId());
            envioDTO.setOrigen(solicitudDTO.getOrigen());
            envioDTO.setDestino(solicitudDTO.getDestino());
            envioDTO.setPaquete(solicitudDTO.getPaquete());
            envioDTO.setCosto(calcularCostoEnvio(solicitudDTO));
            envioDTO.setDistanciaPorKm(5.2);
            envioDTO.setEstado("SOLICITADO");
            envioDTO.setFechaCreacion(java.time.LocalDateTime.now());
            envioDTO.setFechaEstimadaEntrega(java.time.LocalDateTime.now().plusHours(2));

            boolean agregado = envioService.agregarEnvio(envioDTO);
            if (agregado) {
                usuarioActualDTO.setTotalEnvios(usuarioActualDTO.getTotalEnvios() + 1);
                return envioDTO;
            }
        } catch (Exception e) {
            System.out.println("Error al solicitar envio: " + e.getMessage());
        }
        return null;
    }

    public List<EnvioDTO> obtenerMisEnvios() {
        List<EnvioDTO> todosEnvios = envioService.obtenerTodosLosEnvios();
        List<EnvioDTO> misEnvios = new ArrayList<>();

        for (EnvioDTO envio : todosEnvios) {
            if (envio.getIdUsuario() != null &&
                    envio.getIdUsuario().equals(usuarioActualDTO.getId())) {
                misEnvios.add(envio);
            }
        }
        return misEnvios;
    }

    private double calcularCostoEnvio(EnvioDTO solicitud) {
        return 10000 + (solicitud.getPaquete().getPeso() * 2000);
    }

    private int generarIdEnvio() {
        return (int) (System.currentTimeMillis() % 10000);
    }

    public UsuarioDTO getUsuarioActual() {
        return usuarioActualDTO;
    }
}