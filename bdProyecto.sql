drop schema if exists tiquiviajes;
drop user if exists usuarioBD;

create schema tiquiviajes;
use tiquiviajes;

create user 'usuarioBD'@'%' identified by 'claveBD123';
grant all privileges on tiquiviajes.* to 'usuarioBD'@'%';
flush privileges;

create table usuario(
	idUsuario int primary key auto_increment,
    nombre varchar(50) not null,
    primerApellido varchar(50) not null,
    segundoApellido varchar(50) not null,
    correoElectronico varchar(50) not null,
    contrasena varchar(10) not null,
    telefono varchar(8) not null,
    direccion varchar(255) not null,
    idiomaPreferido boolean not null
);

-- si el idiomaPreferido es true es ES, si es false es EN
insert into usuario (nombre, primerApellido, segundoApellido, correoElectronico, contrasena, telefono, direccion, idiomaPreferido)
values
('Juan', 'Pérez', 'González', 'juan.perez@example.com', 'password1', '12345678', 'Calle Falsa 123, Ciudad', true),
('María', 'López', 'Martínez', 'maria.lopez@example.com', 'password2', '87654321', 'Avenida Siempre Viva 456, Ciudad', false),
('Carlos', 'Gómez', 'Hernández', 'carlos.gomez@example.com', 'password3', '12348765', 'Boulevard Principal 789, Ciudad', true);

create table destino(
	idDestino int primary key auto_increment,
    nombre varchar(50) not null,
    ubicacion varchar(50) not null,
    tiempoViaje varchar(10) not null
);

insert into destino (nombre, ubicacion, tiempoViaje)
values
('París', 'Francia', '10 horas'),
('Nueva York', 'Estados Unidos', '8 horas'),
('Tokio', 'Japón', '14 horas');

create table paquete(
	idPaquete int primary key auto_increment,
    nombre varchar(50) not null,
    descripcion varchar(255) not null,
    precio double not null,
    incluyeAlojamiento boolean not null,
    incluyeTransporte boolean not null,
    incluyeActividades boolean not null,
	idDestino int not null,
    foreign key (idDestino) references destino(idDestino)
);

-- si en los campos de incluyeXcosa es true es pq si, y si es false es pq no
insert into paquete (nombre, descripcion, precio, incluyeAlojamiento, incluyeTransporte, incluyeActividades, idDestino)
values
('Paquete París Romántico', 'Viaje romántico a París con alojamiento y visitas guiadas', 1200.00, true, true, true, 1),
('Aventura en Nueva York', 'Explora Nueva York con transporte incluido y actividades diarias', 1500.00, false, true, true, 2),
('Descubre Tokio', 'Visita Tokio con alojamiento y excursiones', 1800.00, true, false, true, 3);

create table reserva(
	idReserva int primary key auto_increment,
    tipoReserva boolean not null,
    fechaReserva date not null,
    fechaInicio date not null,
    fechaFin date not null,    
    estado int not null,
    idUsuario int not null,
    foreign key (idUsuario) references usuario(idUsuario)
);

-- el tipoReserva true es paquete y el false es destino sin paquete
-- el estado 1 es confirmada, el 2 es pendiente y el 3 es cancelada
insert into reserva (tipoReserva, fechaReserva, fechaInicio, fechaFin, estado, idUsuario)
values
(true, '2023-07-01', '2023-07-15', '2023-07-25', 1, 1),
(false, '2023-08-01', '2023-08-10', '2023-08-20', 1, 2),
(true, '2023-09-01', '2023-09-15', '2023-09-25', 2, 3);