# Centro Médico - Arquitectura Empresarial Basada en Microservicios

Sistema integral para la gestión de un **Centro Médico** implementado bajo el paradigma de **Arquitectura Empresarial** y arquitectura de **Microservicios** con **Spring Boot** y **Spring Cloud Netflix Eureka**.

---

## 🏥 Procesos de Negocio del Centro Médico

El sistema abarca 4 procesos clave de negocio:
1. **Proceso 1:** Programación de horarios de atención de médicos *(En desarrollo)*.
2. **Proceso 2 (Implementado):** Solicitud de cita médica.
3. **Proceso 3:** Atención médica, diagnóstico, recetas y análisis clínicos *(En desarrollo)*.
4. **Proceso 4:** Historia clínica y registro inicial de pacientes *(En desarrollo)*.

---

## ⚙️ Mapa de Servicios y Puertos

### 1. Infraestructura Común
| Servicio | Componente | Puerto | URL / Endpoint |
|---|---|:---:|---|
| **CentroMedicoEurekaServer** | Service Discovery (Eureka) | `8761` | [http://localhost:8761](http://localhost:8761) |

### 2. Backend - Proceso 2 (Solicitud de Cita Médica)
| Microservicio | Tipo | Puerto | Descripción |
|---|---|:---:|---|
| **MicroCompCita** (`CitamedicaMicroCompCita`) | Orquestador / Composite | `9999` | Orquesta la consulta, validación y reserva ([Swagger UI](http://localhost:9999/swagger-ui/index.html)) |
| **MicroCatalogoMedico** | Servicio de Dominio | `8080` | Gestión y consulta de médicos por especialidad |
| **MicroCatalogoHorario** | Servicio de Dominio | `8081` | Consulta de fechas, turnos e intervalos de atención |
| **MicroValidar** | Servicio de Dominio | `8082` | Validación previa del paciente para reservar |
| **MicroRegistroCita** | Servicio Transaccional | `8083` | Generación y almacenamiento de la cita médica |

> 📌 **Documentación Interactiva (Swagger / OpenAPI):**  
> Al iniciar el servicio orquestador (`MicroCompCita`), puedes acceder a la consola interactiva de Swagger para probar todas las APIs en:  
> 👉 [http://localhost:9999/swagger-ui/index.html](http://localhost:9999/swagger-ui/index.html)

### 3. Frontend / Vistas Web - Proceso 2
| Aplicación Web | Puerto | Rol |
|---|---|:---:|
| **VistaCitaMedica** | `9090` | **Portal principal** de reserva de cita médica |
| **VistaCatalogoMedico** | `8090` | Interfaz de selección de médico especialista |
| **VistaCatalogoHorario** | `8091` | Interfaz de selección de horario disponible |
| **VistaValidar** | `8092` | Interfaz de validación de identidad del paciente |
| **VistaRegistroCita** | `8093` | Interfaz de confirmación y resumen de la cita |

---

## 🚀 Guía de Ejecución

### Prerrequisitos
- **Java 17** o superior
- **Maven 3.8+**
- Navegador web moderno

### Orden de Inicio Recomendado
1. **Paso 1: Levantar el Servidor Eureka**
   - Iniciar `CentroMedicoEurekaServer` (Puerto `8761`).
   - Verificar en [http://localhost:8761](http://localhost:8761) que el panel de control esté activo.
2. **Paso 2: Iniciar los Microservicios Backend**
   - Ejecutar en cualquier orden: `MicroCatalogoMedico` (8080), `MicroCatalogoHorario` (8081), `MicroValidar` (8082), `MicroRegistroCita` (8083).
   - Iniciar el orquestador: `CitamedicaMicroCompCita` (9999).
   - Verificar en el panel de Eureka que todos aparezcan en estado `UP`.
3. **Paso 3: Iniciar la Capa de Vistas**
   - Iniciar `VistaCitaMedica` (Puerto `9090`).
   - Acceder desde el navegador a `http://localhost:9090` para realizar el flujo de reserva de cita.

