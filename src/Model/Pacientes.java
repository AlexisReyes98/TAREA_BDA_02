
package Model;

/**
 *
 * @author alexis
 */
public class Pacientes {
    private int id_paciente;
    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;
    private String motivo_consulta;

    // Constructor basio
    public Pacientes() {
    }

    // Constructor
    public Pacientes(int id_paciente, String nombre, String apellido, int edad, String sexo, String motivo_consulta) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.motivo_consulta = motivo_consulta;
    }

    // Getters y Setters
    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMotivo_consulta() {
        return motivo_consulta;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }
    
}
