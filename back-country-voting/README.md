# BackCountryVoting - API REST de Votación por Países

BackCountryVoting es un backend robusto desarrollado con **Spring Boot 4.0.2** que proporciona una API REST para gestionar la votación de países y calcular rankings en tiempo real.

##  Descripción General

Esta API ofrece:
- **Gestión de votos** con validación de datos
- **Ranking dinámico** de países más votados
- **Persistencia de datos** en base de datos H2
- **API REST completa** con documentación automática Swagger
- **Arquitectura modular** con separación de responsabilidades
- **Validación robusta** de datos de entrada

##  Arquitectura

El proyecto sigue una **arquitectura de tres capas**:

`

         Controller Layer                 
   (VoteController - Manejo de requests)  

                   

         Service Layer                   
   (Lógica de negocio y validación)      

                   

        Repository Layer                 
   (Acceso a datos con JPA)              

                   

       Base de Datos (H2)                
   (Persistencia de votos)               

`

##  Estructura del Proyecto

\\\
back-country-voting/
 src/main/java/com/loopstudio/backcountryvoting/
    BackCountryVotingApplication.java    # Clase principal de Spring Boot
    controller/
       VoteController.java              # Endpoints REST
    service/
       VoteService.java                 # Lógica de votación
       RankingService.java              # Cálculo de rankings
    repository/
       VoteRepository.java              # Acceso a datos
    model/
       Vote.java                        # Entidad Vote
    dto/
       VoteRequest.java                 # DTO para crear voto
       RankingResponse.java             # DTO para ranking
    mapper/
       VoteMapper.java                  # Mapeo de entidades
    exception/
       GlobalExceptionHandler.java      # Manejo de excepciones
    config/
        WebConfig.java                   # Configuración de CORS
 src/main/resources/
    application.properties               # Configuración de la app
    static/                              # Archivos estáticos
 pom.xml                                  # Dependencias Maven
 README.md                                # Este archivo
\\\

##  Inicio Rápido

### Requisitos Previos

- **Java 21+**
- **Maven 3.8+**
- **IDE recomendado**: IntelliJ IDEA o Eclipse

### Instalación

1. **Clonar o navegar al repositorio**
   \\\ash
   cd back-country-voting
   \\\

2. **Compilar el proyecto**
   \\\ash
   mvn clean install
   \\\

3. **Ejecutar la aplicación**
   \\\ash
   mvn spring-boot:run
   \\\

   O compilar y ejecutar el JAR:
   \\\ash
   mvn clean package
   java -jar target/back-country-voting-0.0.1-SNAPSHOT.jar
   \\\

### Verificar que el servidor está corriendo

La aplicación estará disponible en: **http://localhost:8080**

##  Endpoints Principales

### 1. Registrar un Voto
\\\http
POST /api/v1/votes
Content-Type: application/json

{
  "username": "Juan Pérez",
  "email": "juan@example.com",
  "country": "Colombia",
  "region": "Americas",
  "subRegion": "South America",
  "capital": "Bogotá"
}
\\\

**Response (200 OK)**:
\\\
Your vote was successfully submitted
\\\

### 2. Obtener Ranking de Votos
\\\http
GET /api/v1/votes/ranking
\\\

**Response (200 OK)**:
\\\json
{
  "content": [
    {
      "country": "Colombia",
      "capital": "Bogotá",
      "region": "Americas",
      "subregion": "South America",
      "votes": 5
    },
    {
      "country": "Peru",
      "capital": "Lima",
      "region": "Americas",
      "subregion": "South America",
      "votes": 3
    }
  ],
  "empty": false,
  "first": true,
  "last": true,
  "number": 0,
  "numberOfElements": 2,
  "size": 10,
  "totalElements": 2,
  "totalPages": 1
}
\\\

##  Documentación Swagger/OpenAPI

Una vez que la aplicación está en ejecución, accede a:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

En Swagger puedes:
- Ver todos los endpoints disponibles
- Ejecutar requests de prueba
- Ver esquemas de entrada y salida

##  Base de Datos H2

### Acceder a la Consola H2

URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

**Credenciales**:
- **JDBC URL**: jdbc:h2:mem:testdb
- **Username**: sa
- **Password**: (dejar en blanco)
- **Driver Class**: org.h2.Driver

### Consultas Útiles

\\\sql
-- Ver todos los votos
SELECT * FROM VOTE;

-- Ranking de países por votos
SELECT COUNTRY, CAPITAL, REGION, SUB_REGION, COUNT(*) as VOTES
FROM VOTE
GROUP BY COUNTRY, CAPITAL, REGION, SUB_REGION
ORDER BY VOTES DESC;

-- Votos por región
SELECT REGION, COUNT(*) as VOTES
FROM VOTE
GROUP BY REGION;
\\\

##  Configuración

### application.properties

\\\properties
# Nombre de la aplicación
spring.application.name=back-country-voting

# Configuración de H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Datasource
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
\\\

### Variables de Entorno (Opcional)

Puedes configurar mediante variables de entorno:

\\\ash
# Linux/Mac
export SERVER_PORT=8080

# Windows PowerShell
\="8080"
\\\

##  Dependencias Principales

| Dependencia | Versión | Descripción |
|------------|---------|------------|
| Spring Boot | 4.0.2 | Framework principal |
| Spring Data JPA | Incluido | Acceso a datos |
| Spring Validation | Incluido | Validación de datos |
| Lombok | Latest | Reducción de boilerplate |
| Springdoc OpenAPI | 2.7.0 | Documentación Swagger |
| H2 Database | Incluido | Base de datos embebida |

##  Testing

Para ejecutar los tests:

\\\ash
mvn test
\\\

##  CORS Configuration

La aplicación está configurada para aceptar requests desde:
- http://localhost:4200 (Frontend Angular)
- http://localhost:3000 (Desarrollo alternativo)

Ver más en: \config/WebConfig.java\

##  Modelos de Datos

### Vote Entity

\\\java
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue
    private Long id;
    
    @NotBlank
    private String username;
    
    @Email
    private String email;
    
    @NotBlank
    private String country;
    
    private String region;
    private String subRegion;
    private String capital;
    
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
\\\

##  Manejo de Errores

La API retorna respuestas de error con estructura:

\\\json
{
  "timestamp": "2024-02-08T12:34:56",
  "status": 400,
  "error": "Bad Request",
  "message": "Email debe ser válido",
  "path": "/api/v1/votes"
}
\\\

##  Performance

- **Base de datos**: H2 (en memoria) - Rápido para desarrollo
- **Conexión pooling**: HikariCP (incluido en Spring Boot)
- **Índices**: Optimizados en VOTE table

##  Troubleshooting

### Puerto 8080 en uso

\\\ash
# Cambiar puerto en application.properties
server.port=8081
\\\

### Limpiar caché Maven

\\\ash
mvn clean -DskipTests
\\\

### Rebuild completo

\\\ash
mvn clean install -U
\\\

##  Contribución

1. Crear una rama feature: \git checkout -b feature/nueva-funcionalidad\
2. Commit cambios: \git commit -m 'Agregar nueva funcionalidad'\
3. Push a la rama: \git push origin feature/nueva-funcionalidad\
4. Abrir Pull Request

##  Licencia

Este proyecto está licenciado bajo MIT License.

##  Soporte

Para reportar issues o sugerencias, contactar al equipo de desarrollo.

---

**Última actualización**: Febrero 2026
