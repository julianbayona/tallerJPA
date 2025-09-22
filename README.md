# 📚 Sistema de Biblioteca - Spring Boot JPA

## Descripción

Sistema de gestión de biblioteca desarrollado con **Spring Boot 3.5.6** que implementa:

- **JPA/Hibernate** para persistencia de datos
- **API REST** con configuración CORS
- **Base de datos MariaDB** containerizada
- **Interfaz web** con Thymeleaf
- **Docker** para fácil despliegue

## Prerrequisitos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

### Requerimientos Obligatorios:

1. **Docker Desktop** 
   - Descargar desde: https://www.docker.com/products/docker-desktop
   - **IMPORTANTE**: Docker Desktop debe estar **instalado y abierto**
   - Verificar: `docker --version` y `docker-compose --version`

2. **Java 17+** 
   - Verificar: `java --version`

3. **Maven 3.6+** 
   - Verificar: `mvn --version`

### Verificar instalaciones:
```bash
docker --version
docker-compose --version
java --version
mvn --version
```

## Instrucciones de Ejecución

### Pasos para Levantar la Aplicación:

#### **1. Clonar el repositorio** (si es necesario)
```bash
git clone <URL_DEL_REPOSITORIO>
cd tallerJPA
```

#### **2. Construir la imagen Docker**
```bash
docker build -t pee-java-app:1.0.0 .
```

#### **3. Compilar el proyecto Maven**
```bash
mvn clean package -DskipTests
```

#### **4. Levantar todos los servicios**
```bash
docker-compose up -d --build
```

### Tu aplicación estará disponible en:

- **Aplicación Web**: http://localhost:8090
- **API REST**: http://localhost:8090/api/libros
- **Test CORS**: http://localhost:8090/api/libros/test-cors

## Arquitectura de la Aplicación

```
┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend       │
│   (Puerto 8090) │◄──►│   Spring Boot   │
└─────────────────┘    └─────────────────┘
                                │
                                ▼
                       ┌─────────────────┐
                       │   MariaDB       │
                       │   (Puerto 3307) │
                       └─────────────────┘
```

## Servicios Docker

| Servicio | Contenedor | Puerto | Descripción |
|----------|------------|--------|-------------|
| **java_app** | `java_app` | 8090:8080 | Aplicación Spring Boot |
| **java_db** | `java_db` | 3307:3306 | Base de datos MariaDB |

## 🔗 Endpoints Disponibles

### **Interfaz Web (Thymeleaf)**
- `/` - Página principal con listado de libros
- `/buscar?q=texto` - Búsqueda de libros
- `/libros/crear` - Formulario para crear libro
- `/editoriales` - Listado de editoriales

### **API REST**
- `GET /api/libros` - Obtener todos los libros
- `GET /api/libros/buscar?consulta=texto` - Buscar libros
- `GET /api/libros/editorial/{id}` - Libros por editorial
- `POST /api/libros` - Crear nuevo libro
- `GET /api/libros/test-cors` - Test de configuración CORS

## Testing de la Aplicación

### **Verificar que todo funciona:**

```bash
# Test página principal
curl http://localhost:8090/

# Test API REST
curl http://localhost:8090/api/libros

# Test CORS
curl -H "Origin: http://localhost:3000" \
     http://localhost:8090/api/libros/test-cors
```


### 🌐 **Test desde el navegador:**
```javascript
// Abrir consola del navegador (F12) y ejecutar:
fetch('http://localhost:8090/api/libros/test-cors')
  .then(response => response.text())
  .then(data => console.log('CORS OK:', data));
```

## Comandos para Parar la Aplicación

```bash
# Parar todos los contenedores
docker-compose down

# Parar y eliminar volúmenes (elimina datos)
docker-compose down -v
```
## Troubleshooting

### ❌ **Problema: Docker Desktop no está abierto**
**Error:** `Cannot connect to the Docker daemon`
**Solución:** Abrir Docker Desktop y esperar a que inicie completamente

### ❌ **Problema: Puerto ocupado**
**Error:** `Port 8090 is already in use`
**Solución:** 
```bash
# Ver qué proceso usa el puerto
netstat -ano | findstr :8090

# Cambiar puerto en docker-compose.yml o parar el proceso
```

## Características Implementadas

- **Persistencia JPA**: Entidades Libro y Editorial
- **Relaciones**: ManyToOne entre Libro y Editorial  
- **API REST**: CRUD completo con JSON
- **CORS**: Configurado para múltiples orígenes
- **Docker**: Aplicación completamente containerizada
- **Base de datos**: MariaDB con inicialización automática
- **Búsqueda**: Consultas JPQL personalizadas
- **Interfaz Web**: Formularios y listados con Thymeleaf

## Documentación Adicional

- [`CORS-DOCUMENTATION.md`](CORS-DOCUMENTATION.md) - Guía completa de configuración CORS

---

## Comando Rápido de Inicio

```bash
# Copia y pega estos comandos uno por uno:
docker build -t pee-java-app:1.0.0 .
mvn clean package -DskipTests
docker-compose up -d --build
```

** ¡La aplicación estará corriendo en http://localhost:8090!**