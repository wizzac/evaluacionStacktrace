package escuela

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification


/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AlumnoController)
@Mock([Alumno,AlumnoController])
class AlumnoControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "invalid alumno insert"() {
        when:
            request.method = 'POST'
            controller.createAlumno()
        then:
            response.json.status == 422
    }


    void "valid alumno insert"() {
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

        controller.createAlumno()
        then:
            response.json.status == 200
            response.json.id > 0
    }

    void "show alumnos"(){
        when:
            request.method = 'get'
            controller.show()
        then:
            response.json.status==200
            response.json.alumnos.size()>=0;
    }

    void "invalid update alumno"(){
        when:
            request.method = 'PUT'
            controller.updateAlumno()
        then:
            response.json.status==422
    }
    void " delete alumno"(){
        when:
            request.method='DELETE'
            controller.deleteAlumno(Alumno.get(5))
        then:
            response.status==200
            response.json.status==200
            response.json.success==true
    }

    void "cursosByAlumno valid"(){
        when:
            request.method='GET'
            params.id= 1
            controller.cursosByAlumno()
        then:
            response.status==200
            response.json.status==200
            response.json.success==true
    }
}
