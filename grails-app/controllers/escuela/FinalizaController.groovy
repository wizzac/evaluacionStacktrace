package escuela

import grails.rest.RestfulController

class FinalizaController extends RestfulController{

    static responseFormats = ['json', 'xml']
    FinalizaController(){
        super(Finaliza)
    }

    def aprobadosByMateria(){
        def res = [:]
        def result=new ArrayList()
        def data=request.JSON

        try{
            def listaAlumnos
            if(data.id){
                listaAlumnos=Finaliza.findAllByCurso(data.id)
            }
            for(x in listaAlumnos){
                result.add(x.alumno)
            }
        }catch (Exception ex) {

            res.err = true
            res.status = 500
            respond(res)
        }
        respond(idAlumnos:result,status: 200)
    }
}
