package escuela

import grails.rest.RestfulController
import java.text.SimpleDateFormat

class CursoController extends  RestfulController{

    static responseFormats = ['json', 'xml']
    CursoController(){
        super(Curso)
    }

       def createCurso(){
        def data=request.JSON
        def result =[:];
        SimpleDateFormat sdf=new SimpleDateFormat('dd/MM/yyyy')
        Curso curso=new Curso()
        try{
            curso.nombre= data.nombre ? data.nombre:''
            curso.descripcion= data.descripcion ? data.descripcion:''
            curso.duracion= data.duracion ? data.duracion:0
            curso.notaAprobar= data.notaAprobar ? data.notaAprobar: 0f
            curso.estado=1
            curso.validate()
            if(curso.hasErrors()){
                respond(curso.errors,status:422)
            }else{
                if(data.profesorId){
                    Profesor profesor=Profesor.get(data.profesorId)
                    if(profesor!=null){
                        profesor.addToCursos(curso)
                        profesor.save(flush:true)
                        result.id=curso.id
                        result.status=200
                    }else{
                        result.err=true
                        result.status=404
                        result.error="Profesor not found"
                    }
                }else{
                    curso.save(flush:true)
                    result.id=curso.id
                    result.status=200
                }
            }
        }catch (Exception ex) {
            result = [:]
            result.err = true
            result.status = 500
            respond(result)
        }
        respond (result)
    }

    def show(){
        respond(Cursos: Curso.findAllByEstado(1),status: 200)
    }


    def updateCurso(Curso curso){
        try{
            if(curso == null) {
                respond ([err:true,status: 404])
            }
            else {
                curso.validate()
                if(curso.hasErrors()){
                    respond (curso.errors,status: 422)
                }else{
                    curso.save(flush: true)
                    respond(['success':true,'status':200,curso:curso])
                }
            }   }catch (Exception ex) {
            def result = [:]
            result.err = true
            result.status = 500
            respond(result)
        }

    }

    def deleteCurso(Curso curso){
        try{
            if(curso == null) {
                respond ([status: 404])
            }
            else {
                curso.estado=0
                curso.validate()
                if(curso.hasErrors()){
                    respond (errors:curso.errors,status: 422)
                }else{
                    curso.save(flush: true)
                    respond(['success':true,'status':200])
                }
            }
        }catch (Exception ex) {
           def  result = [:]
            result.err = true
            result.status = 500
            respond(result)
        }
    }

    def alumnosByCurso(Curso curso){
        try{
           if(curso!=null){
               respond(idAlumnos:curso.asiste*.id,status:200)
           }else{
               respond(err:true,status:404)
           }
        }catch (Exception ex){
            respond(err:true,status:500)
        }
    }

}
