package co.edu.uniquindio.poo.appdelivery.utils.mappers;

import java.lang.reflect.Field;

public class TestUtils {
    public static Object getField(Object obj, String fieldName) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// ===============================================
// TestUtils: ¿PARA QUÉ SIRVE?
// -----------------------------------------------
// Esta clase se usa únicamente en PRUEBAS UNITARIAS
// cuando necesitamos acceder a ATRIBUTOS PRIVADOS
// de una clase (por ejemplo listas o estados internos).
//
// ¿Por qué?
// Porque en Java, desde una prueba solo podemos acceder
// a métodos públicos. Si una clase NO tiene getters,
// no podemos leer ni modificar esos atributos.
//
// Con esta utilidad podemos usar REFLEXIÓN para:
//   - Leer un atributo privado
//   - Modificar un atributo privado
//   - Verificar el contenido de listas privadas
//   - Manipular estados internos durante los tests
//
// ¿Cuándo usarlo?
//   - Cuando la clase NO tiene getters o métodos públicos
//     para acceder a sus datos internos.
//   - Ejemplo: private List<Envio> envios;
//
// ¿Cuándo NO usarlo?
//   - Si la clase ya ofrece getters o métodos públicos.
//   - Si puedes hacer la prueba sin tocar atributos privados.
//
// Nota importante:
//   - Es SOLO para pruebas. Nunca usar reflexión en código real.
//   - Lo ideal es crear getters para evitar esta clase,
//     pero cuando no es posible, TestUtils ayuda.
//
// ===============================================

