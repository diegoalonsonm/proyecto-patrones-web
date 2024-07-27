# Proyecto Patrones Web II Cuatri

## Integrantes
- Diego Naranjo

## Descripción
Creacion de una aplicacion web utilizando Java, Springboot, Bootstrap, SQL, entre otras tecnologias.
La aplicacion consiste en un sistema de gestion para una agencia de vuelos donde se puesden hacer reservas, manejar viajes, usuarios, destinos, etc.

### Cosas importantes
- Gracias a la integracion de Firebase, cada que se hace un push se va a "borrar" la base de datos y se va a volver a cargar con los datos del archivo `.json` con la configuracion del firebase. Esta en la carpeta `src/main/resources/firebase/`. Por lo que cada vez que hagan un git pull a su computadora, van a tener que hacer un nuevo archivo desde la consola de firebase, una nueva carpeta llamada `firebase`, guardar el archivo `.json` ahi y reemplazarlo con los datos en la clase `FirebaseStorageService`.