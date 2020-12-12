## mastercloudapps-spring-rest-api-with-db
Practica numero 2 de la asignatura `Modulo II - 1. Tecnologías de servicios de internet`

Se desean realizar los siguientes cambios a la práctica de libros y comentarios:
- Añadir persistencia a la aplicación con Spring Data y MySQL
- Quitar la interfaz web:
  - Eliminar ficheros estáticos y templates y su dependencia del pom.xml
  - Eliminar el controlador web
  - Eliminar el resto de recursos de la web
- Los usuarios que crean comentarios pasan a ser una entidad propia (User) con las propiedades nick y email. El nick deberá ser único.
- Nuevas operaciones de la API REST:
  - Se debe añadir un endpoint para gestionar usuarios (creación, actualización
del email y consulta). Sólo se podrán borrar usuarios sin comentarios.
  - Se deberá añadir un endpoint de consulta de todos los comentarios de un
usuario concreto. En este caso, cada comentario deberá contener el id del
Libro que comentan.
- Cambios en las operaciones REST ya existentes:
  - Al consultar un Libro, se devolverán todos sus comentarios. Los comentarios en este caso deberán traer el texto, el nick y el email del Usuario.
  - Al crear un Comentario, se deberá proporcionar el nick del usuario.
- Documentación
  - Se deberá actualizar la colección Postman con ejemplos de acceso a la API.
  - Se deberá actualizar la documentación OpenAPI a las nuevas operaciones
de la API REST.
