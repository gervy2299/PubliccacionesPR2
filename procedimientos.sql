
use mydb;

SET FOREIGN_KEY_CHECKS=0;

/* Procedimiento para cargar usuario*/
DELIMITER //
CREATE PROCEDURE p_usuario(in usu varchar(6))
BEGIN
SELECT d.nombres,d.apellido_pat,d.apellido_mat,r.denominacion 
FROM docentes d 
INNER JOIN roles r on r.idroles=d.fk_idroles 
WHERE usuario = usu;
END //
DELIMITER ;
CALL p_usuario("VIC345");
/* procedimiento para Mostrar publicacion segun autor*/
DELIMITER //
CREATE PROCEDURE p_pubs(in usu varchar(6))
BEGIN
Select p.titulo,p.fecha_publicacion,p.fkidtipo_publicacion,p.fk_idescuela,p.fkestado_publicacion
from autores a
INNER JOIN docentes d ON a.fk_DNI=d.DNI
INNER JOIN publicaciones p ON p.fk_idautores=a.idautores
WHERE d.usuario=usu
group by p.fecha_publicacion;
END //
DELIMITER ;
CALL p_pubs('VIC345');
-- falta terminar
DROP PROCEDURE p_pubs;
/* procedimiento para llamar idautor segun docente.usuario*/
DELIMITER //
CREATE PROCEDURE p_idautor(in idusu varchar(6))
BEGIN
SELECT a.idautores,d.nombres
FROM autores a
INNER JOIN docentes d ON d.DNI=a.fk_DNI
WHERE d.usuario=idusu;
END //
DELIMITER ;
CALL p_idautor("VIC345");
/* procedimiento para llamar datos del docente segun docente.usuario*/
DELIMITER //
CREATE PROCEDURE p_datosDoc(in idusu varchar(6))
BEGIN
SELECT d.nombres, r.denominacion Cargo
FROM docentes d
INNER JOIN roles r ON d.fk_idroles=r.idroles
WHERE d.usuario=idusu;
END //
DELIMITER ;
CALL p_datosDoc("VIC345");
DROP procedure p_datosDoc;







insert into roles(denominacion) values ('DOCENTE'),('DIRECTOR DE DEPARTAMENTO'),('COMISION-TIPIFICACIÓN'),('COMISION-RATIFICACION'),('DECANO');
insert into docentes(DNI, nombres, apellido_pat, apellido_mat,fecha_nacimiento, domicilio, telefono, usuario, contrasenia,fk_idroles) values 
( '57762345','Victoria','Jaramillo','Cardenas','1999-04-22','AV 28 Julio ','845769123','VIC345','57762345' ,5 ),
( '72548035', 'GERVY', 'SALINAS', 'BAZAN','1999-04-22', 'Av.Luzuriaga Nº3311', '978456123','GER035','72548035' ,4 ),
( '87451356', 'STEVE', 'ENRIQUEZ', 'RONDAN','1999-04-22', 'Av.Miraflores', '954231544','STE356','87451356' ,3 ),
( '77564123', 'CARLOS', 'BEGOÑA', 'TAPIR','1999-04-22', 'Av.San Martin', '912345678','CAR123','77564123' ,3 ),
( '51253414', 'JULIO', 'SUAREZ', 'BOLGOÑA','1999-04-22', 'Av.Mandalarias', '921453947','JUL414','51253414' ,3 ),
( '74561235', 'RAMIRTO', 'SUAREZ', 'BOLGOÑA','1999-04-22', 'Av.Mandalarias', '921453947','RAM235','74561235' ,2 ),
( '44465312','Juan','Campos','Verdes','1999-04-22','AV Las Americas','987234561','JUA312','44465312' ,1 ),
( '77762345','Eusebio','Pizarro','Aramburu','1999-04-22','Psj San Pedro','987234571','EUS345','77762345' ,1 ),
( '66762345','Zaida','Gonzales','Moreno','1999-04-22','AV Los Girasoles','981134561','ZAI345','66762345' ,1 ),
( '77722345','Margot','Valverde','Cano','1999-04-22','Jr 13 de Diciembre','987734561','MAR345','77722345' ,1 ),
( '31452345','Benjamin','Terry','Guzman','1999-04-22','AV Las Americas','988834561','BEN345','31452345' ,1 ),
( '71762345','Jennifer','Fujimori','Florentino','1999-04-22','AV Gamarra','997234562','JEN345','71762345' ,1 ),
( '88762345','Styve','Forsyth','Acuña','1999-04-22','Belen','987234321','STY345','88762345' ,1 ),
( '46762343','Estefany','Bautista','Vega','1999-04-22','Jr Sal y Rosas','947231659','EST343','46762343' ,1 ),
( '72548037', 'BENITO', 'ALVARES', 'GUTIERRES','1999-04-22', 'Av.Jose Prada', '945678123','BEN037','72548037' ,1 );
INSERT INTO escuela(nombre_escuela) values ('INGENIERIA DE SISTEMAS'),('MATEMÁTICA'),('ESTADÍSTICA');
select*from estado_publicacion;
select*from tipo_publicacion;
INSERT INTO normas(nronorma, descripcion,fkidtipo_publicacion) values 
('Artículo 21°','Se define un libro como medio no periódico.',1),
('Artículo 22°','Los libros son buenos.',1),
('Artículo 23°','Cuerpo de libro',1),
('Artículo 24°','La estructura mínima que debe cumplir un libro',1),
('Artículo 25°','Es un Compendio que sintetiza lo más importante de una materia',2),
('Artículo 26°','Todo manual deberá tener ejemplos resueltos, ejercicios propuestos y/o cuestionarios.',2),
('Artículo 27°','La estructura mínima que debe cumplir un manual',2),
('Artículo 28°','Todo ensayo debe tener como mínimo 40 páginas',3),
('Artículo 29°','La estructura mínima que debe cumplir un ensayo',3),
('Artículo 30°', 'Las guías deberán tener como mínimo 50 páginas',4),
('Artículo 31°', 'La estructura mínima que debe cumplir una guía',4),
('Artículo 32°', 'Todo folleto debe tener un mínimo de 20 páginas',5),
('Artículo 33°', 'La estructura mínima que debe cumplir un folleto',5);
SELECT nronorma, descripcion FROM normas;
insert into autores(fk_DNI) values 
('57762345'),('72548035'),('87451356'),('77564123'),('51253414'),('74561235'),
('44465312'),('77762345'),('66762345'),('77722345'),('31452345'),('71762345'),
('88762345'),('46762343'),('72548037');
delete from autores where idautores='234';
select*from autores;
insert into publicaciones(titulo,cant_paginas,fecha_publicacion,numero_capitulos,fk_idautores,fk_idescuela,fkidtipo_publicacion,fkestado_publicacion) values
('LA CIENCIA TEGNOLOGICA',200,'2021-05-04','10',1,1,1,2),
('LAS NANOMAQUINAS',300,'2021-05-03','12',2,1,1,1),
('NMEROS´PERFECTOS',50,'2021-05-04','2',3,2,2,1),
('ESTADISTICA APLICADA',30,'2021-05-04',null,4,3,2,4),
('LA TECNOLOGIA AMBIENTAL',15,'2021-05-05',null,5,1,3,4),
('LAS MATEMATICAS EN EL MUNDO',20,'2021-05-06',null,6,2,3,2),
('GUIA ESTADISTICA',150,'2021-05-07','8',7,3,4,1),
('GUIA DE PROBABILIDADES',180,'2021-05-07','9',8,3,4,4),
('UNIVERSITARIO',2,'2021-05-08',null,9,2,5,5);
select*from publicaciones;
