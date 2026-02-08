# FrontCountryVoting

Aplicación frontend desarrollada con **Angular 18** para un sistema de votación de países. Esta aplicación permite a los usuarios votar por sus países favoritos y ver un ranking en tiempo real de los votos recibidos.

## 📋 Descripción General

FrontCountryVoting es una aplicación web moderna que implementa:
- **Formulario de votación interactivo** con validación de datos
- **Tabla de ranking dinámico** que se actualiza en tiempo real
- **Interfaz intuitiva** usando Angular Material
- **Comunicación bidireccional** con el backend mediante observables RxJS
- **Diseño responsivo** basado en Bootstrap

## 🏗️ Estructura del Proyecto

```
front-country-voting/
├── src/
│   ├── app/
│   │   ├── core/
│   │   │   ├── models/
│   │   │   │   └── country.model.ts          # Modelos de datos
│   │   │   └── services/
│   │   │       ├── country.service.ts        # Servicio de países
│   │   │       └── voting.service.ts         # Servicio de votación
│   │   ├── features/
│   │   │   └── voting/
│   │   │       ├── voting.page.ts            # Página principal de votación
│   │   │       ├── countries-table/          # Componente tabla de países
│   │   │       │   ├── countries-table.component.ts
│   │   │       │   ├── countries-table.component.html
│   │   │       │   └── countries-table.component.scss
│   │   │       └── vote-form/                # Componente formulario de voto
│   │   │           ├── vote-form.component.ts
│   │   │           ├── vote-form.component.html
│   │   │           └── vote-form.component.scss
│   │   ├── layout/
│   │   │   └── main-layout/                  # Layout principal
│   │   │       └── main-layout.component.*
│   │   ├── shared/
│   │   │   ├── components/
│   │   │   │   └── header/                   # Componente de encabezado
│   │   │   └── material/
│   │   │       └── material-icons.provider.ts
│   │   ├── app.routes.ts                     # Rutas de la aplicación
│   │   └── app.component.*                   # Componente raíz
│   ├── environments/
│   │   ├── environment.ts                    # Configuración desarrollo
│   │   └── environment.prod.ts               # Configuración producción
│   ├── styles.scss                           # Estilos globales
│   └── main.ts                               # Punto de entrada
├── angular.json                              # Configuración Angular CLI
├── package.json                              # Dependencias del proyecto
└── README.md                                 # Este archivo
```

## 🏛️ Arquitectura

### Componentes Principales

1. **VoteFormComponent**: Formulario para enviar votos
   - Validación de email y nombre
   - Selección de país desde dropdown
   - Notificación de éxito/error

2. **CountriesTableComponent**: Tabla de ranking
   - Visualización de votos por país
   - Ordenamiento y filtrado
   - Actualización en tiempo real

3. **MainLayoutComponent**: Layout general de la aplicación

### Servicios

- **VotingService**: Gestiona votos y notificaciones
  - `submitVote()`: Envía un voto al backend
  - `getVoteRanking()`: Obtiene el ranking actual
  - `voteSubmitted$`: Observable para notificar votos exitosos

- **CountryService**: Gestiona datos de países
  - `getCountries()`: Obtiene lista de países

### Flujo de Datos

```
VoteFormComponent
       ↓
   (submit)
       ↓
VotingService.submitVote()
       ↓
Backend API
       ↓
VotingService.notifyVoteSubmitted()
       ↓
CountriesTableComponent.voteSubmitted$
       ↓
CountriesTableComponent.loadVoteRanking()
       ↓
Tabla actualizada
```

## 🛠️ Tecnologías

- **Angular 18**: Framework principal
- **Angular Material**: Componentes UI
- **Bootstrap**: Utilidades CSS
- **RxJS**: Manejo de observables
- **TypeScript**: Lenguaje de programación
- **SCSS**: Preprocesador CSS
- **Google Fonts**: Fuente Inter

## 📦 Dependencias Principales

```json
{
  "@angular/core": "^18.2.0",
  "@angular/material": "^18.2.0",
  "bootstrap": "^5.3.0",
  "rxjs": "^7.8.0"
}
```

## 🚀 Cómo Correr la Aplicación

### Requisitos Previos

- Node.js (v18 o superior)
- npm (v9 o superior)
- Angular CLI instalado globalmente (opcional)

### Instalación

1. **Navegar a la carpeta del proyecto**
   ```bash
   cd front-country-voting
   ```

2. **Instalar dependencias**
   ```bash
   npm install
   ```

### Desarrollo

1. **Iniciar servidor de desarrollo**
   ```bash
   npm start
   ```
   O con Angular CLI:
   ```bash
   ng serve
   ```

2. **Acceder a la aplicación**
   - Abre tu navegador en: `http://localhost:4200/`
   - La aplicación se recargará automáticamente si realizas cambios

### Build

1. **Compilar para producción**
   ```bash
   npm run build
   ```
   O:
   ```bash
   ng build
   ```

2. **Artefactos generados**
   Los archivos compilados se guardarán en la carpeta `dist/`

### Testing

1. **Ejecutar pruebas unitarias**
   ```bash
   npm test
   ```
   O:
   ```bash
   ng test
   ```

## ⚙️ Configuración

### Variables de Entorno

Las URLs del backend se configuran en `src/environments/`:

**environment.ts** (Desarrollo):
```typescript
export const environment = {
  backUrl: 'http://localhost:8080'
};
```

**environment.prod.ts** (Producción):
```typescript
export const environment = {
  backUrl: 'https://api.production.com'
};
```

### Estilos Globales

Los estilos globales se definen en `src/styles.scss`:
- Fuente: Inter (Google Fonts)
- Color de fondo: #f8f8f8
- Bootstrap integrado

## 🎨 Características de Diseño

- **Responsive**: Adaptable a todos los tamaños de pantalla
- **Material Design**: Siguiendo los principios de Material Design
- **Animaciones suaves**: Transiciones y feedback visual
- **Accesibilidad**: Componentes accesibles con ARIA

## 📝 Scripts Disponibles

| Script | Descripción |
|--------|-------------|
| `npm start` | Inicia servidor de desarrollo |
| `npm run build` | Compila para producción |
| `npm test` | Ejecuta pruebas unitarias |
| `npm run lint` | Ejecuta linter |

## 🔗 Integración Backend

La aplicación se comunica con un backend que debe proporcionar:

### Endpoints Requeridos

- `POST /api/v1/votes`: Enviar un voto
- `GET /api/v1/votes/ranking`: Obtener ranking de votos

## 📄 Licencia

Este proyecto es parte de CountryVote - Sistema de Votación de Países.

## 👥 Contribuciones

Para contribuir al proyecto, contacta al equipo de desarrollo.

---

**Última actualización**: Febrero 2026

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
