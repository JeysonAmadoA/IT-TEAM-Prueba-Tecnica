# IT-TEAM-Prueba-Tecnica

## Frontend - Pixasearch

Este proyecto consiste en un aplicativo web diseñado para la búsqueda de imágenes, utilizando la API de Pixabay. Permite a los usuarios buscar imágenes específicas y explorar categorías para encontrar una amplia variedad de contenido visual.

### Características principales

- **Búsqueda por palabras clave:** Los usuarios pueden ingresar términos de búsqueda específicos para encontrar imágenes relacionadas.

- **Exploración por categorías:** El aplicativo ofrece la opción de explorar imágenes por categorías predefinidas, facilitando la búsqueda de contenido específico.

### Tecnologías utilizadas

- **Frontend:** El frontend de la aplicación está desarrollado utilizando tecnologías web estándar, como HTML, CSS, JavaScript, Bootstrap y Angular.

- **API de Pixabay:** La aplicación utiliza la API de Pixabay para obtener resultados de búsqueda de imágenes en tiempo real.

### Requisitos previos

- **Node.js**

- **Angular CLI**

### Configuración del Proyecto

Una vez clonado el proyecto se debe acceder al directorio Frontend y una vez allí se debe ejecutar los siguientes comandos.

```bash
npm install
```

```bash
ng serve
   ```

A partir de esos comandos ya se podrá acceder al aplicativo Web en el puerto localhost:4200 por defecto para proyectos Angular

## Backend - API REST para la Administración de Usuarios

Este proyecto consiste en una API REST que proporciona servicios CRUD para la administración de usuarios. Utiliza tecnologías como Spring Boot, Java, Postgres, Liquidbase y Docker.

### Funcionalidades principales

- **Operaciones CRUD:** La API permite realizar las operaciones básicas de Crear, Leer, Actualizar y Eliminar usuarios.
- **Autenticación:** Usa Spring Security para el proceso de autenticación de los usuarios registrados.

### Tecnologías utilizadas

- **Backend:** El backend de la aplicación está desarrollado utilizando Spring Boot 3.0.6 y Java 17.

- **Base de datos:** Se utiliza PostgreSQL para almacenar y gestionar los datos de los usuarios.

- **Migraciones:** Se utiliza Liquidbase para generar migraciones a la base de datos

- **Contenedor Docker:** Se utilizó docker para crear una instancia de la base de datos.

### Requisitos previos

Asegúrate de tener instalados los siguientes componentes antes de ejecutar la aplicación:

- **Java 17:**
  [Descargar Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)

- **Docker:**
  [Descargar Docker](https://www.docker.com)

### Configuración del Proyecto


1. **Crear archivo docker-compose.override.yml:** En el directorio .devops/docker/develop se debe crear un archivo docker-compose.override.yml tomando como referencia el archivo de ejemplo. En ese mismo directorio se puede editar las credenciales de la base de datos en caso de que se requiera en el archivo docker-compose.yml.

2. **Iniciar instacia de Base de datos con Docker:**  Para inicializar el contenedor de la base de datos se debe hacer con el siguiente comando ubicado en el directorio raíz del proyecto.
    ```bash
      docker-compose -f .devops/docker/develop/docker-compose.yml -f .devops/docker/develop/docker-compose.override.yml up
    ```

2. **Ejecutar migraciones:**  Al ejecutar la migraciones va a generar todo el esquema de la base de datos, así como unos usuarios de prueba para utilizar en el proyecto. Se debe ejecutar el siguiente comando:

**Windows:**

```bash
  docker run -it --workdir="/project" --rm -v ${PWD}:/project --network=develop_java-pixasearch liquibase/liquibase --defaultsFile=db-migrations/liquidbase.properties --changelog-file=db-migrations/changelog.xml update
```

**Linux:**

```bash
  docker run -it --workdir="/project" --rm -v $PWD:/project --network=develop_uptc-register liquibase/liquibase --defaultsFile=db-migrations/liquidbase.properties --changelog-file=db-migrations/changelog.xml update
```


3. **Configurar archivo application.properties:** Se debe configurar este archivo para establecer el puerto de ejecución y las credenciales de la base de datos.
4. **Ejecución de proyecto:** Una vez realizada las configuraciones anteriores ya se podrá poner en ejecución la API REST.

### Endpoints de la API

Al hacer click [aquí](https://documenter.getpostman.com/view/24997642/2s9YXmWzsL) puede acceder a la documentación de la API
