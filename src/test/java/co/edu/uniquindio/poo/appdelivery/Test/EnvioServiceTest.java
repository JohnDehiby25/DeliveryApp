package co.edu.uniquindio.poo.appdelivery.Test;




import co.edu.uniquindio.poo.appdelivery.model.DTOs.EnvioDTO;
import co.edu.uniquindio.poo.appdelivery.model.envio.Envio;
import co.edu.uniquindio.poo.appdelivery.model.envio.EstadoEnvio;
import co.edu.uniquindio.poo.appdelivery.model.incidencia.Incidencia;
import co.edu.uniquindio.poo.appdelivery.model.pago.EstadoPago;
import co.edu.uniquindio.poo.appdelivery.model.pago.Pago;
import co.edu.uniquindio.poo.appdelivery.model.paquete.Paquete;
import co.edu.uniquindio.poo.appdelivery.service.AdministradorService;
import co.edu.uniquindio.poo.appdelivery.service.EnvioService;
import co.edu.uniquindio.poo.appdelivery.service.UsuarioService;
import co.edu.uniquindio.poo.appdelivery.utils.mappers.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnvioServiceTest {

    private EnvioService envioService;
    private UsuarioService usuarioService;
    private AdministradorService administradorService;

    @BeforeEach
    void setUp() {
        usuarioService = mock(UsuarioService.class);
        administradorService = mock(AdministradorService.class);
        envioService = new EnvioService(usuarioService, administradorService);
    }

    // ============= 1. AGREGAR ENVÍO =============
    @Test
    void testAgregarEnvio() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(1);

        boolean resultado = envioService.agregarEnvio(dto);

        assertTrue(resultado);
        assertEquals(1, envioService.obtenerTodosLosEnvios().size());
    }

    // ============= 2. NO AGREGAR ENVÍO REPETIDO =============
    @Test
    void testAgregarEnvioRepetidoDebeFallar() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(10);

        envioService.agregarEnvio(dto);

        boolean resultado = envioService.agregarEnvio(dto);

        assertFalse(resultado);
    }

    // ============= 3. ACTUALIZAR ESTADO =============
    @Test
    void testActualizarEstado() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(5);

        envioService.agregarEnvio(dto);

        EnvioDTO actualizado = envioService.actualizarEstado(5, EstadoEnvio.EN_RUTA);

        assertEquals(EstadoEnvio.EN_RUTA, actualizado.getEstado());
    }

    // ============= 4. ELIMINAR ENVÍO =============
    @Test
    void testEliminarEnvio() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(3);

        envioService.agregarEnvio(dto);

        boolean eliminado = envioService.eliminarEnvio(3);

        assertTrue(eliminado);
        assertFalse(envioService.existeEnvio(3));
    }

    // ============= 5. CALCULAR COSTO TOTAL =============
    @Test
    void testCalcularCostoTotal() {
        Envio envio = new Envio(1, null, null, 50.0);
        envio.setServiciosAdicionales(new ArrayList<>());
        envio.setPaquete(mock(Paquete.class));

        when(envio.getPaquete().calcularCostoPaquete()).thenReturn(20.0);

        // agregamos manualmente a la lista interna usando reflexión
        List<Envio> lista = (List<Envio>) TestUtils.getField(envioService, "envios");
        lista.add(envio);

        double total = envioService.calcularCostoTotal(1);

        assertEquals(70.0, total);
    }

    // ============= 6. REGISTRAR PAGO =============
    @Test
    void testRegistrarPago() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(8);
        envioService.agregarEnvio(dto);

        Pago pago = new Pago(100.0, LocalDateTime.now(), EstadoPago.APROBADO);

        boolean res = envioService.registrarPago(8, pago);

        assertTrue(res);
        assertNotNull(envioService.buscarEnvioPorId(8).getPago());
    }

    // ============= 7. AGREGAR INCIDENCIA =============
    @Test
    void testAgregarIncidencia() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(15);

        envioService.agregarEnvio(dto);

        Incidencia inc = mock(Incidencia.class);

        boolean res = envioService.agregarIncidencia(15, inc);

        assertTrue(res);
        assertEquals(1, envioService.obtenerIncidenciaActual(15) != null ? 1 : 0);
    }

    // ============= 8. OBTENER ENVÍO POR ID =============
    @Test
    void testObtenerEnvioPorId() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(20);

        envioService.agregarEnvio(dto);

        EnvioDTO encontrado = envioService.buscarEnvioPorId(20);

        assertNotNull(encontrado);
        assertEquals(20, encontrado.getIdEnvio());
    }
}