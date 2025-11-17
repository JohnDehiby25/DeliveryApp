package co.edu.uniquindio.poo.appdelivery.utils.mappers;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.PaqueteDTO;
import co.edu.uniquindio.poo.appdelivery.model.paquete.Paquete;

public class PaqueteMapper {
    public static Paquete toEntity(PaqueteDTO dto) {
        if (dto == null) return null;

        return new Paquete(
                dto.getPeso(),
                dto.getAncho(),
                dto.getLargo(),
                dto.getCostoPaquete()
        );
    }
    public static PaqueteDTO toDTO(Paquete paquete) {
        if (paquete == null) return null;

        PaqueteDTO dto = new PaqueteDTO();
        dto.setPeso(paquete.getPeso());
        dto.setAncho(paquete.getAncho());
        dto.setLargo(paquete.getLargo());
        dto.setCostoPaquete(paquete.getCostoPaquete());

        return dto;
    }
    public static void updateEntityFromDTO(Paquete entity, PaqueteDTO dto) {
        if (entity == null || dto == null) return;

        entity.setPeso(dto.getPeso());
        entity.setAncho(dto.getAncho());
        entity.setLargo(dto.getLargo());
        entity.setCostoPaquete(dto.getCostoPaquete());
    }
}
