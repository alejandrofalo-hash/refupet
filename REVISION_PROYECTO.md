# StreamFlix - Revisión Integral del Proyecto

> **Fecha de revisión:** 29/10/2026
> **Archivo de plan:** `C:/Users/mahc8/Downloads/StreamFlix_FullStack_Trello.xlsx`
> **Repositorio:** `D:/streamflix-app-master/streamflix-app-master`

---

## 1. RESUMEN EJECUTIVO

StreamFlix es una aplicación FullStack de catálogo de streaming construida con arquitectura limpia (Clean Architecture) usando Quarkus, Java 21, MySQL 8 y un frontend vanilla (HTML/CSS/JS).

**Estado general:** ⚠️ **En desarrollo parcial** - Solo el Sprint 1 está parcialmente implementado con múltiples bugs críticos.

---

## 2. ARQUITECTURA DEL PROYECTO

### 2.1 Módulos Maven

```
streamflix (pom.xml - padre)
├── streamflix-domain         ← Capa de dominio (entidades, reglas de negocio)
├── streamflix-application    ← Capa de aplicación (casos de uso, DTOs)
├── streamflix-infrastructure ← Capa de infraestructura (JPA, repositorios)
└── streamflix-api            ← Capa de presentación (REST, JAX-RS)

---

## 3. PLAN DE SPRINTS (Desde Excel)

### 3.1 Resumen por Sprint

| Sprint | Semanas | HU | Backend | Frontend | BD | Test | Total | Pts |
|--------|---------|-----|---------|----------|-----|------|-------|-----|
| Sprint 1 | 1-4 | 6 | 11 | 8 | 7 | 9 | 35 | 39 |
| Sprint 2 | 5-8 | 7 | 9 | 7 | 6 | 8 | 30 | 36 |
| Sprint 3 | 9-12 | 3 | 6 | 3 | 3 | 6 | 18 | 24 |
| Sprint 4 | 13-18 | 3 | 6 | 2 | 2 | 6 | 16 | 31 |
| **TOTAL** | **4 Sprints** | **19** | **32** | **20** | **18** | **29** | **99** | **130** |

### 3.2 Historias de Usuario Detalladas

#### Sprint 1 - Catálogo Público · Registro · Login (Semanas 1-4)

| ID | Épica | Rol | Historia | Pts |
|----|-------|-----|----------|-----|
| HU-01 | Catálogo Público | 👤 Visitante | Ver catálogo de contenidos sin registrarme | 8 |
| HU-02 | Registro | 👤 Visitante | Registrarme como nuevo usuario | 8 |
| HU-03 | Login | 👤 Usuario | Iniciar sesión con credenciales | 8 |
| HU-04 | Autenticación | 👤 Usuario | Mantener sesión con JWT | 5 |
| HU-05 | Seed | 🔴 Admin | Datos iniciales para demo | 5 |
| HU-06 | Frontend Auth | 👤 Visitante | Formularios de login y registro | 5 |

#### Sprint 2 - Perfiles · Reproducción · Watchlist (Semanas 5-8)

| ID | Épica | Rol | Historia | Pts |
|----|-------|-----|----------|-----|
| HU-07 | Perfiles | 🔵 Suscriptor | Crear y eliminar perfiles (máx 5) | 5 |
| HU-08 | Reproducción | 🔵 Suscriptor | Ver historial de reproducción | 8 |
| HU-09 | Watchlist | 🔵 Suscriptor | Agregar/eliminar de watchlist | 8 |
| HU-10 | Calificación | 🔵 Suscriptor | Calificar contenido después del 70% | 8 |
| HU-11 | Frontend Perfiles | 🔵 Suscriptor | Panel de perfiles | 5 |
| HU-12 | Contenido por ID | 👤 Visitante | Ver detalles de un contenido | 5 |
| HU-13 | Progreso | 🔵 Suscriptor | Actualizar progreso de reproducción | 7 |

#### Sprint 3 - Admin Contenido (Semanas 9-12)

| ID | Épica | Rol | Historia | Pts |
|----|-------|-----|----------|-----|
| HU-14 | CRUD Contenido | 🔴 Admin | Crear, leer, actualizar contenido | 8 |
| HU-15 | Estados | 🔴 Admin | Gestionar estados de contenido | 8 |
| HU-19 | Admin Frontend | 🔴 Admin | Panel de administración | 8 |

#### Sprint 4 - Calidad · Despliegue (Semanas 13-18)

| ID | Épica | Rol | Historia | Pts |
|----|-------|-----|----------|-----|
| HU-22 | Experiencia | 🟣 Perfil | Mensajes de error claros | 5 |
| HU-23 | Calidad | 🔵 Suscriptor | Suite de pruebas completa (>80% cobertura) | 13 |
| HU-24 | Despliegue | 🔵 Suscriptor | Docker + CI/CD + Despliegue | 13 |

---

## 4. INVENTARIO DE ARCHIVOS ACTUALES

### 4.1 Dominio (`streamflix-domain`)

| Archivo | Estado | Descripción |
|---------|--------|-------------|
| `Content.java` | ✅ Implementado | Entidad principal con transiciones de estado |
| `ContentId.java` | ✅ Implementado | Value Object con UUID |
| `Genre.java` | ✅ Implementado | Enum con 7 géneros |
| `Status.java` | ✅ Implementado | Enum con máquina de estados |
| `IContentRepository.java` | ✅ Implementado | Interfaz del repositorio |
| `Account.java` | ❌ Vacío | Solo declaración de clase |
| `AccountId.java` | ❌ Vacío | Solo declaración de clase |
| `DomainException.java` | ✅ Implementado | Excepción base del dominio |

### 4.2 Aplicación (`streamflix-application`)

| Archivo | Estado | Descripción |
|---------|--------|-------------|
| `GetCatalogQuery.java` | ⚠️ Con bugs | Caso de uso para obtener catálogo |
| `ContentDTO.java` | ✅ Implementado | Record para respuestas del catálogo |

### 4.3 Infraestructura (`streamflix-infrastructure`)

| Archivo | Estado | Descripción |
|---------|--------|-------------|
| `ContentJpa.java` | ✅ Implementado | Entidad JPA para contenido |
| `ContentRepositoryJpa.java` | ⚠️ Con bugs | Implementación JPA del repositorio |

### 4.4 API (`streamflix-api`)

| Archivo | Estado | Descripción |
|---------|--------|-------------|
| `CatalogResource.java` | ✅ Implementado | Endpoint GET /api/catalog |
| `application.properties` | ⚠️ Inseguro | Configuración con credenciales expuestas |
| `web.xml` | ✅ Vacío | Descriptor web mínimo |
| `beans.xml` | ✅ Implementado | Descriptor CDI |

### 4.5 Configuración

| Archivo | Estado |
|---------|--------|
| `pom.xml` (padre) | ✅ |
| `pom.xml` (domain) | ✅ |
| `pom.xml` (application) | ✅ |
| `pom.xml` (infrastructure) | ✅ |
| `pom.xml` (api) | ✅ |

---

## 5. ANÁLISIS DE CÓDIGO - BUGS Y PROBLEMAS

### 🔴 Críticos

#### BUG-01: Estado inicial incorrecto en Content.java (Línea 30)
```java
// ACTUAL (incorrecto):
this.status = Status.EN_REVISION;

