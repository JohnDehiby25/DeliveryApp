package co.edu.uniquindio.poo.appdelivery.model.mapper;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.EnvioDTO;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.envio.EstadoEnvio;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.DireccionMapper;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.PaqueteMapper;

public class EnvioMapper {

    public static Envio toModel(EnvioDTO dto, Usuario usuario, Repartidor repartidor) {

        return new Envio(
                dto.getIdEnvio(),
                DireccionMapper.toModel(dto.getOrigen()),
                DireccionMapper.toModel(dto.getDestino()),
                PaqueteMapper.toModel(dto.getPaquete()),
                dto.getCosto(),
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
}

