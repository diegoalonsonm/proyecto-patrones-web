drop schema if exists tiquiviajes;
drop user if exists usuarioBD;

create schema tiquiviajes;
use tiquiviajes;

create user 'usuarioBD'@'%' identified by 'claveBD123';
grant all privileges on tiquiviajes.* to 'usuarioBD'@'%';
flush privileges;

create table usuario(
                        id_usuario int primary key auto_increment,
                        nombre varchar(50) not null,
                        primer_apellido varchar(50) not null,
                        segundo_apellido varchar(50) not null,
                        correo_electronico varchar(50) not null,
                        contrasena varchar(10) not null,
                        telefono varchar(8) not null,
                        direccion varchar(255) not null
);

-- si el idiomaPreferido es true es ES, si es false es EN
insert into usuario (nombre, primer_apellido, segundo_apellido, correo_electronico, contrasena, telefono, direccion)
values
    ('Juan', 'Pérez', 'González', 'juan.perez@example.com', 'password1', '12345678', 'Calle Falsa 123, Ciudad'),
    ('María', 'López', 'Martínez', 'maria.lopez@example.com', 'password2', '87654321', 'Avenida Siempre Viva 456, Ciudad'),
    ('Carlos', 'Gómez', 'Hernández', 'carlos.gomez@example.com', 'password3', '12348765', 'Boulevard Principal 789, Ciudad');

create table destino(
                        id_destino int primary key auto_increment,
                        pais varchar(50) not null,
                        ciudad varchar(50) not null,
                        tiempo_viaje varchar(10) not null,
                        precio_persona float not null
);

insert into destino (pais, ciudad, tiempo_viaje, precio_persona)
values
    ('París', 'Francia', '10', 300.0),
    ('Nueva York', 'Estados Unidos', '8', 294.68),
    ('Tokio', 'Japón', '14', 540.38);

create table paquete(
                        id_paquete int primary key auto_increment,
                        nombre varchar(50) not null,
                        descripcion varchar(255) not null,
                        precio double not null,
                        incluye_alojamiento boolean not null,
                        incluye_transporte boolean not null,
                        incluye_actividades boolean not null,
                        id_destino int not null,
                        foreign key (id_destino) references destino(id_destino)
);

-- si en los campos de incluyeXcosa es true es pq si, y si es false es pq no
insert into paquete (nombre, descripcion, precio, incluye_alojamiento, incluye_transporte, incluye_actividades, id_destino)
values
    ('Paquete París Romántico', 'Viaje romántico a París con alojamiento y visitas guiadas', 1200.00, true, true, true, 1),
    ('Aventura en Nueva York', 'Explora Nueva York con transporte incluido y actividades diarias', 1500.00, false, true, true, 2),
    ('Descubre Tokio', 'Visita Tokio con alojamiento y excursiones', 1800.00, true, false, true, 3);

create table reserva(
                        id_reserva int primary key auto_increment,
                        tipo_reserva boolean not null,
                        fecha_reserva date not null,
                        fecha_inicio date not null,
                        fecha_fin date not null,
                        estado int not null,
                        id_usuario int not null,
                        foreign key (id_usuario) references usuario(id_usuario)
);

-- el tipoReserva true es paquete y el false es destino sin paquete
-- el estado 1 es confirmada, el 2 es pendiente y el 3 es cancelada
insert into reserva (tipo_reserva, fecha_reserva, fecha_inicio, fecha_fin, estado, id_usuario)
values
    (true, '2023-07-01', '2023-07-15', '2023-07-25', 1, 1),
    (false, '2023-08-01', '2023-08-10', '2023-08-20', 1, 2),
    (true, '2023-09-01', '2023-09-15', '2023-09-25', 2, 3);

create table rol(
                    id_rol int not null auto_increment primary key,
                    nombre varchar(20) not null,
                    id_usuario int not null,
                    foreign key (id_usuario) references usuario(id_usuario)
);

insert into rol (nombre, id_usuario) values
                                         ("ADMIN", 1), ("VENDEDOR", 1), ("USER", 1),
                                         ("VENDEDOR", 2), ("USER", 2),
                                         ("USER", 3);