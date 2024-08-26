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
    password varchar(255) not null,
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
    tiempo_viaje double not null,
    precio_persona float not null,
    url_imagen varchar(1024)
);

insert into destino (pais, ciudad, tiempo_viaje, precio_persona, url_imagen)
values
    ('Francia', 'París', 10, 300.0, 'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Fparis.jpg?alt=media&token=1658db5c-cc6f-4784-a2df-97ddd5dd518f'),
    ('Estados Unidos', 'New York', 4, 294.68, 'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Fny.jpg?alt=media&token=bf90f7cf-1c03-4381-8688-b7cd390c46d3'),
    ('Inglaterra', 'Londres', 9, 699.24,  'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Flondon.png?alt=media&token=d33c12cc-df3b-403a-bdda-9fd7a62de7e1'),
    ('Japón', 'Tokyo', 14, 540.38, 'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Ftokyo.webp?alt=media&token=5ad99494-4a4c-435b-ba7b-d5a349ff48dc');

insert into destino (pais, ciudad, tiempo_viaje, precio_persona, url_imagen) values ('España', 'Madrid', 10.5, 1000.32, 'https://firebasestorage.googleapis.com/v0/b/proyecto-patrones-b109c.appspot.com/o/assets%2Fmadrid.jpg?alt=media&token=39ead693-6793-40cf-93ac-bd4c8da31b96');

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
    id_usuario int not null,
    id_destino int not null,
    foreign key (id_usuario) references usuario(id_usuario),
    foreign key (id_destino) references destino(id_destino)
);

insert into reserva_destino (cantidad_personas, fecha_inicio, fecha_fin, fecha_reservacion, estado, id_usuario, id_destino)
values
    (2, '2024-09-01', '2024-09-10', '2024-08-01', 1, 1, 1),  -- Usuario 1, Destino 1 (París)
    (4, '2024-10-15', '2024-10-25', '2024-09-15', 1, 2, 2),  -- Usuario 2, Destino 2 (Nueva York)
    (3, '2024-11-05', '2024-11-12', '2024-10-05', 1, 3, 3);  -- Usuario 3, Destino 3, pendiente (Tokio)
    (2, '2024-05-12', '2024-05-20', '2024-08-01', 2, 3, 2),  -- usuario 3, Destino 2, realizada (Nueva York)
    (2, '2024-08-01', '2024-08-17', '2024-08-01', 3, 3, 1),  -- usuario 3, Destino 1, cancelada (París)

create table reserva_paquete(
    id_reserva_paquete int not null auto_increment primary key,
    cantidad_personas int not null,
    fecha_inicio date not null,
    fecha_fin date not null,
    fecha_reservacion date not null,
    estado int not null,
    id_usuario int not null,
    id_paquete int not null,
    foreign key (id_usuario) references usuario(id_usuario),
    foreign key (id_paquete) references paquete(id_paquete)
);

insert into reserva_paquete (cantidad_personas, fecha_inicio, fecha_fin, fecha_reservacion, estado, id_usuario, id_paquete)
values
    (2, '2024-09-01', '2024-09-10', '2024-08-01', 1, 1, 1),  -- Usuario 1, Paquete 1 (Paquete París Romántico)
    (4, '2024-10-15', '2024-10-25', '2024-09-15', 1, 2, 2),  -- Usuario 2, Paquete 2 (Aventura en Nueva York)
    (3, '2024-11-05', '2024-11-12', '2024-10-05', 1, 3, 3);  -- Usuario 3, Paquete 3, pendiente (Descubre Tokio)
    (2, '2024-12-01', '2024-12-10', '2024-11-01', 2, 3, 1),  -- Usuario 3, Paquete 1, relaizada (Paquete París Romántico)
    (4, '2025-01-15', '2025-01-25', '2024-12-15', 3, 3, 2);  -- Usuario 3, Paquete 2, cancelada (Aventura en Nueva York)

create table rol(
    id_rol int not null auto_increment primary key,
    nombre varchar(20) not null,
    id_usuario int not null,
    foreign key (id_usuario) references usuario(id_usuario)
);

insert into rol (nombre, id_usuario) values
("ROLE_ADMIN", 1), ("ROLE_VENDEDOR", 1), ("ROLE_USER", 1),
("ROLE_VENDEDOR", 2), ("ROLE_USER", 2),
("ROLE_USER", 3);

create table hotel(
    id_hotel int not null auto_increment primary key,
    nombre varchar(50) not null,
    estrellas int not null,
    precio_noche float not null,
    id_destino int not null,
    foreign key (id_destino) references destino(id_destino)
);

insert into hotel (nombre, estrellas, precio_noche, id_destino)
values ('Hotel París', 5, 200.0, 1), ('Hotel New York', 4, 150.0, 2), ('Hotel Tokio', 3, 100.0, 3);

create table reserva_hotel(
    id_reserva_hotel int not null auto_increment primary key,
    cantidad_personas int not null,
    fecha_inicio date not null,
    fecha_fin date not null,
    fecha_reservacion date not null,
    estado int not null,
    id_usuario int not null,
    id_hotel int not null,
    foreign key (id_usuario) references usuario(id_usuario),
    foreign key (id_hotel) references hotel(id_hotel)
);

insert into reserva_hotel (cantidad_personas, fecha_inicio, fecha_fin, fecha_reservacion, estado, id_usuario, id_hotel)
values
    (2, '2024-09-01', '2024-09-10', '2024-08-01', 1, 1, 1),  -- Usuario 1, Hotel 1 (Hotel París)
    (4, '2024-10-15', '2024-10-25', '2024-09-15', 1, 2, 2),  -- Usuario 2, Hotel 2 (Hotel New York)
    (3, '2024-11-05', '2024-11-12', '2024-10-05', 1, 3, 3),  -- Usuario 3, Hotel 3, pendiente (Hotel Tokio)
    (2, '2024-05-12', '2024-05-20', '2024-08-01', 2, 3, 2),  -- usuario 3, Hotel 2, realizada (Hotel New York)
    (2, '2024-08-01', '2024-08-17', '2024-08-01', 3, 3, 1);  -- usuario 3, Hotel 1, cancelada (Hotel París)