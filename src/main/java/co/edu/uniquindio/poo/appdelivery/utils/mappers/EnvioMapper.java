package co.edu.uniquindio.poo.appdelivery.utils.mappers;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.EnvioDTO;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.envio.EstadoEnvio;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;

public class EnvioMapper {

    public static Envio toEntity(EnvioDTO dto, Usuario usuario, Repartidor repartidor) {
        if (dto == null) return null;

        return new Envio(
                dto.getIdEnvio(),
                DireccionMapper.toEntity(dto.getOrigen()),
                DireccionMapper.toEntity(dto.getDestino()),
                PaqueteMapper.toEntity(dto.getPaquete()),
                dto.getCosto(),
                dto.getDistanciaPorKm(),
                EstadoEnvio.valueOf(dto.getEstado()),
                dto.getFechaCreacion(),
                dto.getFechaEstimadaEntrega(),
                usuario,
                repartidor,
                null,
                null
        );
    }

    public static EnvioDTO toDTO(Envio envio) {
        if (envio == null) return null;

        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(envio.getIdEnvio());
        dto.setCosto(envio.getCosto());
        dto.setEstado(envio.getEstado().name());
        dto.setFechaCreacion(envio.getFechaCreacion());
        dto.setFechaEstimadaEntrega(envio.getFechaEstimadaEntrega());

        dto.setOrigen(DireccionMapper.toDTO(envio.getOrigen()));
        dto.setDestino(DireccionMapper.toDTO(envio.getDestino()));
        dto.setPaquete(PaqueteMapper.toDTO(envio.getPaquete()));

        if (envio.getUsuario() != null)
            dto.setIdUsuario(envio.getUsuario().getId());

        if (envio.getRepartidor() != null)
            dto.setIdRepartidor(envio.getRepartidor().getId());

        return dto;
    }

    public static void updateEntityFromDTO(Envio entity, EnvioDTO dto, Usuario usuario, Repartidor repartidor) {
        if (entity == null || dto == null) return;

        entity.setOrigen(DireccionMapper.toEntity(dto.getOrigen()));
        entity.setDestino(DireccionMapper.toEntity(dto.getDestino()));
        entity.setPaquete(PaqueteMapper.toEntity(dto.getPaquete()));
        entity.setCosto(dto.getCosto());
        entity.setEstado(EstadoEnvio.valueOf(dto.getEstado()));
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setFechaEstimadaEntrega(dto.getFechaEstimadaEntrega());
        entity.setUsuario(usuario);
        entity.setRepartidor(repartidor);
    }
}

