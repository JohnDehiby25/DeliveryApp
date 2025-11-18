package co.edu.uniquindio.poo.appdelivery.Test;

import co.edu.uniquindio.poo.appdelivery.model.direccion.Direccion;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.envio.EstadoEnvio;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;
import co.edu.uniquindio.poo.appdelivery.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServioceTest {
    private UsuarioService usuarioService;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
        usuario = new Usuario("123");  // documento
        usuario.setListDirecciones(new ArrayList<>());
        usuario.setListEnvios(new ArrayList<>());
    }

    // ============= 1. CREAR DIRECCIÓN ==================
    @Test
    void testCrearDireccion() {
        Direccion d = usuarioService.crearDireccion(usuario, 1, "Armenia", "Casa", "Calle 10", "10,20");

        assertNotNull(d);
        assertEquals(1, usuario.getListDirecciones().size());
    }

    // ============= 2. DIRECCIÓN REPETIDA ==================
    @Test
    void testCrearDireccionRepetidaDebeRetornarNull() {
        usuarioService.crearDireccion(usuario, 1, "Armenia", "Casa", "Calle 10", "10,20");

        Direccion result = usuarioService.crearDireccion(usuario, 1, "Cali", "Oficina", "Calle 20", "20,30");

        assertNull(result);
        assertEquals(1, usuario.getListDirecciones().size());
    }

    // ============= 3. ACTUALIZAR DIRECCIÓN ==================
    @Test
    void testActualizarInfoDireccion() {
        usuarioService.crearDireccion(usuario, 1, "Armenia", "Casa", "Calle 10", "10,20");

        Direccion nueva = new Direccion(1, "Cali", "Trabajo", "Calle 30", "30,40");

        boolean actualizado = usuarioService.actualizarInfoDireccionExistente(nueva, usuario, 1);

        assertTrue(actualizado);
        assertEquals("Trabajo", usuario.getListDirecciones().get(0).getAlias());
    }

    // ============= 4. ACTUALIZAR DIRECCIÓN NO EXISTE ==================
    @Test
    void testActualizarDireccionNoExiste() {
        Direccion nueva = new Direccion(1, "Cali", "Trabajo", "Calle 30", "30,40");

        boolean actualizado = usuarioService.actualizarInfoDireccionExistente(nueva, usuario, 1);

        assertFalse(actualizado);
    }

    // ============= 5. ELIMINAR DIRECCIÓN ==================
    @Test
    void testEliminarDireccion() {
        usuarioService.crearDireccion(usuario, 1, "Armenia", "Casa", "Calle 10", "10,20");

        boolean eliminado = usuarioService.eliminarDireccion(1, usuario);

        assertTrue(eliminado);
        assertTrue(usuario.getListDirecciones().isEmpty());
    }

    // ============= 6. ELIMINAR DIRECCIÓN QUE NO EXISTE ==================
    @Test
    void testEliminarDireccionNoExiste() {
        boolean eliminado = usuarioService.eliminarDireccion(10, usuario);

        assertFalse(eliminado);
    }

    // ============= 7. VER DIRECCIONES ==================
    @Test
    void testConsultarDetallesDirecciones() {
        usuarioService.crearDireccion(usuario, 1, "Armenia", "Casa", "Calle 10", "10,20");

        String mensaje = usuarioService.consultarDetallesDirecciones(usuario);

        assertTrue(mensaje.contains("Casa"));
    }

    // ============= 8. VER DIRECCIONES VACÍAS ==================
    @Test
    void testConsultarDireccionesVacias() {
        String mensaje = usuarioService.consultarDetallesDirecciones(usuario);

        assertEquals("No hay direcciones asignadas", mensaje);
    }

    // ============= 9. FILTRAR ENVÍOS POR FECHA ==================
    @Test
    void testFiltrarEnviosFecha() {
        Envio e1 = new Envio(1, EstadoEnvio.SOLICITADO,
                LocalDateTime.now(), LocalDateTime.of(2025, 1, 10), null);

        usuario.getListEnvios().add(e1);

        String result = usuarioService.filtrarEnviosFecha(
                LocalDateTime.of(2025, 1, 10), usuario);

        assertTrue(result.contains("1"));
    }


    // ============= 10. CREAR SOLICITUD DE ENVÍO ==================
    @Test
    void testCrearSolicitudEnvio() {
        Envio e1 = new Envio(5, EstadoEnvio.CREADO,
                LocalDateTime.now(), LocalDateTime.now(), null);

        usuario.getListEnvios().add(e1);

        String msg = usuarioService.crearSolicitudEnvio(5, usuario);

        assertTrue(msg.contains("ha sido solicitado"));
        assertEquals(EstadoEnvio.SOLICITADO, e1.getEstado());
    }

    // ============= 11. BUSCAR USUARIO ==================
    @Test
    void testBuscarUsuarioEntity() {
        usuarioService.crearDireccion(usuario, 1, "A", "B", "C", "D");
        usuarioService.buscarUsuarioEntity("123"); // aún no agregado

        usuarioService.buscarUsuarioEntity("123");
        usuarioService.buscarUsuarioEntity("456");

        // Lo agregamos
        Usuario u2 = new Usuario("123");
        usuarioService.buscarUsuarioEntity("123");

        // Lo metemos en la lista interna
        usuarioService.buscarUsuarioEntity("123");

        // Como no hay método para insertar usuario, lo agregamos manual:
        java.lang.reflect.Field f;
        try {
            f = usuarioService.getClass().getDeclaredField("usuarios");
            f.setAccessible(true);
            ((ArrayList<Usuario>) f.get(usuarioService)).add(u2);
        } catch (Exception ignored) {}

        Usuario encontrado = usuarioService.buscarUsuarioEntity("123");

        assertNotNull(encontrado);
        assertEquals("123", encontrado.getId());
    }

}