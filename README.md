# Centro Médico - Arquitectura Empresarial Basada en Microservicios

Sistema integral para la gestión de un **Centro Médico** implementado bajo el paradigma de **Arquitectura Empresarial** y arquitectura de **Microservicios** con **Spring Boot** y **Spring Cloud Netflix Eureka**.

---

## 🏥 Procesos de Negocio del Centro Médico

El sistema abarca 4 procesos clave de negocio:
1. **Proceso 1 (Implementado):** Programación de horarios de atención de médicos.
2. **Proceso 2 (Implementado):** Solicitud de cita médica.
3. **Proceso 3:** Atención médica, diagnóstico, recetas y análisis clínicos *(En desarrollo)*.
4. **Proceso 4:** Historia clínica y registro inicial de pacientes *(En desarrollo)*.

---

## ⚙️ Mapa de Servicios y Puertos

### 1. Infraestructura Común
| Servicio | Componente | Puerto | URL / Endpoint |
|---|---|:---:|---|
| **CentroMedicoEurekaServer** | Service Discovery (Eureka) | `8761` | [http://localhost:8761](http://localhost:8761) |

### 2. Backend - Proceso 1 (Programación de Horarios de Médicos)
| Microservicio | Tipo | Puerto | Descripción |
|---|---|:---:|---|
| **MicroCompProgramacionHorario** | Orquestador / Composite | `8019` | Orquesta el login asistencial, programación y franjas ([Swagger UI](http://localhost:8019/swagger-ui/index.html)) |
| **MicroValidarUsuario** | Servicio de Dominio | `8010` | Autenticación y gestión de usuarios administradores/asistenciales |
| **MicroMedico** | Servicio de Dominio | `8011` | Catálogo de médicos y especialidades |
| **MicroProgramacionHorario** | Servicio de Dominio | `8012` | Registro y gestión de programaciones (fechas/consultorios) |
| **MicroFranjaHoraria** | Servicio de Dominio | `8013` | Gestión de franjas horarias individuales (máx. 20 min) |

> 📌 **Documentación Interactiva Proceso 1 (Swagger / OpenAPI):**  
> Al iniciar el servicio orquestador (`MicroCompProgramacionHorario`), puedes probar todos los endpoints del backend desde:  
> 👉 [http://localhost:8019/swagger-ui/index.html](http://localhost:8019/swagger-ui/index.html)

### 3. Backend - Proceso 2 (Solicitud de Cita Médica)
| Microservicio | Tipo | Puerto | Descripción |
|---|---|:---:|---|
| **MicroCompCita** (`CitamedicaMicroCompCita`) | Orquestador / Composite | `9999` | Orquesta la consulta, validación y reserva ([Swagger UI](http://localhost:9999/swagger-ui/index.html)) |
| **MicroCatalogoMedico** | Servicio de Dominio | `8080` | Gestión y consulta de médicos por especialidad |
| **MicroCatalogoHorario** | Servicio de Dominio | `8081` | Consulta de fechas, turnos e intervalos de atención |
| **MicroValidar** | Servicio de Dominio | `8082` | Validación previa del paciente para reservar |
| **MicroRegistroCita** | Servicio Transaccional | `8083` | Generación y almacenamiento de la cita médica |

> 📌 **Documentación Interactiva Proceso 2 (Swagger / OpenAPI):**  
> Al iniciar el servicio orquestador (`MicroCompCita`), puedes acceder a la consola interactiva de Swagger para probar todas las APIs en:  
> 👉 [http://localhost:9999/swagger-ui/index.html](http://localhost:9999/swagger-ui/index.html)

---

## 🚀 Guía de Ejecución

### Prerrequisitos
- **Java 17** o superior
- **Maven 3.8+**
- Navegador web moderno / Postman o Swagger UI

### Orden de Inicio Recomendado

#### Paso 1: Levantar el Servidor de Descubrimiento (Eureka)
- Iniciar `CentroMedicoEurekaServer` (Puerto `8761`).
- Verificar en [http://localhost:8761](http://localhost:8761) que el panel de control de Eureka esté activo.

#### Paso 2: Ejecutar Proceso 1 - Programación de Horarios (Backend)
1. **Servicios de Dominio** (en cualquier orden):
   - `ProgramacionHorarioMicroValidarUsuario` (Puerto `8010`)
   - `ProgramacionHorarioMicroMedico` (Puerto `8011`)
   - `ProgramacionHorarioMicroProgramacionHorario` (Puerto `8012`)
   - `ProgramacionHorarioMicroFranjaHoraria` (Puerto `8013`)
2. **Servicio Orquestador**:
   - `MicroCompProgramacionHorario` (Puerto `8019`)
3. **Pruebas y Verificación**:
   - Acceder a Swagger UI: [http://localhost:8019/swagger-ui/index.html](http://localhost:8019/swagger-ui/index.html) para probar todos los endpoints REST.

#### Paso 3: Ejecutar Proceso 2 - Solicitud de Citas Médicas (Backend)
1. **Servicios de Dominio** (en cualquier orden):
   - `MicroCatalogoMedico` (Puerto `8080`)
   - `MicroCatalogoHorario` (Puerto `8081`)
   - `MicroValidar` (Puerto `8082`)
   - `MicroRegistroCita` (Puerto `8083`)
2. **Servicio Orquestador**:
   - `CitamedicaMicroCompCita` (Puerto `9999`)
3. **Pruebas y Verificación**:
   - Acceder a Swagger UI: [http://localhost:9999/swagger-ui/index.html](http://localhost:9999/swagger-ui/index.html) para probar todos los endpoints REST.
