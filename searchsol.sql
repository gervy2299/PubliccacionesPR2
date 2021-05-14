DELIMITER //
CREATE procedure p_searchsol(in sdni varchar(8))
begin
select d.DNI, concat(d.nombres,'', d.apellido_pat,'', d.apellido_mat) AUTOR, p.titulo, p.fecha_publicacion, e.nombre_escuela ESCUELA, es.descripcion
from  docentes d inner join autores a on d.DNI=a.fk_DNI inner join publicaciones p on a.idautores=p.fk_idautores 
INNER JOIN escuela e on e.idescuela=p.fk_idescuela inner join estado_publicacion es on es.idestado=p.fkestado_publicacion
where d.DNI=sdni;
END //
DELIMITER ;