// ESPERADO según HU-01 y HU-14:
this.status = Status.BORRADOR;
```
**Impacto:** Todo nuevo contenido se crea directamente en EN_REVISION, saltando el estado BORRADOR. Esto rompe la máquina de estados definida en Status.java y las reglas de negocio de HU-15.

#### BUG-02: ContentRepositoryJpa.buscarId() retorna siempre vacío (Línea 36-40)
```java
@Override
public Optional<Content> buscarId(ContentId contentId) {
    entityManager.createQuery(
        "SELECT cj.title,cj.description,cj.genre FROM ContentJpa cj WHERE cj.contentId = :id", 
        ContentJpa.class
    ).setParameter("id",contentId.value());
    return Optional.empty(); // ← Siempre retorna vacío
}
```
**Problemas:**
1. La query solo selecciona 3 campos pero intenta mapear a ContentJpa completo
2. No se llama a `getSingleResult()`
3. Siempre retorna `Optional.empty()`

#### BUG-03: ContentRepositoryJpa.borrar() no implementado (Línea 52-54)
```java
@Override
public int borrar(ContentId contentId) {
    return 0; // ← No hace nada
}
```

#### BUG-04: GetCatalogQuery no filtra por estado PUBLICADO (Línea 31-35)
```java
public List<ContentDTO> execute(String genreRaw, String queryText) {
    Genre genre = Genre.fromString(genreRaw);
    return repository
        .buscar() // ← Retorna TODO, no solo PUBLICADO
        .stream()
        .map(ContentDTO::from)
        .toList();
}
```
**Impacto:** Según HU-01, solo se deben mostrar contenidos PUBLICADOS al visitante.

#### BUG-05: GetCatalogQuery ignora parámetros de filtro
Los parámetros `genreRaw` y `queryText` se reciben pero nunca se usan para filtrar.

### 🟡 Medios

#### BUG-06: Content.archivar() sin validación (Línea 83-85)
```java
public void archivar() {
    this.status = Status.ARCHIVADO; // ← Sin verificar estado actual
}
```
Según Status.java, solo PUBLICADO puede pasar a ARCHIVADO.

#### BUG-07: IContentRepository falta método findAllPublished()
Según HU-01 (T-01-BE-2), el repositorio debe tener un método específico `findAllPublished()`.

#### BUG-08: Credenciales hardcodeadas en application.properties
```properties
quarkus.datasource.username=test
quarkus.datasource.password=Curso20251
quarkus.datasource.jdbc.url=jdbc:mysql://54.221.17.1:3306/2026-2
```
Contradice HU-24 que exige variables de entorno.

### 🟢 Bajos

#### BUG-09: Dependencia de Quarkus en streamflix-domain
El módulo domain importa `quarkus-arc`, violando el principio de Clean Architecture de que el dominio debe ser puro.

#### BUG-10: Account.java y AccountId.java vacíos
Las clases existen pero no tienen implementación, bloqueando HU-02 a HU-06.

---

## 6. ANÁLISIS DE CUMPLIMIENTO POR SPRINT

### Sprint 1 - Estado: ⚠️ 30% Completo

| HU | Estado | Tareas completadas | Tareas totales |
|----|--------|-------------------|----------------|
| HU-01 | ⚠️ Parcial | 3/9 | 9 |
| HU-02 | ❌ No iniciado | 0/7 | 7 |
| HU-03 | ❌ No iniciado | 0/7 | 7 |
| HU-04 | ❌ No iniciado | 0/5 | 5 |
| HU-05 | ❌ No iniciado | 0/5 | 5 |
| HU-06 | ❌ No iniciado | 0/5 | 5 |

**Detalle HU-01 (Catálogo Público):**
- ✅ T-01-BE-1: Entidad Content creada
- ✅ T-01-BE-2: ContentRepositoryJpa existe (con bugs)
- ✅ T-01-BE-3: Endpoint GET /api/catalog existe
- ❌ T-01-FE-1: index.html no existe
- ❌ T-01-FE-2: api.js no existe
- ❌ T-01-DB-1: Script SQL no existe
- ❌ T-01-TS-1: Tests no existen

### Sprint 2-4 - Estado: ❌ 0% Completo

Ninguna tarea de estos sprints ha sido iniciada.

---

## 7. DISEÑO DE BASE DE DATOS (Según Excel)

### Tabla: contents
```sql
CREATE TABLE contents (
  content_id CHAR(36) PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  genre VARCHAR(50) NOT NULL,
  duration_seconds INT NOT NULL,
  release_year SMALLINT NOT NULL,
  status ENUM('BORRADOR','EN_REVISION','PUBLICADO','ARCHIVADO') NOT NULL DEFAULT 'BORRADOR',
  average_rating DECIMAL(3,2) DEFAULT 0.00,
  created_at DATETIME NOT NULL,
  updated_at DATETIME
);
```

### Diferencias con ContentJpa.java actual:

| Campo | Excel (esperado) | Código actual | Diferencia |
|-------|-----------------|---------------|------------|
| duration | `duration_seconds INT` | `duration INT` | Nombre diferente |
| release_year | `release_year SMALLINT` | `time LocalDateTime` | Tipo diferente |
| created_at | `DATETIME NOT NULL` | No existe | Falta |
| updated_at | `DATETIME` | No existe | Falta |
| archived_at | `DATETIME NULL` (Sprint 3) | No existe | Futuro |

---

## 8. MÁQUINA DE ESTADOS DEL CONTENIDO

### Según Status.java (implementado):
```
BORRADOR ──→ EN_REVISION ──→ PUBLICADO ──→ ARCHIVADO
    │              │               │
    └──────────────┴───────────────┘ (→ ARCHIVADO)
