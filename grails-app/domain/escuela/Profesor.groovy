package escuela

class Profesor extends escuela.EstadoSimple{


    String nombre
    String apellido
    String tipoDocumento
    String numeroDocumento
    Date fechaNacimiento
    String domicilioActual
    String sexo
    String telefono

    static hasMany = [titulos: Titulos,cursos:Curso]

    static mapping = {
        version(false)
    }

    static constraints = {
    }
}
