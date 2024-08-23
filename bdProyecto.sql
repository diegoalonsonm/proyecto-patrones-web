drop schema if exists tiquiviajes;
drop user if exists usuarioBD;

create schema tiquiviajes;
use tiquiviajes;

create user 'usuarioBD'@'%' identified by 'claveBD123';
grant all privileges on tiquiviajes.* to 'usuarioBD'@'%';
flush privileges;

create table usuario(
	id_usuario int primary key auto_increment,
    username varchar(20) not null,
	nombre varchar(50) not null,
	primer_apellido varchar(50) not null,
	segundo_apellido varchar(50) not null,
	correo_electronico varchar(50) not null,
	password varchar(10) not null,
	telefono varchar(8) not null,
	direccion varchar(255) not null,
    ruta_imagen varchar(1024)
);

-- si el idiomaPreferido es true es ES, si es false es EN
insert into usuario (username, nombre, primer_apellido, segundo_apellido, correo_electronico, password, telefono, direccion)
values ('juansito47', 'Juan', 'Pérez', 'González', 'juan.perez@example.com', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', '12345678', 'Calle Falsa 123, Ciudad'),
    ('marx222', 'María', 'López', 'Martínez', 'maria.lopez@example.com', '$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi', '87654321', 'Avenida Siempre Viva 456, Ciudad'),
    ('miTioCharlie', 'Carlos', 'Gómez', 'Hernández', 'carlos.gomez@example.com', '$2a$10$koGR7eS22Pv5KdaVJKDcge04ZB53iMiw76.UjHPY.XyVYlYqXnPbO', '12348765', 'Boulevard Principal 789, Ciudad');

create table destino(
	id_destino int primary key auto_increment,
	pais varchar(50) not null,
	ciudad varchar(50) not null,
	tiempo_viaje varchar(10) not null,
	precio_persona float not null,
	url_imagen varchar(1024)
);

insert into destino (pais, ciudad, tiempo_viaje, precio_persona, url_imagen)
values
    ('Francia', 'París', '10', 300.0, 'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Fparis.jpg?alt=media&token=1658db5c-cc6f-4784-a2df-97ddd5dd518f'),
    ('Estados Unidos', 'New York', '4', 294.68, 'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Fny.jpg?alt=media&token=bf90f7cf-1c03-4381-8688-b7cd390c46d3'),
    ('Inglaterra', 'Londres', '9', 699.24,  'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Flondon.png?alt=media&token=d33c12cc-df3b-403a-bdda-9fd7a62de7e1'),
    ('Japón', 'Tokyo', '14', 540.38, 'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Ftokyo.webp?alt=media&token=5ad99494-4a4c-435b-ba7b-d5a349ff48dc');

insert into destino (pais, ciudad, tiempo_viaje, precio_persona)values ('España', 'Madrid', '10', 1000.32);

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
values ('Paquete París Romántico', 'Viaje romántico a París con alojamiento y visitas guiadas', 1200.00, true, true, true, 1),
    ('Aventura en Nueva York', 'Explora Nueva York con transporte incluido y actividades diarias', 1500.00, false, true, true, 2),
    ('Descubre Tokio', 'Visita Tokio con alojamiento y excursiones', 1800.00, true, false, true, 3);

create table reserva_destino(
	id_reserva_destino int not null auto_increment primary key,
    cantidad_personas int not null,
    fecha_inicio date not null,
    fecha_fin date not null,
    fecha_reservacion date not null,
    estado int not null,
    -- id_usuario int not null,
    id_destino int not null,
    -- foreign key (id_usuario) references usuario(id_usuario),
    foreign key (id_destino) references destino(id_destino)
);

INSERT INTO reserva_destino (cantidad_personas, fecha_inicio, fecha_fin, fecha_reservacion, estado, id_destino)
VALUES
    (2, '2024-07-27', '2024-08-06', '2024-07-27', 1, 1),  -- Reservación para el usuario 1
    (1, '2024-08-07', '2024-08-17', '2024-08-07', 1, 2),  -- Reservación para el usuario 1
    (3, '2024-08-18', '2024-08-28', '2024-08-18', 1, 3),  -- Reservación para el usuario 2
    (2, '2024-08-29', '2024-09-08', '2024-08-29', 1, 4);  -- Reservación para el usuario 3

create table reserva_paquete(
	id_reserva_Destino int not null auto_increment primary key,
    cantidad_personas int not null,
    fecha_inicio date not null,
    fecha_fin date not null,
    fecha_reservacion date not null,
    estado int not null,
    -- id_usuario int not null,
    id_paquete int not null,
    -- foreign key (id_usuario) references usuario(id_usuario),
    foreign key (id_paquete) references paquete(id_paquete)
);

INSERT INTO reserva_paquete (cantidad_personas, fecha_inicio, fecha_fin, fecha_reservacion, estado, id_paquete)
VALUES
    (2, '2024-07-27', '2024-08-06', '2024-07-27', 1, 1),  -- Reservación para el usuario 1
    (1, '2024-08-07', '2024-08-17', '2024-08-07', 1, 1),  -- Reservación para el usuario 1
    (3, '2024-08-18', '2024-08-28', '2024-08-18', 1, 2),  -- Reservación para el usuario 2
    (2, '2024-08-29', '2024-09-08', '2024-08-29', 1, 3);  -- Reservación para el usuario 3

create table rol(
	id_rol int not null auto_increment primary key,
	nombre varchar(20) not null,
	id_usuario int not null,
	foreign key (id_usuario) references usuario(id_usuario)
);

insert into rol (nombre, id_usuario) values ("ADMIN", 1), ("VENDEDOR", 1), ("USER", 1), ("VENDEDOR", 2), ("USER", 2), ("USER", 3);