```

### Según Excel (HU-15):
```
BORRADOR ──submit()──→ EN_REVISION ──publish()──→ PUBLICADO ──archive()──→ ARCHIVADO
    │                                              │
    └──────────────────archive()───────────────────┘
```

### Inconsistencias:
1. Status.java permite BORRADOR → ARCHIVADO directamente
2. Content.java no tiene métodos `submit()` ni `publish()`, usa `pasarARevision()` y `publicarContenido()`
3. El método `archivar()` en Content.java no valida la transición

---

## 9. ENDPOINTS ACTUALES

| Método | Path | Estado | Descripción |
|--------|------|--------|-------------|
| GET | /api/catalog | ✅ Implementado | Retorna catálogo (sin filtrar) |
| GET | /api/catalog?genre=X | ❌ No funciona | Parámetro ignorado |
| GET | /api/catalog?q=X | ❌ No funciona | Parámetro ignorado |
| POST | /auth/register | ❌ No existe | HU-02 |
| POST | /auth/login | ❌ No existe | HU-03 |
| GET | /api/content/{id} | ❌ No existe | HU-12 |
| GET | /api/health | ❌ No existe | HU-24 |

---

## 10. RECOMENDACIONES DE ORDEN DE TRABAJO

### Fase 1: Corrección de bugs críticos (Prioridad inmediata)
1. Cambiar estado inicial de Content a BORRADOR
2. Implementar ContentRepositoryJpa.buscarId() correctamente
3. Implementar ContentRepositoryJpa.borrar()
4. Filtrar solo PUBLICADO en GetCatalogQuery
5. Agregar método findAllPublished() a IContentRepository
6. Validar transición en Content.archivar()

### Fase 2: Completar Sprint 1
7. Implementar Account y AccountId
8. Crear IAccountRepository
9. Implementar AccountRepositoryJpa
10. Crear endpoints de auth
11. Implementar JWT filter
12. Crear frontend (index.html, login.html, api.js)
13. Crear script SQL de seed

### Fase 3: Preparar Sprint 2
14. Implementar Profile entity
15. Implementar Watchlist
16. Implementar PlaybackHistory

### Fase 4: Sprint 3
17. Implementar CRUD admin de contenido
18. Panel admin frontend

### Fase 5: Sprint 4
19. ExceptionMapper global
20. Suite de tests
21. Docker + CI/CD

---

## 11. ARCHIVOS FALTANTES CRÍTICOS

### Frontend (Sprint 1)
- ❌ `refupet-api` - Página principal con catálogo
- ❌ `refupet-api` - Formulario de login
- ❌ `refupet-api` - Formulario de registro
- ❌ `refupet-api` - Cliente API con Fetch
- ❌ `refupet-api` - Estilos

### Backend (Sprint 1)
- ❌ `refupet-domain` (vacío)
- ❌ `refupet-domain` (vacío)
- ❌ `refupet-domain`
- ❌ `refupet-domain` (enum)
- ❌ `refupet-domain` (enum)
- ❌ `refupet-application`
- ❌ `refupet-application`
- ❌ `refupet-infrastructure`
- ❌ `refupet-infrastructure`
- ❌ `refupet-api`
- ❌ `refupet-api`

### Base de datos
- ❌ Script SQL para crear tablas
- ❌ Script SQL para datos de seed

### Configuración
- ❌ `.env.example`
- ❌ `Dockerfile`
- ❌ `docker-compose.yml`
- ❌ `.github/workflows/ci.yml`

### Tests
- ❌ Tests unitarios de dominio
- ❌ Tests de integración de API
- ❌ Fake repositories para tests

---

## 12. APUNTES ADICIONALES

### Convenciones del proyecto
- **Paquete base:** `co.ufps.streamflix`
- **Arquitectura:** Clean Architecture con 4 módulos Maven
- **Framework:** Quarkus 3.8.2 con Java 21
- **Patrón de repositorio:** Interfaz en domain, implementación en infrastructure
- **Value Objects:** Clases finales con constructor privado y factory methods
- **Naming:** camelCase para código, snake_case para base de datos

### Notas del Excel sobre Trello
- Listas: Backlog → En Progreso → En Revisión → Hecho
- Etiquetas por capa: Backend (azul), Frontend (morado), BD (verde), Test (naranja)
- Flujo: Backlog → En Progreso (asignado) → En Revisión (terminado) → Hecho (aprobado)

---

*Documento generado automáticamente el 29/10/2026*
### 2.2 Stack Tecnológico

| Capa | Tecnología | Versión |
|------|-----------|---------|
| Framework | Quarkus | 3.8.2 |
| Lenguaje | Java | 21 |
| Build | Maven | - |
| ORM | Hibernate ORM (Quarkus) | - |
| Base de datos | MySQL | 8.0 |
| API | JAX-RS (RESTEasy Reactive) | - |
| JSON | Jackson | - |
| Frontend | HTML/CSS/JS vanilla | - |

### 2.3 Dependencias entre módulos

```
streamflix-api
    ├── streamflix-application
    │       └── streamflix-domain
    └── streamflix-infrastructure
            └── streamflix-domain
```
