# Configuración de CORS en Spring Boot - Biblioteca JPA

## Resumen de la Implementación

Este proyecto implementa **CORS (Cross-Origin Resource Sharing)** usando múltiples enfoques para máxima flexibilidad y compatibilidad.

## Configuraciones Implementadas

### 1. **Configuración Global (`CorsConfig.java`)**
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer
```
- Aplica a **todas las rutas** (`/**`)
- Configuración centralizada y reutilizable
- Permite orígenes específicos de desarrollo y producción

### 2. **Configuración a Nivel de Controlador (`@CrossOrigin`)**
```java
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
@RequestMapping("/api/libros")
```
- Control granular por controlador
- Configuración específica para APIs REST
- Sobrescribe configuración global si es necesario

## 🌐 Orígenes Permitidos

| Tipo | URL | Propósito |
|------|-----|-----------|
| **React** | `http://localhost:3000` | Desarrollo React |
| **Angular** | `http://localhost:4200` | Desarrollo Angular |
| **Vite** | `http://localhost:5173` | Desarrollo Vite/Vue |
| **Local** | `http://localhost:8080` | Testing local |
| **Producción** | `https://midominio.com` | Dominio de producción |

## Testing CORS

### **1. Endpoint de Prueba**
```http
GET http://localhost:8080/api/libros/test-cors
```

### **2. Test desde JavaScript (Consola del Navegador)**
```javascript
// Test básico desde cualquier página web
fetch('http://localhost:8080/api/libros/test-cors')
  .then(response => response.text())
  .then(data => console.log('CORS Response:', data))
  .catch(error => console.error('CORS Error:', error));
```

### **3. Test con cURL**
```bash
# Test preflight request
curl -H "Origin: http://localhost:3000" \
     -H "Access-Control-Request-Method: GET" \
     -H "Access-Control-Request-Headers: Content-Type" \
     -X OPTIONS \
     http://localhost:8080/api/libros

# Test GET request
curl -H "Origin: http://localhost:3000" \
     -X GET \
     http://localhost:8080/api/libros
```

### **4. Test desde HTML Local**
```html
<!DOCTYPE html>
<html>
<head>
    <title>Test CORS</title>
</head>
<body>
    <button onclick="testCors()">Test CORS</button>
    <div id="result"></div>
    
    <script>
    function testCors() {
        fetch('http://localhost:8080/api/libros/test-cors')
            .then(response => response.text())
            .then(data => {
                document.getElementById('result').innerHTML = 
                    '<span style="color: green;">CORS OK: ' + data + '</span>';
            })
            .catch(error => {
                document.getElementById('result').innerHTML = 
                    '<span style="color: red;">CORS Error: ' + error + '</span>';
            });
    }
    </script>
</body>
</html>
```

## 📡 Endpoints API Disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/libros` | Obtener todos los libros |
| `GET` | `/api/libros/buscar?consulta=texto` | Buscar libros |
| `GET` | `/api/libros/editorial/{id}` | Libros por editorial |
| `POST` | `/api/libros` | Crear nuevo libro |
| `GET` | `/api/libros/test-cors` | **Test CORS** |

## 🔧 Headers CORS Configurados

### **Permitidos:**
- `Origin`
- `Content-Type`
- `Accept`
- `Authorization`
- `X-Requested-With`

### **Métodos HTTP:**
- `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`, `PATCH`

### **Características:**
- **Credenciales permitidas** (`allowCredentials: true`)
- **Cache preflight**: 1 hora (3600 segundos)
- **Logging habilitado** para debugging

## Troubleshooting

### **Error: "CORS policy has blocked"**
1. Verificar que el origen esté en la lista permitida
2. Revisar logs del servidor: `logging.level.org.springframework.web.cors=DEBUG`
3. Verificar que el navegador no esté cacheando respuestas

### **Error: "Preflight request failed"**
1. Verificar que `OPTIONS` esté permitido
2. Revisar headers de la request
3. Verificar configuración de `allowedHeaders`

### **Desarrollo vs Producción**
```java
// Para desarrollo local
origins = {"http://localhost:3000", "http://localhost:4200"}

// Para producción
origins = {"https://midominio.com", "https://www.midominio.com"}
```

## Comandos para Testing

```bash
# 1. Ejecutar la aplicación
mvn spring-boot:run

# 2. Test endpoints
curl http://localhost:8080/api/libros/test-cors

# 3. Ver logs CORS
tail -f logs/application.log | grep CORS
```

## Buenas Prácticas Implementadas

1. **Principio de menor privilegio**: Solo orígenes específicos
2. **Métodos explícitos**: No usar `*` en producción
3. **Headers específicos**: Solo los necesarios
4. **Logging habilitado**: Para debugging en desarrollo
5. **Configuración por ambiente**: Desarrollo vs Producción
6. **Documentación completa**: Endpoints y testing

## Configuración Adicional

Para agregar nuevos orígenes, modificar en `CorsConfig.java`:
```java
.allowedOrigins(
    "http://localhost:3000",
    "https://nuevo-dominio.com"  // Agregar aquí
)
```