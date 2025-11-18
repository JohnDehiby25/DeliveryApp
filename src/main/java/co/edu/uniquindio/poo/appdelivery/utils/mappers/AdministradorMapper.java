package co.edu.uniquindio.poo.appdelivery.utils.mappers;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.AdministradorDTO;
import co.edu.uniquindio.poo.appdelivery.model.admin.Administrador;

public class AdministradorMapper {

    public static AdministradorDTO toDTO(Administrador admin) {
        if (admin == null) return null;

        AdministradorDTO dto = new AdministradorDTO();
        dto.setId(admin.getId());
        dto.setNombre(admin.getNombre());
        dto.setTelefono(admin.getTelefono());
        dto.setEmail(admin.getEmail());
        dto.setTotalUsuarios(admin.getListUsuarios().size());
        dto.setTotalRepartidores(admin.getListRepartidores().size());
        dto.setTotalEnvios(admin.getListEnvios().size());

        return dto;
    }

    public static Administrador toEntity(AdministradorDTO dto) {
        if (dto == null) return null;

        return new Administrador(
                dto.getNombre(),
                dto.getId(),
                dto.getTelefono(),
                dto.getEmail()
        );
    }
}
