package escuela

class Titulos extends escuela.EstadoSimple{

    String descripcion
    Date fechaFinalizacion
    static belongsTo = [profesor:Profesor]


    static mapping={
        version(false)

    }

    static constraints = {

    }
}
