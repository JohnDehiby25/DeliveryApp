package co.edu.uniquindio.poo.appdelivery.utils.mappers;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.RepartidorDTO;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.TipoDisponibilidad;

public class RepartidorMapper {

    public static RepartidorDTO toDTO(Repartidor repartidor) {
        if (repartidor == null) return null;

        RepartidorDTO dto = new RepartidorDTO();
        dto.setId(repartidor.getId());
        dto.setNombre(repartidor.getNombre());
        dto.setTelefono(repartidor.getTelefono());
        dto.setEmail(repartidor.getEmail());
        dto.setDocumento(repartidor.getDocumento());
        dto.setEstado(repartidor.getTipodisponibilidad().name());
        dto.setZonaCobertura(repartidor.getZonaCobertura());
        dto.setTotalEnviosAsignados(repartidor.getListEnvios().size());

        return dto;
    }

    public static Repartidor toEntity(RepartidorDTO dto) {
        if (dto == null) return null;

        return new Repartidor(
                dto.getNombre(),
                dto.getId(),
                dto.getTelefono(),
                dto.getEmail(),
                dto.getDocumento(),
                TipoDisponibilidad.valueOf(dto.getEstado()),
                dto.getZonaCobertura()
        );
    }
}
