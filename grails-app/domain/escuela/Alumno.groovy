package escuela

import grails.rest.*

class Alumno  extends escuela.EstadoSimple{

    String tipoDocumento
    String numeroDocumento
    String nombre
    String apellido
    Date fechaNacimiento
    String domicilioActual
    String sexo
    String telefono
    static hasMany = [cursa:Curso]
    static belongsTo = Curso


    static mapping={
        version(false)
    }
    static constraints = {
        nombre(nullable:false)
        apellido(nullable: false)
        nombre(maxSize: 50)
        apellido(maxSize: 50)
    }


}
