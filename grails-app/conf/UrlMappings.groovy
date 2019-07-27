class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(controller: 'Api',action: "index")
        "/escuela"(view:'/index')
        "500"(view:'/error')
        "404"(view: '/error')

        //alumno
        "/createAlumno"(controller:"Alumno", method:"POST",action: "createAlumno")
        "/getAlumnos"(controller:"Alumno", method:"GET",action: "show")
        "/deleteAlumno"(controller:"Alumno", method:"DELETE",action: "deleteAlumno")
        "/updateAlumno"(controller:"Alumno", method:"PUT",action: "updateAlumno")

        //profesor
        "/createProfesor"(controller:"Profesor", method:"POST",action: "createProfesor")
        "/getProfesores"(controller:"Profesor", method:"GET",action: "getProfesores")
        "/deleteProfesor"(controller:"Profesor", method:"DELETE",action: "deleteProfesor")
        "/updateProfesor"(controller:"Profesor", method:"PUT",action: "updateProfesor")

        //Curso
        "/createCurso"(controller:"Curso", method:"POST",action: "createCurso")
        "/getCursos"(controller:"Curso", method:"GET",action: "show")
        "/deleteCurso"(controller:"Curso", method:"DELETE",action: "deleteCurso")
        "/updateCurso"(controller:"Curso", method:"PUT",action: "updateCurso")

//        //titulos
//        "/createTitulos"(controller:"Titulos", method:"POST",action: "createTitulos")
//        "/getTitulos"(controller:"Titulos", method:"GET",action: "getTitulos")
//        "/deleteTitulos"(controller:"Titulos", method:"DELETE",action: "deleteTitulos")
//        "/updateTitulos"(controller:"Titulos", method:"PUT",action: "updateTitulos")


        "/alumnosByCurso"(controller:"curso", method:"get",action: "alumnosByCurso")
        "/cursosByAlumno"(controller:"alumno", method:"get",action: "cursosByAlumno")
        "/finalizaByAlumno"(controller:"alumno", method:"get",action: "finalizaByAlumno")
        "/cursosByProfesor"(controller:"profesor", method:"get",action: "cursosByProfesor")
        "/aprobadosByMateria"(controller:"Finaliza", method:"post",action: "aprobadosByMateria")

	}
}
