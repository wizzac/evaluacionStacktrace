package escuela

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(FinalizaController)
class FinalizaControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "aprobadosByMateria valida"() {
        when:
            request.method = 'POST'
            request.setJSON(
                    id:1
            )
            controller.aprobadosByMateria()
        then:
            response.json.status == 200
            response.json.idAlumnos.size() >=0
    }

    void "aprobadosByMateria invalida"() {
        when:
        request.method = 'POST'
        request.setJSON(
                profesorId:50
        )
        controller.aprobadosByMateria()
        then:
        response.json.status == 404
        response.json.err==true
    }
}
