insert into roles(denominacion) values 
('DOCENTE'),('DIRECTOR DE DEPARTAMENTO'),('COMISION-TIPIFICACIÓN'),('COMISION-RATIFICACION'),('DECANO');
select*from roles;

insert into docentes(DNI, nombres, apellido_pat, apellido_mat,fecha_nacimiento, domicilio, telefono, usuario, contrasenia,fk_idroles) values 
( '57762345','Victoria','Jaramillo','Cardenas','1999-04-22','AV 28 Julio ','845769123','VIC345','57762345' ,10 ),
( '72548035', 'GERVY', 'SALINAS', 'BAZAN','1999-04-22', 'Av.Luzuriaga Nº3311', '978456123','GER035','72548035' ,9 ),
( '87451356', 'STEVE', 'ENRIQUEZ', 'RONDAN','1999-04-22', 'Av.Miraflores', '954231544','STE356','87451356' ,8 ),
( '77564123', 'CARLOS', 'BEGOÑA', 'TAPIR','1999-04-22', 'Av.San Martin', '912345678','CAR123','77564123' ,8 ),
( '51253414', 'JULIO', 'SUAREZ', 'BOLGOÑA','1999-04-22', 'Av.Mandalarias', '921453947','JUL414','51253414' ,8 ),
( '74561235', 'RAMIRTO', 'SUAREZ', 'BOLGOÑA','1999-04-22', 'Av.Mandalarias', '921453947','RAM235','74561235' ,7 ),
( '44465312','Juan','Campos','Verdes','1999-04-22','AV Las Americas','987234561','JUA312','44465312' ,6 ),
( '77762345','Eusebio','Pizarro','Aramburu','1999-04-22','Psj San Pedro','987234571','EUS345','77762345' ,6 ),
( '66762345','Zaida','Gonzales','Moreno','1999-04-22','AV Los Girasoles','981134561','ZAI345','66762345' ,6 ),
( '77722345','Margot','Valverde','Cano','1999-04-22','Jr 13 de Diciembre','987734561','MAR345','77722345' ,6 ),
( '31452345','Benjamin','Terry','Guzman','1999-04-22','AV Las Americas','988834561','BEN345','31452345' ,6 ),
( '71762345','Jennifer','Fujimori','Florentino','1999-04-22','AV Gamarra','997234562','JEN345','71762345' ,6 ),
( '88762345','Styve','Forsyth','Acuña','1999-04-22','Belen','987234321','STY345','88762345' ,6 ),
( '46762343','Estefany','Bautista','Vega','1999-04-22','Jr Sal y Rosas','947231659','EST343','46762343' ,6 ),
( '72548037', 'BENITO', 'ALVARES', 'GUTIERRES','1999-04-22', 'Av.Jose Prada', '945678123','BEN037','72548037' ,6 );

INSERT INTO estado_publicacion(descripcion) values ('Entregado'),('En Tipificación'),('En Ratificación'),('Aceptado'),('Rechazado');
INSERT INTO tipo_publicacion(descripcion) values ('LIBRO'),('MANUAL'),('ENSAYO'),('GUIAS'),('FOLLETOS');
INSERT INTO escuela(nombre_escuela) values ('INGENIERIA DE SISTEMAS'),('MATEMÁTICA'),('ESTADÍSTICA');

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
insert into autores(fk_DNI) values 
('44465312'),('77762345'),('66762345'),('77722345'),('31452345'),('71762345'),('88762345'),('46762343'),('72548037');
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
select p.DNI DNI, p.nombres NOMBRES,p.usuario,p.contrasenia , r.denominacion CARGO 
from docentes p 
inner join roles r 
where r.idroles = p.fk_idroles ; 
DELIMITER //
CREATE procedure p_searchsol(in sdni varchar(8))
begin
select d.DNI, concat(d.nombres,'', d.apellido_pat,'', d.apellido_mat) AUTOR, p.titulo, p.fecha_publicacion, e.nombre_escuela ESCUELA, es.descripcion
from  docentes d inner join autores a on d.DNI=a.fk_DNI inner join publicaciones p on a.idautores=p.fk_idautores 
INNER JOIN escuela e on e.idescuela=p.fk_idescuela inner join estado_publicacion es on es.idestado=p.fkestado_publicacion
where d.DNI=sdni;
END //
DELIMITER ;
call p_searchsol('88762345');
select * from personas;