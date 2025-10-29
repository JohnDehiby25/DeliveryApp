package co.edu.uniquindio.poo.appdelivery.model.persona;

public class PersonaBuilder {
    protected String nombre;
    protected String id;
    protected String telefono;
    protected String email;


    public PersonaBuilder(String nombre, String id, String telefono, String email) {

        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
        this.email = email;
    }

    // Métodos "with"
    public PersonaBuilder withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PersonaBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PersonaBuilder withTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public PersonaBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    // Metodo build
    public Persona build() {
        return new Persona(nombre, id, telefono, email);
    }
}
