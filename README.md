# Te Lo Llevo

## Autor

- [@rulloag](https://www.github.com/rulloag)

## Descripción

El proyecto de software `Te Lo Llevo` es completo con frontend y backend. El backend está construido con Java, el marco de trabajo Spring Boot y SQL. Por otro lado, el frontend está construido con JavaScript, potenciado con Bootstrap y JQuery.


### Backend

El backend de `Te Lo Presto` está diseñado para administrar todas las operaciones de datos. Utiliza Java y Spring Boot para proporcionar una API RESTful que permite que el frontend interactúe con la base de datos. SQL maneja la base de datos, lo que permite una gestión de datos eficiente y segura.

### Frontend

El frontend de `Te Lo Presto` está diseñado para crear una interfaz de usuario interactiva y fácil de usar. El uso de JavaScript permite una interacción dinámica y una experiencia de usuario atrayente.

###  API pública para integración

El backend de `Te Lo Presto` también proporciona una API pública para integración. Esta API permite que otros sistemas se comuniquen con `Te Lo Presto` para realizar operaciones de datos. 

## Configuración

Antes de comenzar a instalar el proyecto, debemos asegurarnos de que se cumplan los siguientes requisitos:

- Java 17 o más
- Maven 3.6.3 o más reciente
- Base de datos SQL externa (opcional)

Sigue estos pasos para instalar y ejecutar este proyecto:

1. Ejecute el siguiente comando en tu terminal para clonar el repositorio en tu máquina local:
    ```bash
    git clone https://github.com/Rulloag/te-lo-llevo.git
    ```

2. Ir al directorio del proyecto:
    ```bash
    cd te-lo-llevo
    ``` 

3. Ahora que tiene una copia local del proyecto, está preparado para instalar las dependencias necesarias para el front-end y el back-end. Para obtener instrucciones detalladas, consulte las secciones correspondientes de este documento.

### Backend

1. Navegar hasta el directorio del backend: Después de clonar el repositorio, utilice el siguiente comando para llegar al directorio del backend:

     ```bash
     cd backend
     ``` 
2. Instalar las dependencias: Después de entrar en el directorio del backend, utilice el siguiente comando para instalar las dependencias requeridas:

    ```bash
    mvn clean install
    ```

3. Instalar las dependencias: Después de entrar en el directorio del backend, utilice el siguiente comando para instalar las dependencias requeridas:

    ```bash
    mvn spring-boot:run
    ```

#### Configuración de la base de datos

Este proyecto utiliza H2 como sistema de gestión de bases de datos. La configuración de la base de datos se encuentra en el archivo `application.yml` en el directorio `src/main/resources`.

Aquí hay una descripción de las propiedades más importantes:

- `spring.datasource.url`: Esta es la URL de tu base de datos. En este caso, estamos utilizando una base de datos en memoria llamada `tllbackend`.
- `spring.datasource.username` y `spring.datasource.password`: Estas son las credenciales para acceder a tu base de datos. En este caso, el nombre de usuario es `sa` y no hay contraseña.
- `spring.datasource.driver-class-name`: Este es el nombre de la clase del controlador de la base de datos. En este caso, estamos utilizando el controlador H2.
- `spring.jpa.hibernate.ddl-auto`: Esta propiedad controla la generación automática del esquema de la base de datos. En este caso, está configurado para `validate`, lo que significa que Hibernate solo validará que las tablas y columnas existan, no las creará.
- `spring.sql.init.mode`: Esta propiedad controla si se deben ejecutar scripts SQL al inicio. En este caso, está configurado para `always`, lo que significa que siempre se ejecutarán los scripts.
- `spring.sql.init.schema-locations` y `spring.sql.init.data-locations`: Estas propiedades especifican la ubicación de los scripts SQL que se deben ejecutar al inicio. En este caso, los scripts están en el directorio `src/main/resources/sql_imports`.

Para configurar la base de datos:

1. No es necesario instalar H2 en tu máquina. El proyecto Spring Boot ya incluye la dependencia de H2 en el archivo `pom.xml`, por lo que se utilizará una base de datos en memoria.
2. Abre el archivo `application.yml` y revisa las propiedades de la base de datos. Asegúrate de que la URL, el nombre de usuario y la contraseña sean correctos para tu configuración.
3. Si necesitas cambiar alguna propiedad, hazlo directamente en el archivo `application.yml`.
4. Una vez que hayas configurado la base de datos, puedes ejecutar el proyecto. Los scripts SQL especificados en `spring.sql.init.schema-locations` y `spring.sql.init.data-locations` se ejecutarán automáticamente al inicio. Los scripts en sql_imports pueden estar organizados de la siguiente manera:
* `00_SCHEMA.sql`: Este archivo contiene los comandos SQL para crear las tablas y relaciones en la base de datos. Por lo general, incluye comandos `CREATE TABLE`.
* `01_DATA.sql`: Este archivo contiene los comandos SQL para insertar datos iniciales en las tablas. Por lo general, incluye comandos `INSERT INTO`.

## Uso de otro motor de base de datos

Si prefieres utilizar PostgreSQL en lugar de H2, deberás realizar algunos cambios en la configuración de la base de datos:

1. **Dependencia de PostgreSQL**: Asegúrate de que la dependencia de PostgreSQL está presente en tu archivo `pom.xml`. Si no es así, deberás agregarla. Aquí tienes un ejemplo de cómo hacerlo:
   ```xml
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.2.5</version>
    </dependency>
   ```
   
2. **Configuración de la base de datos**: Deberás actualizar las propiedades de la base de datos en el archivo application.yml para que apunten a tu instancia de MySQL. Aquí tienes un ejemplo de cómo podría ser:

    ```yaml
    spring:
        datasource:
        url: jdbc:postgresql://localhost:5432/tllbackend
        username: tu_usuario
        password: tu_contraseña
        driver-class-name: org.postgresql.Driver
    ```
   
3. **Creación de la base de datos**: Deberás crear la base de datos `tllbackend` en tu instancia de PostgreSQL. Puedes hacerlo ejecutando el siguiente comando SQL en tu consola de PostgreSQL:

   ```sql
   CREATE DATABASE tllbackend;
   ```
   
4. **Scripts SQL**: Los scripts SQL que se ejecutan al inicio pueden necesitar modificaciones para ser compatibles con PostgreSQL. Revisa los scripts en el directorio `src/main/resources/sql_imports` y haz los cambios necesarios.

#### APIs disponibles

El backend de `Te Lo Llevo` expone las siguientes APIs:

**Solicitudes de transporte**

* **GET /api/v1/solicitudes**: Esta API devuelve una lista de todas las solicitudes de transporte
* **POST /api/v1/solicitudes**: Esta API crea una nueva solicitud de transporte
* **GET /api/v1/solicitudes/{codigo_seguimiento}**: Esta API devuelve la solicitud de transporte con el `codigo_seguimiento` especificado
* **PATCH /api/v1/solicitudes/{codigo_seguimiento}**: Esta API actualiza la solicitud de transporte con el `codigo_seguimiento` especificado
* **PATCH /api/v1/solicitudes/{codigo_seguimiento}/asignacion**: Esta API asigna un transporte a la solicitud de transporte con el `codigo_seguimiento` especificado
* **GET /api/v1/solicitudes/{codigo_seguimiento}/estado**: Esta API devuelve el estado de la solicitud de transporte con el `codigo_seguimiento` especificado

**Gestión de transporte**
* **GET /api/v1/transportes**: Esta API devuelve una lista de todos los transportes
* **GET /api/v1/transportes/busqueda**: Esta API devuelve los transportes que coinciden con los criterios de búsqueda especificados

Estas APIs tiene una especificación que puede ser accedida desde [la documentación Swagger](http://localhost:8080/swagger-ui/index.html)

### Frontend

El frontend de `Te Lo Llevo` es un proyecto sencillo que utiliza HTML, JavaScript, Bootstrap y jQuery. No es necesario establecer dependencias. Sigue estos pasos para ejecutar el frontend:

1. Navega hasta el directorio del frontend:

    ```bash
    cd repository/frontend
    ```

2. Abre el archivo `telollevo-frontend/paginas/solicitud/lista.html` en el navegador para ver la aplicación.

#### Estructura de Carpetas

El proyecto frontend tiene la siguiente estructura de carpetas:

- `css/`: Esta carpeta contiene todos los archivos CSS para estilos.
- `js/`: Esta carpeta contiene todos los archivos JavaScript para la lógica de la aplicación.
- `paginas/`: Esta carpeta contiene todas las páginas HTML de la aplicación.

```
Para que las funcionalidades del front-end estén disponibles, el back-end debe estar iniciado.
```

### Librerías Externas

Las librerías externas `Bootstrap` y `jQuery` se utilizan en el proyecto. Estas librerías están incluidas en todas las páginas que necesitas, por lo que no necesitas instalarlas.