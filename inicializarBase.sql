INSERT INTO `escuela`.`alumno`(`apellido`,`domicilio_actual`,`estado`,`fecha_nacimiento`,`nombre`,`numero_documento`,`sexo`,`telefono`,`tipo_documento`)
VALUES('parez','justo aca',1,'1996-02-02','pablo',388338,'m','321654','dni');
INSERT INTO `escuela`.`alumno`(`apellido`,`domicilio_actual`,`estado`,`fecha_nacimiento`,`nombre`,`numero_documento`,`sexo`,`telefono`,`tipo_documento`)
VALUES('alumno2','justo aca',1,'1996-02-02','alumno2',388338,'m','321654','dni');
INSERT INTO `escuela`.`alumno`(`apellido`,`domicilio_actual`,`estado`,`fecha_nacimiento`,`nombre`,`numero_documento`,`sexo`,`telefono`,`tipo_documento`)
VALUES('alumno3','justo aca',1,'1996-02-02','alumno3',388338,'m','321654','dni');

INSERT INTO `escuela`.`profesor`(`apellido`,`domicilio_actual`,`estado`,`fecha_nacimiento`,`nombre`,`numero_documento`,`sexo`,`telefono`,`tipo_documento`)
VALUES('profesor1','justo aca',1,'1996-02-02','profesor1',388338,'m','321654','dni');
INSERT INTO `escuela`.`profesor`(`apellido`,`domicilio_actual`,`estado`,`fecha_nacimiento`,`nombre`,`numero_documento`,`sexo`,`telefono`,`tipo_documento`)
VALUES('profesor2','justo aca',1,'1996-02-02','profesor2',388338,'m','321654','dni');
INSERT INTO `escuela`.`profesor`(`apellido`,`domicilio_actual`,`estado`,`fecha_nacimiento`,`nombre`,`numero_documento`,`sexo`,`telefono`,`tipo_documento`)
VALUES('profesor3','justo aca',1,'1996-02-02','profesor3',388338,'m','321654','dni');


INSERT INTO `escuela`.`titulos`(`descripcion`,`estado`,`fecha_finalizacion`,`profesor_id`)VALUES('Doctorado en doctoradez',1,curdate(),1);
INSERT INTO `escuela`.`titulos`(`descripcion`,`estado`,`fecha_finalizacion`,`profesor_id`)VALUES('Doctorado en huevadas',1,curdate(),1);
INSERT INTO `escuela`.`titulos`(`descripcion`,`estado`,`fecha_finalizacion`,`profesor_id`)VALUES('titulo secundario',1,curdate(),3);

INSERT INTO `escuela`.`curso`(`descripcion`,`duracion`,`estado`,`nombre`,`nota_aprobar`,`profesor_id`)
VALUES('cocina',200,1,'curso de cocina',8,1);
INSERT INTO `escuela`.`curso`(`descripcion`,`duracion`,`estado`,`nombre`,`nota_aprobar`,`profesor_id`)
VALUES('computacion',200,1,'curso computacion',8,2);
INSERT INTO `escuela`.`curso`(`descripcion`,`duracion`,`estado`,`nombre`,`nota_aprobar`,`profesor_id`)
VALUES('mecanografia',200,1,'curso de mecanografia',8,3);


insert into curso_asiste values(1,1);
insert into curso_asiste values(2,2);
insert into curso_asiste values(3,3);


INSERT INTO `escuela`.`finaliza`(`alumno_id`,`curso_id`)VALUES(1,1);
INSERT INTO `escuela`.`finaliza`(`alumno_id`,`curso_id`)VALUES(1,2);
