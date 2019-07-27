package escuela

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ProfesorController)
@Mock([Curso,Alumno,Profesor,Titulos])

class ProfesorControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "valid profesor insert"() {
        when:
        request.method = 'POST'
        request.setJSON(
                nombre:'pedro',
                apellido:'perez',
                tipoDocumento:'Dni',
                numeroDocumento:'654321654',
                fechaNacimiento:'12/11/1992',
                domicilioActual:'justo aca',
                sexo:'M',
                telefono:'321564'
        )

        controller.createProfesor()
        then:
        response.json.status == 200
        response.json.id > 0
    }

    void "show profesor"(){
        when:
        request.method = 'get'
        controller.show()
        then:
            response.json.status==200
            response.json.alumnos.size()>=0;
    }

    void "invalid update profesor"(){
        when:
        request.method = 'PUT'
        controller.updateProfesor()
        then:
        response.json.status==422
    }

    void " delete profesor"(){
        when:
        controller.deleteProfesor()
            request.method='DELETE'
        then:
            response.status==200
            response.json.status==200
            response.json.success==true
    }

    void "cursosByProfesor valid"(){
        when:
            request.method='GET'
            params.id= 1
            controller.cursosByProfesor()
        then:
            response.status==200
            response.json.status==200
            response.json.success==true
    }

}

