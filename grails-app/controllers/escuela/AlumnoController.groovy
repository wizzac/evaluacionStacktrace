package escuela

import grails.converters.JSON
import grails.rest.RestfulController
import org.codehaus.groovy.grails.web.json.*

import java.text.SimpleDateFormat;

class AlumnoController extends RestfulController {

    static responseFormats = ['json', 'xml']

    AlumnoController() {
        super(Alumno)
    }

    def getAlumnos() {
        respond Alumno.findAllByEstado(1)
    }

    def createAlumno() {
        def data = request.JSON
        SimpleDateFormat sdf = new SimpleDateFormat('dd/MM/yyyy')
        Alumno alumno = new Alumno()
        def result = [:];
        try {
            alumno.nombre = data.nombre ? data.nombre : ''
            alumno.apellido = data.apellido ? data.apellido : ''
            alumno.tipoDocumento = data.tipoDocumento ? data.tipoDocumento : ''
            alumno.numeroDocumento = data.numeroDocumento ? data.numeroDocumento : ''
            if (data.fechaNacimiento && data.fechaNacimiento != '')
                alumno.fechaNacimiento = sdf.parse(data.fechaNacimiento)
            alumno.domicilioActual = data.domicilioActual ? data.domicilioActual : ''
            alumno.sexo = data.sexo ? data.sexo : ''
            alumno.telefono = data.telefono ? data.telefono : ''
            alumno.estado = 1
            alumno.validate()
            if (alumno.hasErrors()) {
                respond(err:alumno.errors,status: 422)
            } else {
                alumno.save(flush: true)
                result.id = alumno.id
                result.status = 200
            }
            respond(result)
        } catch (Exception ex) {
            result = [:]
            result.err = true
            result.status = 500
            respond(result)
        }
    }

    def show() {
        respond(alumnos: Alumno.findAllByEstado(1),status: 200)
    }

    def updateAlumno(Alumno alumno) {
        try {
            if (alumno == null) {
                respond([status: 404])
            } else {
                alumno.validate()
                if (alumno.hasErrors()) {
                    respond(errors: alumno.errors, status: 422)
                } else {
                    alumno.save(flush: true)
                    respond(['success': true, 'status': 200, alumno: alumno])
                }
            }
        } catch (Exception ex) {
            def result = [:]
            result.err = true
            result.status = 500
            respond(result)
        }
    }

    def deleteAlumno(Alumno alumno) {
        try {
            if (alumno == null) {
                respond([status: 404])
            } else {
                alumno.estado = 0
                alumno.validate()
                if (alumno.hasErrors()) {
                    respond alumno.errors
                } else {
                    alumno.save(flush: true)
                    respond(['success': true, 'status': 200])
                }
            }
        } catch (Exception ex) {
            def result = [:]
            result.err = true
            result.status = 500
            respond(result)
        }
    }


    def cursosByAlumno(Alumno alumno){
        try{
            if(alumno!=null)
                respond(idCurso:alumno.cursa*.id,status: 200)
            else
                respond(err:true,status: 404)
        }catch (Exception ex){
            def result = [:]
            result.err = true
            result.status = 500
            respond(result)
        }
    }

    def finalizaByAlumno(Alumno alumno){
        try{
            if(alumno!=null){
                def listaFinaliza = Finaliza.findAllByAlumno(alumno)
                respond(idCurso:listaFinaliza.curso*.id,status: 200)
            }
            else
                respond(err:true,status: 404)
        }catch (Exception ex){
            def result = [:]
            result.err = true
            result.status = 500
            respond(result)
        }
    }

}
