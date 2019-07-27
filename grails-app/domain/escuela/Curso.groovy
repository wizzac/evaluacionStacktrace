package escuela

class Curso extends escuela.EstadoSimple{

    String nombre
    String descripcion
    Integer duracion
    Float notaAprobar
    Profesor profesor
    static hasMany = [asiste:Alumno]



    static mapping = {
        version(false)
    }

    static constraints = {
        profesor(nullable: true)
    }
}
