# CountryVote - Sistema de Votación de Países

CountryVote es una aplicación web moderna que permite a los usuarios votar por sus países favoritos y ver un ranking en tiempo real de los votos recibidos. El proyecto está dividido en una arquitectura de microservicios con un frontend desarrollado en Angular y un backend escalable en Spring Boot.

## 📋 Descripción General

CountryVote proporciona:
- **Votación interactiva** de países con validación de datos
- **Ranking dinámico** actualizado en tiempo real
- **API REST** robusta y escalable con Swagger/OpenAPI
- **Interfaz intuitiva y responsiva** con Material Design
- **Arquitectura modular** y mantenible
- **Base de datos H2** para persistencia de votos

## 📁 Estructura del Proyecto

```
CountryVote/
├── front-country-voting/          # Frontend (Angular 18)
│   ├── src/
│   ├── package.json
│   ├── angular.json
│   └── README.md                  # Documentación del frontend
├── back-country-voting/           # Backend (Spring Boot 4.0.2)
│   ├── src/
│   ├── pom.xml
│   └── README.md                  # Documentación del backend
└── README.md                       # Este archivo
```

## 🎯 Componentes Principales

### Frontend - FrontCountryVoting (Angular 18)

Aplicación Angular que implementa:
- Formulario de votación con validación avanzada
- Tabla de ranking con ordenamiento y filtrado
- Integración HTTP con el backend
- Diseño responsivo con Material Design

**Para más detalles ver**: [front-country-voting/README.md](./front-country-voting/README.md)

### Backend - BackCountryVoting (Spring Boot 4.0.2)

Sistema API REST que gestiona:
- Persistencia de votos en base de datos H2
- Cálculo y ranking de países votados
- Endpoints RESTful para votación
- Documentación automática con Swagger/OpenAPI

**Para más detalles ver**: [back-country-voting/README.md](./back-country-voting/README.md)

## 🚀 Inicio Rápido

### Requisitos Previos

- **Node.js** v18+ (para el frontend)
- **Java** 21+ (para el backend)
- **Maven** 3.8+ (para compilar el backend)
- **npm** o **yarn** (para el frontend)

### Instalación y Ejecución

#### Frontend

```bash
cd front-country-voting
npm install
npm start
```

La aplicación estará disponible en `http://localhost:4200`

#### Backend

```bash
cd back-country-voting
mvn clean install
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console`

## 🔗 Enlaces Principales

| Recurso | URL |
|---------|-----|
| Frontend | http://localhost:4200 |
| Backend API | http://localhost:8080/api/v1 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| H2 Database Console | http://localhost:8080/h2-console |

## 📚 Endpoints Principales

### Votación
- `POST /api/v1/votes` - Registrar un nuevo voto
- `GET /api/v1/votes/ranking` - Obtener ranking de países votados

## 🏗️ Arquitectura

El proyecto sigue una arquitectura de **tres capas**:

1. **Frontend (Angular)** - Interfaz de usuario responsiva
2. **Backend (Spring Boot)** - Lógica de negocio y persistencia
3. **Base de Datos (H2)** - Almacenamiento de datos

## 🛠️ Tecnologías Utilizadas

### Frontend
- Angular 18
- Material Design
- RxJS
- TypeScript
- SCSS

### Backend
- Spring Boot 4.0.2
- Spring Data JPA
- Spring Validation
- Lombok
- Springdoc OpenAPI
- H2 Database

## 📝 Licencia

Este proyecto está licenciado bajo MIT License.
- npm v9+
- Git

### Instalación y Ejecución

#### 1. Frontend

```bash
# Clonar o descargar el proyecto
cd CountryVote/front-country-voting

# Instalar dependencias
npm install

# Iniciar servidor de desarrollo
npm start

# Acceder a http://localhost:4200/
```

#### 2. Backend

Consulta la documentación del backend para instrucciones de instalación y ejecución.

## 🏛️ Arquitectura General

