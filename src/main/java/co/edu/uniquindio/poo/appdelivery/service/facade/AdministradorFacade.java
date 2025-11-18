package co.edu.uniquindio.poo.appdelivery.service.facade;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.AdministradorDTO;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.RepartidorDTO;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.UsuarioDTO;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;
import co.edu.uniquindio.poo.appdelivery.service.AdministradorService;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.RepartidorMapper;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.UsuarioMapper;

import java.util.ArrayList;
import java.util.List;

public class AdministradorFacade {
    private AdministradorService adminService;
    private AdministradorDTO administradorActualDTO;

    public AdministradorFacade(AdministradorDTO adminDTO, AdministradorService adminService) {
        this.adminService = adminService;
        this.administradorActualDTO = adminDTO;
    }

    // Gestionar usuarios
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        try {
            var usuarioEntity = UsuarioMapper.toEntity(usuarioDTO);
            boolean registrado = adminService.getAdministrador().getListUsuarios().add(usuarioEntity);

            if (registrado) {
                administradorActualDTO.setTotalUsuarios(administradorActualDTO.getTotalUsuarios() + 1);
                return usuarioDTO;
            }
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
        return null;
    }

    public List<UsuarioDTO> obtenerTodosUsuarios() {
        List<Usuario> usuariosEntities = adminService.obtenerTodosLosUsuarios();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        for (Usuario usuario : usuariosEntities) {
            usuariosDTO.add(UsuarioMapper.toDTO(usuario));
        }
        return usuariosDTO;
    }

    // Gestionar repartidores
    public RepartidorDTO registrarRepartidor(RepartidorDTO repartidorDTO) {
        try {
            var repartidorEntity = RepartidorMapper.toEntity(repartidorDTO);
            boolean registrado = adminService.registrarRepartidor(
                    repartidorEntity.getNombre(),
                    repartidorEntity.getId(),
                    repartidorEntity.getTelefono(),
                    repartidorEntity.getEmail(),
                    repartidorEntity.getDocumento(),
                    repartidorEntity.getTipodisponibilidad(),
                    repartidorEntity.getZonaCobertura()
            );

            if (registrado) {
                administradorActualDTO.setTotalRepartidores(administradorActualDTO.getTotalRepartidores() + 1);
                return repartidorDTO;
            }
        } catch (Exception e) {
            System.out.println("Error al registrar repartidor: " + e.getMessage());
        }
        return null;
    }

    public List<RepartidorDTO> obtenerRepartidoresDisponibles() {
        List<Repartidor> repartidoresEntities = adminService.obtenerRepartidoresDisponibles();
        List<RepartidorDTO> repartidoresDTO = new ArrayList<>();

        for (Repartidor repartidor : repartidoresEntities) {
            repartidoresDTO.add(RepartidorMapper.toDTO(repartidor));
        }
        return repartidoresDTO;
    }

    public AdministradorDTO getAdministradorActual() {
        return administradorActualDTO;
    }
}