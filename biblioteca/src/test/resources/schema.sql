create table prestamo (
 id int(11) not null auto_increment,
 isbn varchar(100) not null,
 identificacion_usuario varchar(100) not null,
 tipo_usuario int(5) not null,
 fecha_maxima_devolucion varchar(100) not null,
 primary key (id)
);