```
┌─────────────────────────────────────────────────────────┐
│                    Cliente Web (Angular)                 │
│                                                           │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐   │
│  │ Vote Form    │  │ Countries    │  │   Services   │   │
│  │ Component    │  │   Table      │  │  (RxJS)      │   │
│  └──────────────┘  └──────────────┘  └──────────────┘   │
└────────────────────────┬────────────────────────────────┘
                         │
                  HTTP REST API
                         │
┌────────────────────────┴────────────────────────────────┐
│                   Backend API                            │
│                                                           │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐   │
│  │ Vote         │  │   Ranking    │  │   Countries  │   │
│  │ Controller   │  │   Service    │  │   Service    │   │
│  └──────────────┘  └──────────────┘  └──────────────┘   │
└────────────────────────┬────────────────────────────────┘
                         │
                    Database
                         │
┌────────────────────────┴────────────────────────────────┐
│                     Persistencia                         │
└─────────────────────────────────────────────────────────┘
```

## 🛠️ Tecnologías Utilizadas

### Frontend
- **Angular 18**: Framework principal
- **Angular Material**: Componentes UI profesionales
- **Bootstrap 5**: Utilidades CSS
- **TypeScript**: Tipado fuerte
- **RxJS 7**: Programación reactiva
- **SCSS**: Preprocesador CSS

### General
- **Node.js/npm**: Gestión de dependencias
- **Git**: Control de versiones

## 📚 Documentación

- **Frontend**: [front-country-voting/README.md](./front-country-voting/README.md)
  - Estructura detallada del proyecto
  - Arquitectura de componentes y servicios
  - Guía completa de instalación y ejecución

## 🔄 Flujo de Trabajo

1. **Usuario ingresa datos** en el formulario de votación
2. **Validación** de email y datos requeridos
3. **Envío al backend** mediante HTTP POST
4. **Backend procesa** el voto
5. **Frontend recibe notificación** de éxito
6. **Tabla se actualiza** automáticamente en tiempo real
7. **Usuario ve ranking actualizado**

## ⚙️ Configuración

### Variables de Entorno

Las URLs del backend se configuran en:
- **Desarrollo**: `front-country-voting/src/environments/environment.ts`
- **Producción**: `front-country-voting/src/environments/environment.prod.ts`

## 🔌 API Endpoints

### Votación

```
POST /api/v1/votes
Content-Type: application/json

{
  "username": "John Doe",
  "email": "john@example.com",
  "country": "Colombia",
  "region": "Americas",
  "subRegion": "South America",
  "capital": "Bogotá"
}

Response: 200 OK - "Vote submitted successfully"
```

### Ranking

```
GET /api/v1/votes/ranking

Response: 200 OK
{
  "content": [
    {
      "country": "Colombia",
      "capital": "Bogotá",
      "region": "Americas",
      "subregion": "South America",
      "votes": 5
    },
    ...
  ]
}
```

## 📊 Base de Datos

### Modelo de Datos

```
Vote
├── id (UUID)
├── username (String)
├── email (String)
├── country (String)
├── region (String)
├── subRegion (String)
├── capital (String)
└── timestamp (DateTime)
```

## 🧪 Testing

### Frontend

```bash
cd front-country-voting
npm test
```

### Backend

Consulta la documentación del backend.

## 📈 Mejoras Futuras

- [ ] Autenticación de usuarios
- [ ] Límite de votos por IP
- [ ] Análisis y estadísticas avanzadas
- [ ] Notificaciones en tiempo real (WebSockets)
- [ ] Caché y optimización de rendimiento
- [ ] Internacionalización (i18n)
- [ ] Temas oscuros/claros

## 🔒 Seguridad

- Validación de datos en cliente y servidor
- Sanitización de inputs
- Protección contra CSRF
- CORS configurado apropiadamente
- HTTPS en producción

## 📄 Licencia

Este proyecto es propiedad de LoopStudio.

## 👥 Equipo de Desarrollo

- **Frontend**: Equipo Angular
- **Backend**: Equipo API

## 📞 Soporte

Para reportar bugs o solicitar características, contacta al equipo de desarrollo.

---

**Última actualización**: Febrero 2026

**Estado**: En desarrollo activo
