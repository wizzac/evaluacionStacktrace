package escuela

import grails.rest.RestfulController

import java.text.SimpleDateFormat

class ProfesorController extends  RestfulController{
    static responseFormats = ['json', 'xml']
    ProfesorController(){
        super(Profesor)
    }


    def getProfesores(){
        respond Profesor.findAllByEstado(1)
    }

    def createProfesor(){
        def data=request.JSON
        def result =[:];
        try{
            SimpleDateFormat sdf=new SimpleDateFormat('dd/MM/yyyy')
            Profesor profesor=new Profesor()
            profesor.nombre= data.nombre ? data.nombre : ''
            profesor.apellido= data.apellido ? data.apellido : ''
            profesor.tipoDocumento= data.tipoDocumento ? data.tipoDocumento : ''
            profesor.numeroDocumento=data.numeroDocumento ? data.numeroDocumento : ''
            if(data.fechaNacimiento && data.fechaNacimiento!='')
                profesor.fechaNacimiento=sdf.parse(data.fechaNacimiento)
            profesor.domicilioActual=data.domicilioActual ? data.domicilioActual : ''
            profesor.sexo=data.sexo ?data.sexo : ''
            profesor.telefono=data.telefono ? data.telefono : ''
            if(data.titulos){
                if(data.titulos.size()>0){
                    for(x in data.titulos){
                        Titulos titulos=new Titulos()
                        titulos.descripcion=x.descripcion ? x.descripcion:''
                        titulos.estado=1
                        titulos.fechaFinalizacion=x.fechaFinalizacion ? sdf.parse(x.fechaFinalizacion):sdf.parse('01/01/1800')
                        profesor.addToTitulos(titulos)
                    }
                }
            }
            profesor.estado=1
            profesor.validate()
            if(profesor.hasErrors()){
                respond profesor.errors
            }else{
                profesor.save(flush:true)
                result.id=profesor.id
            }
        }catch (Exception ex){
            result.err=true
            result.status=500
        }
        respond (result)
    }

    def show(){
        respond(profesores: Profesor.findAllByEstado(1),status:200)
    }


    def updateProfesor(Profesor profesor){
        try{
            if(profesor == null) {
                respond ([status: 404])
            }
            else {
                profesor.validate()
                if(profesor.hasErrors()){
                    respond profesor.errors
                }else{
                    profesor.save(flush: true)
                    respond(['success':true,'status':200,profesor:profesor])
                }
            }
        }catch (Exception ex){
            def result=[:]
            result.err=true
            result.status=500
            respond(result)
        }
    }

    def deleteProfesor(Profesor profesor) {
        try {
            if (profesor == null) {
                respond([status: 404])
            } else {
                profesor.estado = 0
                profesor.validate()
                if (profesor.hasErrors()) {
                    respond(errors: profesor.errors, status: 422)
                } else {
                    profesor.save(flush: true)
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

    def cursosByProfesor(Profesor profesor){
        try{
            if(profesor!=null)
                respond(idCurso:profesor.cursos*.id,status: 200)
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
