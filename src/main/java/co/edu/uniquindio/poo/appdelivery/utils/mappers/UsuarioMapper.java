package co.edu.uniquindio.poo.appdelivery.utils.mappers;

import co.edu.uniquindio.poo.appdelivery.model.DTOs.DireccionDTO;
import co.edu.uniquindio.poo.appdelivery.model.DTOs.UsuarioDTO;
import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setTelefono(usuario.getTelefono());
        dto.setEmail(usuario.getEmail());
        dto.setTotalEnvios(usuario.getListEnvios().size());


        List<DireccionDTO> direccionesDTO = new ArrayList<>();
        if (usuario.getListDirecciones() != null) {
            for (Direccion direccion : usuario.getListDirecciones()) {
                direccionesDTO.add(DireccionMapper.toDTO(direccion));
            }
        }
        dto.setDirecciones(direccionesDTO);

        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;

        return new Usuario(
                dto.getNombre(),
                dto.getId(),
                dto.getTelefono(),
                dto.getEmail()
        );
    }
}
