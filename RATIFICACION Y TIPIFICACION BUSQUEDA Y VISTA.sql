-- TIPIFICACION
DELIMITER //
CREATE procedure p_busrati(in sdni varchar(8))
begin
select re.DNI, re.NOMBRE, re.TITULO, re.PAGINAS, re.CAPITULO, re.FECHA, re.ESCUELA, re.TIPO, re.ESTADO
 from rati_estado re
 where re.DNI = sdni;
END //
DELIMITER ;
call p_busrati('57762345');

CREATE VIEW rati_estado AS
SELECT d.DNI DNI, concat(d.nombres ,' ', d.apellido_pat, ' ',d.apellido_mat) NOMBRE,p.titulo TITULO, p.cant_paginas PAGINAS, p.numero_capitulos CAPITULO, p.fecha_publicacion FECHA, e.nombre_escuela ESCUELA, tp.descripcion TIPO, ep.descripcion ESTADO
FROM docentes d, publicaciones p, autores a, escuela e, tipo_publicacion tp, estado_publicacion ep
WHERE d.dni = a.fk_DNI 
and a.idautores  = p.fk_idautores 
and e.idescuela = p.fk_idescuela 
and tp.idtipo_publicacion= p.fkidtipo_publicacion 
and ep.idestado = p.fkestado_publicacion 
and p.fkestado_publicacion = 2;
drop view  rati_estado; 
SELECT * FROM rati_estado;

-- RATIFICACION
CREATE VIEW rati_estado1 AS
SELECT d.DNI DNI, concat(d.nombres ,' ', d.apellido_pat, ' ',d.apellido_mat) NOMBRE,p.titulo TITULO, p.cant_paginas PAGINAS, p.numero_capitulos CAPITULO, p.fecha_publicacion FECHA, e.nombre_escuela ESCUELA, tp.descripcion TIPO, ep.descripcion ESTADO
FROM docentes d, publicaciones p, autores a, escuela e, tipo_publicacion tp, estado_publicacion ep
WHERE d.dni = a.fk_DNI 
and a.idautores  = p.fk_idautores 
and e.idescuela = p.fk_idescuela 
and tp.idtipo_publicacion= p.fkidtipo_publicacion 
and ep.idestado = p.fkestado_publicacion 
and p.fkestado_publicacion = 3;
drop view  rati_estado1; 
SELECT * FROM rati_estado1;

DELIMITER //
CREATE procedure p_busrati1(in sdni varchar(8))
begin
select re.DNI, re.NOMBRE, re.TITULO, re.PAGINAS, re.CAPITULO, re.FECHA, re.ESCUELA, re.TIPO, re.ESTADO
 from rati_estado re
 where re.DNI = sdni;
END //
DELIMITER ;
call p_busrati1('57762345');

