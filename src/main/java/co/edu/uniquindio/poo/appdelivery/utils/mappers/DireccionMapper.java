package co.edu.uniquindio.poo.appdelivery.utils.mappers;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.DireccionDTO;
import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;

public class DireccionMapper {
    public static Direccion toEntity(DireccionDTO dto) {
        if (dto == null) return null;

        return new Direccion(
                dto.getIdDireccion(),
                dto.getCiudad(),
                dto.getCalle(),
                dto.getAlias(),
                dto.getCoordenadas()
        );
    }
    public static DireccionDTO toDTO(Direccion direccion) {
        if (direccion == null) return null;

        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setCiudad(direccion.getCiudad());
        dto.setCalle(direccion.getCalle());
        dto.setAlias(direccion.getAlias());
        dto.setCoordenadas(direccion.getCoordenadas());
        return dto;
    }
    public static void updateEntityFromDTO(Direccion entity, DireccionDTO dto) {
        if (entity == null || dto == null) return;

        entity.setCiudad(dto.getCiudad());
        entity.setCalle(dto.getCalle());
        entity.setAlias(dto.getAlias());
        entity.setCoordenadas(dto.getCoordenadas());
    }
}
