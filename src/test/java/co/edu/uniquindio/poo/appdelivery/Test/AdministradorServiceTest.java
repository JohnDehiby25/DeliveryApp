package co.edu.uniquindio.poo.appdelivery.Test;

import co.edu.uniquindio.poo.appdelivery.model.admin.Administrador;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.Repartidor;
import co.edu.uniquindio.poo.appdelivery.model.repartidor.TipoDisponibilidad;
import co.edu.uniquindio.poo.appdelivery.model.usuario.Usuario;
import co.edu.uniquindio.poo.appdelivery.service.AdministradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorServiceTest {
    private AdministradorService adminService;
    private Administrador administrador;

    @BeforeEach
    void setUp() {
        administrador = new Administrador();
        adminService = new AdministradorService(administrador);
    }

    // ========== 1. ELIMINAR USUARIO ==========
    @Test
    void testEliminarUsuario() {
        Usuario u1 = new Usuario("1", "Julian", "3001234567", "j@mail.com");
        administrador.getListUsuarios().add(u1);

        boolean res = adminService.eliminarUsuario("1");

        assertTrue(res);
        assertEquals(0, administrador.getListUsuarios().size());
    }

    // ========== 2. ACTUALIZAR INFO DE USUARIO ==========
    @Test
    void testActualizarInfoUsuario() {
        Usuario u = new Usuario("2", "Maria", "111", "old@mail.com");
        administrador.getListUsuarios().add(u);

        boolean res = adminService.actualizarInfoUsuario("2", "3000000000", "new@mail.com");

        assertTrue(res);
        assertEquals("3000000000", u.getTelefono());
        assertEquals("new@mail.com", u.getEmail());
    }

    // ========== 3. REGISTRAR REPARTIDOR ==========
    @Test
    void testRegistrarRepartidor() {
        boolean res = adminService.registrarRepartidor(
                "Pedro", "10", "301555", "p@mail.com",
                "ABC123", TipoDisponibilidad.ACTIVO, 5
        );

        assertTrue(res);
        assertEquals(1, administrador.getListRepartidores().size());
    }

    // ========== 4. ACTUALIZAR ESTADO DE REPARTIDOR ==========
    @Test
    void testActualizarEstadoRepartidor() {
        Repartidor r = new Repartidor("Ana", "99", "300", "a@mail.com",
                "DOC1", TipoDisponibilidad.ACTIVO, 3);
        administrador.getListRepartidores().add(r);

        boolean res = adminService.actualizarEstadoRepartidor("DOC1", TipoDisponibilidad.ENRUTA);

        assertTrue(res);
        assertEquals(TipoDisponibilidad.ENRUTA, r.getTipodisponibilidad());
    }

    // ========== 5. ASIGNAR ENVÍO A REPARTIDOR ==========
    @Test
    void testAsignarEnvio() {
        Repartidor r = new Repartidor("Juan", "50", "300", "j@mail.com",
                "DOC50", TipoDisponibilidad.ACTIVO, 2);
        administrador.getListRepartidores().add(r);

        Envio envio = new Envio(1, null, null, 100);
        administrador.getListEnvios().add(envio);

        boolean res = adminService.asignarEnvio(1, "DOC50");

        assertTrue(res);
        assertEquals(r, envio.getRepartidor());
        assertEquals(1, r.getListEnvios().size());
    }

    // ========== 6. ELIMINAR ENVÍO ==========
    @Test
    void testEliminarEnvio() {
        Usuario u = new Usuario("7", "Luis", "777", "l@mail.com");
        administrador.getListUsuarios().add(u);

        Envio envio = new Envio(20, null, null, 50);
        envio.setUsuario(u);

        administrador.getListEnvios().add(envio);
        u.getListEnvios().add(envio);

        boolean res = adminService.eliminarEnvio(20);

        assertTrue(res);
        assertEquals(0, administrador.getListEnvios().size());
        assertEquals(0, u.getListEnvios().size());
    }

    // ========== 7. FILTRAR REPARTIDORES POR ZONA ==========
    @Test
    void testFiltrarRepartidoresPorZona() {
        administrador.getListRepartidores().add(new Repartidor("A", "1", "1","a@mail","D1", TipoDisponibilidad.ACTIVO, 3));
        administrador.getListRepartidores().add(new Repartidor("B", "2", "2","b@mail","D2", TipoDisponibilidad.ACTIVO, 5));
        administrador.getListRepartidores().add(new Repartidor("C", "3", "3","c@mail","D3", TipoDisponibilidad.ACTIVO, 3));

        var lista = adminService.filtrarRepartidoresPorZona(3);

        assertEquals(2, lista.size());
    }

    // ========== 8. FILTRAR REPARTIDORES POR DISPONIBILIDAD ==========
    @Test
    void testFiltrarRepartidoresPorDisponibilidad() {
        administrador.getListRepartidores().add(new Repartidor("Walter","1","1","x","D1", TipoDisponibilidad.ACTIVO, 1));
        administrador.getListRepartidores().add(new Repartidor("Emily","2","2","y","D2", TipoDisponibilidad.ENRUTA, 2));

        var activos = adminService.filtrarRepartidoresPorDisponibilidad(TipoDisponibilidad.ACTIVO);

        assertEquals(1, activos.size());
        assertEquals("Walter", activos.get(0).getNombre());
    }


}