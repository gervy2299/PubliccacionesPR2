create database mydb;
use mydb;

SELECT * FROM docentes;

insert into roles(denominacion) values 
('DOCENTE'),('DIRECTOR DE DEPARTAMENTO'),('COMISION-TIPIFICACIÓN'),('COMISION-RATIFICACION'),('DECANO');
select*from roles;


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

INSERT INTO estado_publicacion(descripcion) values ('Entregado'),('En Tipificación'),('En Ratificación'),('Aceptado'),('Rechazado');
INSERT INTO tipo_publicacion(descripcion) values ('LIBRO'),('MANUAL'),('ENSAYO'),('GUIAS'),('FOLLETOS');
INSERT INTO escuela(nombre_escuela) values ('INGENIERIA DE SISTEMAS'),('MATEMÁTICA'),('ESTADÍSTICA');

INSERT INTO normas(nronorma, descripcion) values 
('Artículo 21°','Se define un libro como medio no periódico.'),
('Artículo 22°','Los libros son buenos.'),
('Artículo 23°','Cuerpo de libro'),
('Artículo 24°','La estructura mínima que debe cumplir un libro'),
('Artículo 25°','Es un Compendio que sintetiza lo más importante de una materia'),
('Artículo 26°','Todo manual deberá tener ejemplos resueltos, ejercicios propuestos y/o cuestionarios.'),
('Artículo 27°','La estructura mínima que debe cumplir un manual'),
('Artículo 28°','Todo ensayo debe tener como mínimo 40 páginas'),
('Artículo 29°','La estructura mínima que debe cumplir un ensayo'),
('Artículo 30°', 'Las guías deberán tener como mínimo 50 páginas'),
('Artículo 31°', 'La estructura mínima que debe cumplir una guía'),
('Artículo 32°', 'Todo folleto debe tener un mínimo de 20 páginas'),
('Artículo 33°', 'La estructura mínima que debe cumplir un folleto');

DROP DATABASE mydb;

select p.DNI DNI, p.nombres NOMBRES,p.usuario,p.contrasenia , r.denominacion CARGO 
from docentes p 
inner join roles r 
where r.idroles = p.fk_idroles ; 
