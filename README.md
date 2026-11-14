# Centro Médico - Arquitectura Empresarial de Microservicios

Sistema integral para la gestión de un **Centro Médico** implementado bajo el paradigma de **Arquitectura Empresarial** y arquitectura orientada a **Microservicios** con **Java 17**, **Spring Boot**, **Spring Cloud Netflix Eureka** y documentación interactiva estandarizada con **SpringDoc OpenAPI (Swagger UI)**.

---

## 📋 Catálogo General de Microservicios y Swagger UI

A continuación se listan todos los microservicios backend del sistema con sus puertos reales de ejecución y sus consolas interactivas de Swagger UI.

### Infraestructura Común
| Microservicio | Puerto | Consola Swagger UI / Panel |
|---|:---:|---|
| **CentroMedicoEurekaServer** | `8761` | [http://localhost:8761](http://localhost:8761) *(Panel Eureka Dashboard)* |

### Proceso 1: Programación de Horarios de Atención Médica
| Microservicio | Tipo | Puerto | Consola Swagger UI |
|---|---|:---:|---|
| **MicroCompProgramacionHorario** | Compuesto / Orquestador | `8019` | [http://localhost:8019/swagger-ui/index.html](http://localhost:8019/swagger-ui/index.html) |
| **MicroValidarUsuario** | Atómico (Dominio) | `8010` | [http://localhost:8010/swagger-ui/index.html](http://localhost:8010/swagger-ui/index.html) |
| **MicroMedico** | Atómico (Catálogo) | `8011` | [http://localhost:8011/swagger-ui/index.html](http://localhost:8011/swagger-ui/index.html) |
| **MicroProgramacionHorario** | Atómico (Entidad) | `8012` | [http://localhost:8012/swagger-ui/index.html](http://localhost:8012/swagger-ui/index.html) |
| **MicroFranjaHoraria** | Atómico (Entidad) | `8013` | [http://localhost:8013/swagger-ui/index.html](http://localhost:8013/swagger-ui/index.html) |

### Proceso 2: Solicitud de Cita Médica
| Microservicio | Tipo | Puerto | Consola Swagger UI |
|---|---|:---:|---|
| **CitamedicaMicroCompCita** (`MicroCompCita`) | Compuesto / Orquestador | `8029` | [http://localhost:8029/swagger-ui/index.html](http://localhost:8029/swagger-ui/index.html) |
| **MicroCatalogoMedico** | Atómico (Catálogo) | `8020` | [http://localhost:8020/swagger-ui/index.html](http://localhost:8020/swagger-ui/index.html) |
| **MicroCatalogoHorario** | Atómico (Catálogo) | `8021` | [http://localhost:8021/swagger-ui/index.html](http://localhost:8021/swagger-ui/index.html) |
| **MicroValidar** | Atómico (Dominio) | `8022` | [http://localhost:8022/swagger-ui/index.html](http://localhost:8022/swagger-ui/index.html) |
| **MicroRegistroCita** | Atómico (Transaccional) | `8023` | [http://localhost:8023/swagger-ui/index.html](http://localhost:8023/swagger-ui/index.html) |

### Proceso 3: Atención Médica, Diagnóstico, Recetas y Análisis Clínicos
| Microservicio | Tipo | Puerto | Consola Swagger UI |
|---|---|:---:|---|
| **micro-atencion-compuesto** | Compuesto / Orquestador | `8036` | [http://localhost:8036/swagger-ui/index.html](http://localhost:8036/swagger-ui/index.html) |
| **micro-medico** | Atómico (Catálogo) | `8031` | [http://localhost:8031/swagger-ui/index.html](http://localhost:8031/swagger-ui/index.html) |
| **micro-historia** | Atómico (Referencial) | `8032` | [http://localhost:8032/swagger-ui/index.html](http://localhost:8032/swagger-ui/index.html) |
| **micro-atencion** | Atómico (Transaccional) | `8033` | [http://localhost:8033/swagger-ui/index.html](http://localhost:8033/swagger-ui/index.html) |
| **micro-medicamento** | Atómico (Catálogo) | `8034` | [http://localhost:8034/swagger-ui/index.html](http://localhost:8034/swagger-ui/index.html) |
| **micro-analisis** | Atómico (Catálogo) | `8035` | [http://localhost:8035/swagger-ui/index.html](http://localhost:8035/swagger-ui/index.html) |
| **micro-cesta-receta** | Atómico (Cesta / Estado Temporal) | `8038` | [http://localhost:8038/swagger-ui/index.html](http://localhost:8038/swagger-ui/index.html) |
| **micro-cesta-analisis** | Atómico (Cesta / Estado Temporal) | `8039` | [http://localhost:8039/swagger-ui/index.html](http://localhost:8039/swagger-ui/index.html) |

### Proceso 4: Historia Clínica y Registro de Pacientes
| Microservicio | Tipo | Puerto | Consola Swagger UI |
|---|---|:---:|---|
| **MicroHistoriaClinicaCompuesto** | Compuesto / Orquestador | `8049` | [http://localhost:8049/swagger-ui/index.html](http://localhost:8049/swagger-ui/index.html) |
| **MicroValidarEnfermera** | Atómico (Dominio) | `8040` | [http://localhost:8040/swagger-ui/index.html](http://localhost:8040/swagger-ui/index.html) |
| **MicroPaciente** | Atómico (Entidad) | `8041` | [http://localhost:8041/swagger-ui/index.html](http://localhost:8041/swagger-ui/index.html) |
| **MicroAtencionMedica** | Atómico (Consulta / Entidad) | `8042` | [http://localhost:8042/swagger-ui/index.html](http://localhost:8042/swagger-ui/index.html) |
| **MicroHistoria** | Atómico (Entidad) | `8043` | [http://localhost:8043/swagger-ui/index.html](http://localhost:8043/swagger-ui/index.html) |

---

## 🔌 Matriz de Dependencias: Microservicios Requeridos Activos

Para que cada proceso de negocio y su servicio orquestador/compuesto funcionen adecuadamente y puedan resolver sus llamadas REST sin errores de comunicación, deben estar encendidos los siguientes microservicios:

### 1. Para Proceso 1 (`MicroCompProgramacionHorario` - Puerto `8019`)
Requiere tener encendidos:
1. `CentroMedicoEurekaServer` (Puerto `8761`) - Servidor de descubrimiento.
2. `ProgramacionHorarioMicroValidarUsuario` (Puerto `8010`) - Autenticación del usuario administrativo.
3. `ProgramacionHorarioMicroMedico` (Puerto `8011`) - Catálogo de especialidades y datos de médicos.
4. `ProgramacionHorarioMicroProgramacionHorario` (Puerto `8012`) - Cabecera de programación de horarios.
5. `ProgramacionHorarioMicroFranjaHoraria` (Puerto `8013`) - Franjas horarias de 20 minutos.
6. `MicroCompProgramacionHorario` (Puerto `8019`) - Servicio orquestador.

### 2. Para Proceso 2 (`CitamedicaMicroCompCita` - Puerto `8029`)
Requiere tener encendidos:
1. `CentroMedicoEurekaServer` (Puerto `8761`) - Servidor de descubrimiento.
2. `MicroCatalogoMedico` (Puerto `8020`) - Catálogo de especialidades y médicos especialistas.
3. `MicroCatalogoHorario` (Puerto `8021`) - Horarios disponibles del médico (fechas, intervalos y consultorios).
4. `MicroValidar` (Puerto `8022`) - Validación previa del paciente por DNI.
5. `MicroRegistroCita` (Puerto `8023`) - Registro transaccional de la cita médica.
6. `CitamedicaMicroCompCita` (Puerto `8029`) - Servicio orquestador.

### 3. Para Proceso 3 (`micro-atencion-compuesto` - Puerto `8036`)
Requiere tener encendidos:
1. `CentroMedicoEurekaServer` (Puerto `8761`) - Servidor de descubrimiento.
2. `micro-atencion` (Puerto `8033`) - Persistencia de la atención médica, diagnóstico y tratamiento.
3. `micro-medico` (Puerto `8031`) - Verificación y enriquecimiento del médico tratante.
4. `micro-historia` (Puerto `8032`) - Verificación de la historia clínica referenciada.
5. `micro-medicamento` (Puerto `8034`) - Catálogo de medicamentos para la receta médica.
6. `micro-analisis` (Puerto `8035`) - Catálogo de tipos de análisis clínicos solicitados.
7. `micro-cesta-receta` (Puerto `8038`) - Gestión temporal de medicamentos de la receta por número de atención.
8. `micro-cesta-analisis` (Puerto `8039`) - Gestión temporal de análisis clínicos por número de atención.
9. `micro-atencion-compuesto` (Puerto `8036`) - Servicio orquestador.

### 4. Para Proceso 4 (`MicroHistoriaClinicaCompuesto` - Puerto `8049`)
El servicio compuesto de historia clínica funciona de forma autónoma e independiente dentro de su propio paquete de microservicios. Requiere tener activos:

#### A. Infraestructura Base:
1. `CentroMedicoEurekaServer` (Puerto `8761`) - Servidor de descubrimiento.

#### B. Servicios Propios del Proceso 4:
2. `MicroValidarEnfermera` (Puerto `8040`) - Autenticación de enfermera.
3. `MicroPaciente` (Puerto `8041`) - Consulta y registro de datos personales del paciente.
4. `MicroAtencionMedica` (Puerto `8042`) - Consulta de atenciones médicas asociadas a la historia (con sus recetas médicas y análisis clínicos).
5. `MicroHistoria` (Puerto `8043`) - Registro y consulta de la apertura de la historia clínica.
6. `MicroHistoriaClinicaCompuesto` (Puerto `8049`) - Servicio orquestador.

> ℹ️ **Autonomía del Proceso 4:** Para operar de forma independiente antes de la integración global de todos los procesos, el Proceso 4 cuenta con su propio microservicio local `MicroAtencionMedica` (puerto `8042`), permitiendo consultar el historial médico completo sin depender del Proceso 3.

---

## 🧪 Guía de Pruebas de Endpoints por Caso de Negocio

A continuación se detallan las solicitudes exactas para probar los 4 flujos de negocio del caso de estudio, centrados en los servicios compuestos correspondientes.

---

### Caso 1: Programación de Horarios de Atención de Médicos

> **Descripción del negocio:**  
> El personal administrativo elabora la programación de horarios para la atención médica (especialidad, médicos, fechas de atención, consultorio de atención, franjas horarias en intervalos de 20 minutos como máximo). Asimismo, elabora la consulta por especialidad mostrando los médicos programados, su programación por fecha y tiempos de atención.

#### 1. Autenticación de Personal Administrativo
- **Método:** `POST`
- **URL:** `http://localhost:8019/validarUsuario`
- **Request Body (JSON):**
```json
{
  "username": "admin",
  "password": "123"
}
```

#### 2. Consultar Especialidades Médicas
- **Método:** `GET`
- **URL:** `http://localhost:8019/medicos/especialidades`
- **Request Body:** *(Sin cuerpo)*

#### 3. Consultar Médicos Programados por Especialidad (incluye programaciones y franjas)
- **Método:** `GET`
- **URL:** `http://localhost:8019/medicos/especialidad/Cardiologia`
- **Request Body:** *(Sin cuerpo)*

#### 4. Registrar Cabecera de Programación de Horario
- **Método:** `POST`
- **URL:** `http://localhost:8019/programaciones/grabar`
- **Request Body (JSON):**
```json
{
  "idMedico": 1,
  "fecha": "2026-11-20",
  "consultorio": "Consultorio 101"
}
```

#### 5. Registrar Franja Horaria de Atención (intervalo máximo de 20 minutos)
- **Método:** `POST`
- **URL:** `http://localhost:8019/franjas/grabar`
- **Request Body (JSON):**
```json
{
  "idProgramacion": 1,
  "horaInicio": "08:00",
  "horaFin": "08:20"
}
```

#### 6. Consultar Detalle de Programación por ID (con Médico y Franjas)
- **Método:** `GET`
- **URL:** `http://localhost:8019/programaciones/1`
- **Request Body:** *(Sin cuerpo)*

#### 7. Consultar Ficha Completa del Médico Programado
- **Método:** `POST`
- **URL:** `http://localhost:8019/consultarFichaMedicoProgramado`
- **Request Body (JSON):**
```json
{
  "idMedico": 1
}
```

---

### Caso 2: Solicitud de Cita Médica

> **Descripción del negocio:**  
> Para ser atendido el paciente consulta médicos por especialidad; el sistema proporciona la lista de médicos especialistas. Por cada médico seleccionado se muestra la fecha, horas de atención, consultorio de atención y estado. A continuación, el paciente debe validarse previamente y reservar la cita médica.

#### 1. Consultar Especialidades Médicas
- **Método:** `GET`
- **URL:** `http://localhost:8029/especialidades`
- **Request Body:** *(Sin cuerpo)*

#### 2. Consultar Médicos por Especialidad
- **Método:** `GET`
- **URL:** `http://localhost:8029/medicos/Cardiologia`
- **Request Body:** *(Sin cuerpo)*

#### 3. Consultar Ficha del Médico Seleccionado (Horarios, Fechas, Turnos y Consultorios)
- **Método:** `POST`
- **URL:** `http://localhost:8029/consultarFichaMedico`
- **Request Body (JSON):**
```json
{
  "idMedico": 1
}
```

#### 4. Consultar Horarios Disponibles del Médico
- **Método:** `GET`
- **URL:** `http://localhost:8029/horariosDisponibles/1`
- **Request Body:** *(Sin cuerpo)*

#### 5. Validar Paciente Previamente por DNI
- **Método:** `POST`
- **URL:** `http://localhost:8029/validarPaciente`
- **Request Body (JSON):**
```json
{
  "dni": "12345678"
}
```

#### 6. Registrar Cita Médica
- **Método:** `POST`
- **URL:** `http://localhost:8029/registrarCita`
- **Request Body (JSON):**
```json
{
  "idHorario": 1,
  "idPaciente": 1
}
```

#### 7. Consultar Comprobante de Cita Médica por ID
- **Método:** `GET`
- **URL:** `http://localhost:8029/cita/1`
- **Request Body:** *(Sin cuerpo)*

#### 8. Consultar Citas Reservadas por DNI del Paciente
- **Método:** `GET`
- **URL:** `http://localhost:8029/citas/dni/12345678`
- **Request Body:** *(Sin cuerpo)*

---

### Caso 3: Atención Médica, Diagnóstico, Recetas y Análisis Clínicos

> **Descripción del negocio:**  
> El médico consulta la cita médica, se genera la atención médica y examina al paciente. Registra el diagnóstico y tratamiento. A dicha ficha se le añaden la receta de medicamentos y/o análisis clínicos. La referencia utilizada es el número de atención médica. Para la receta, selecciona medicamentos del catálogo; para los análisis, selecciona del catálogo de tipos de análisis. Al finalizar, registra la atención médica completa.

#### 1. Consultar la Cita Médica a Atender (generada en Proceso 2)
- **Método:** `GET`
- **URL:** `http://localhost:8029/cita/1`
- **Request Body:** *(Sin cuerpo)*

#### 2. Consultar Catálogo de Medicamentos
- **Método:** `GET`
- **URL:** `http://localhost:8034/medicamento/listar`
- **Request Body:** *(Sin cuerpo)*

#### 3. Añadir Medicamento a la Receta Médica en Cesta (referencia: `AT-001`)
- **Método:** `POST`
- **URL:** `http://localhost:8038/cesta-receta/AT-001/agregar`
- **Request Body (JSON):**
```json
{
  "idMedicamento": 1,
  "dosis": "500mg cada 8 horas por 5 dias",
  "cantidad": 15
}
```

#### 4. Consultar Catálogo de Tipos de Análisis Clínicos
- **Método:** `GET`
- **URL:** `http://localhost:8035/analisis/listar`
- **Request Body:** *(Sin cuerpo)*

#### 5. Añadir Análisis Clínico a la Orden en Cesta (referencia: `AT-001`)
- **Método:** `POST`
- **URL:** `http://localhost:8039/cesta-analisis/AT-001/agregar`
- **Request Body (JSON):**
```json
{
  "idAnalisis": 1,
  "indicacion": "En ayunas de 8 horas"
}
```

#### 6. Registrar Atención Médica Completa (asocia las cestas mediante `AT-001` y las vacía)
- **Método:** `POST`
- **URL:** `http://localhost:8036/atencion-compuesto/registrar/AT-001`
- **Request Body (JSON):**
```json
{
  "idCita": 1,
  "idMedico": 1,
  "idHistoria": 1,
  "diagnostico": "Faringitis aguda",
  "tratamiento": "Tratamiento antibiotico oral y reposo medico por 3 dias"
}
```

#### 7. Consultar Atención Médica Enriquecida por su ID
- **Método:** `GET`
- **URL:** `http://localhost:8036/atencion-compuesto/buscar/1`
- **Request Body:** *(Sin cuerpo)*

#### 8. Consultar Atenciones Médicas por Número de Historia Clínica
- **Método:** `GET`
- **URL:** `http://localhost:8036/atencion-compuesto/historia/1`
- **Request Body:** *(Sin cuerpo)*

---

### Caso 4: Historia Clínica y Registro de Pacientes

> **Descripción del negocio:**  
> Para ser atendido el paciente, la enfermera registra sus datos personales siempre y cuando sea su primera cita médica, generándose de esta manera su historia clínica. Esta contiene el historial de atenciones médicas del paciente; toda esta información se requiere conocer.

#### 1. Autenticación / Login de la Enfermera
- **Método:** `POST`
- **URL:** `http://localhost:8049/historia-compuesto/validarEnfermera`
- **Request Body (JSON):**
```json
{
  "username": "enfermera.ana",
  "password": "123"
}
```

#### 2. Registrar Paciente en su Primera Cita Médica y Aperturar Historia Clínica
*(Si el paciente ya existe por su DNI, no se duplica: el sistema recupera su expediente y su historial existente).*
- **Método:** `POST`
- **URL:** `http://localhost:8049/historia-compuesto/registrarPaciente`
- **Request Body (JSON):**
```json
{
  "dni": "72345678",
  "nombre": "Carlos Alberto Mendoza",
  "genero": "MASCULINO",
  "telefono": "987654321"
}
```

#### 3. Consultar Historia Clínica Completa por DNI del Paciente (incluye paciente + fecha de apertura + historial de atenciones médicas con recetas y análisis clínicos)
- **Método:** `GET`
- **URL:** `http://localhost:8049/historia-compuesto/dni/72345678`
- **Request Body:** *(Sin cuerpo)*

#### 4. Consultar Historia Clínica Completa por ID de Paciente
- **Método:** `GET`
- **URL:** `http://localhost:8049/historia-compuesto/paciente/1`
- **Request Body:** *(Sin cuerpo)*
