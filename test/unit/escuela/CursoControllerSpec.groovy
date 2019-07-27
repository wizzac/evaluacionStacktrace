package escuela

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CursoController)
@Mock([Curso,Alumno,Profesor,CursoController])

class CursoControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void ""() {

    }
    void "test createCurso sin parametros"() {
        when:
        request.method = 'POST'
        controller.createCurso()
        then:
            response.json.status == 200
            response.json.id > 0
    }

    void "test createCurso con profesor"(){
        when:
        request.method = 'POST'
        request.setJSON(
                profesorId:2
        )
        controller.createCurso()
        then:
        response.json.status == 200
        response.json.id > 0

    }

    void "test createCurso con profesor invalido"(){
        when:
        request.method = 'POST'
        request.setJSON(
                profesorId:50
        )
        controller.createCurso()
        then:
        response.json.status == 404
        response.json.err==true
    }

    void "test show"() {
        when:
            request.method = 'get'
            controller.show()
        then:
            response.json.status==200
            response.json.cursos.size()>=0;

    }

    void "test updateCurso profesor invalido"(){
        when:
        request.method = 'PUT'
        request.setJSON(
                profesorId:50
        )
        controller.updateCurso()
        then:
            response.json.status == 404
            response.json.err==true
    }

    void "test updateCurso profesor valido"(){
        when:
        request.method = 'PUT'
        request.setJSON(
                cursoid:1,
                nombre:'curso nuevo'
        )
        controller.updateCurso()
        then:
            response.json.status == 200
            response.json.success==true
    }

    void "test delete curso"() {
        when:
            request.method='DELETE'
            params.id= 1
            controller.deleteCurso()
        then:
            response.json.status==200
            response.json.success==true
    }

    void "test delete curso no existe"() {
        when:
            request.method='DELETE'
            params.id= 50
            controller.deleteCurso()
        then:
            response.json.status==404
    }

    void "test alumnosByCurso"() {
        when:
            request.method='GET'
            params.id= 1
            controller.cursosByAlumno()
        then:
            response.json.status==200
            response.json.idAlumnos.size>=0
    }

    void "test alumnosByCurso invalido"() {
        when:
            request.method='GET'
            params.id= 50
            controller.cursosByAlumno()
        then:
            response.json.status==404
            response.json.err==true
    }
}
