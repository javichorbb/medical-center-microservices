-- ============================================================================
-- ATENCIONES MÉDICAS (Correlacionadas con MicroHistoria y MicroPaciente)
-- Historias: 1 (Juan Pérez, 2026-10-01), 2 (María López, 2026-10-05),
--            3 (Carlos Ramírez, 2026-10-10), 4 (Ana Torres, 2026-10-12),
--            5 (Luis Fernández, 2026-10-15)
-- ============================================================================

INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (1, 1, 1, '2026-10-15', '10:30:00', 'Faringitis bacteriana aguda', 'Reposo e hidratacion por 5 dias', 1, 'Dr. Carlos Ramirez Lopez', 'Medicina General');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (2, 2, 1, '2026-10-22', '09:00:00', 'Control post-tratamiento de faringitis', 'Continuar hidratacion y evitar bebidas frias', 2, 'Dra. Elena Mendoza Ruiz', 'Gastroenterologia');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (4, 4, 2, '2026-10-18', '11:20:00', 'Gastritis aguda leve', 'Dieta blanda por 7 dias, evitar irritantes', 2, 'Dra. Elena Mendoza Ruiz', 'Gastroenterologia');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (5, 5, 2, '2026-11-01', '10:00:00', 'Control de gastritis en remision', 'Continuar dieta saludable y horarios regulares de comida', 2, 'Dra. Elena Mendoza Ruiz', 'Gastroenterologia');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (6, 6, 3, '2026-10-20', '15:40:00', 'Hipertension arterial controlada', 'Dieta hiposodica y caminata diaria de 30 minutos', 3, 'Dr. Jorge Salazar Vega', 'Cardiologia');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (7, 7, 3, '2026-11-04', '16:20:00', 'Control de presion arterial y perfil lipidico', 'Continuar tratamiento actual y control en 3 meses', 3, 'Dr. Jorge Salazar Vega', 'Cardiologia');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (8, 8, 4, '2026-10-25', '08:40:00', 'Rinitis alergica estacional', 'Evitar exposicion al polvo y polen', 4, 'Dra. Patricia Quispe Leon', 'Otorrinolaringologia');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (9, 9, 4, '2026-11-05', '09:20:00', 'Sinusitis leve asociada a rinitis', 'Lavados nasales con solucion salina cada 12 horas', 4, 'Dra. Patricia Quispe Leon', 'Otorrinolaringologia');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (10, 10, 5, '2026-10-28', '16:00:00', 'Lumbalgia mecanica aguda', 'Reposo relativo y compresas tibias', 5, 'Dr. Miguel Angel Paredes', 'Traumatologia');

-- ============================================================================
-- RECETAS MÉDICAS POR ATENCIÓN
-- ============================================================================

INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (1, 1, 1, 'Amoxicilina 500mg', 'Capsulas', '1 capsula cada 8 horas por 7 dias', 21);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (2, 1, 2, 'Ibuprofeno 400mg', 'Tabletas', '1 tableta cada 8 horas condicional al dolor o fiebre', 10);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (12, 2, 10, 'Paracetamol 500mg', 'Tabletas', '1 tableta cada 8 horas condicional a malestar', 10);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (3, 4, 3, 'Omeprazol 20mg', 'Capsulas', '1 capsula en ayunas por 14 dias', 14);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (4, 4, 4, 'Sucralfato 1g', 'Suspension', '10 ml antes de cada comida por 7 dias', 1);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (5, 5, 3, 'Omeprazol 20mg', 'Capsulas', '1 capsula en ayunas por 7 dias adicionales', 7);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (6, 6, 5, 'Losartan 50mg', 'Tabletas', '1 tableta cada 24 horas por la manana', 30);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (7, 7, 5, 'Losartan 50mg', 'Tabletas', '1 tableta cada 24 horas por la manana', 30);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (8, 8, 6, 'Loratadina 10mg', 'Tabletas', '1 tableta cada 24 horas por 10 dias', 10);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (9, 9, 9, 'Budesonida nasal 64mcg', 'Spray nasal', '1 aplicacion en cada fosa nasal cada 12 horas', 1);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (10, 10, 7, 'Naproxeno 550mg', 'Tabletas', '1 tableta cada 12 horas despues de alimentos por 5 dias', 10);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (11, 10, 8, 'Orfenadrina 100mg', 'Tabletas', '1 tableta cada 12 horas por 5 dias', 10);

-- ============================================================================
-- ANÁLISIS CLÍNICOS POR ATENCIÓN
-- ============================================================================

INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (1, 1, 1, 'Hemograma completo', 'Laboratorio', 'En ayunas por 8 horas');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (2, 1, 2, 'Cultivo de hisopado faringeo', 'Microbiologia', 'Muestra matutina sin enjuague bucal previo');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (10, 2, 9, 'Proteina C Reactiva (PCR)', 'Laboratorio', 'No requiere ayuno');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (5, 4, 3, 'Test de aliento para Helicobacter pylori', 'Laboratorio', 'En ayunas, sin haber tomado antibioticos 15 dias antes');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (6, 6, 4, 'Perfil lipidico completo', 'Laboratorio', 'En ayunas de 12 horas');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (7, 6, 5, 'Electrocardiograma en reposo', 'Cardiologia', 'Acudir con ropa comoda, sin objetos metalicos');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (8, 8, 6, 'Dosaje de IgE total', 'Inmunologia', 'No requiere ayuno');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (9, 10, 7, 'Radiografia de columna lumbosacra', 'Imagenologia', 'Traer orden medica y sin objetos metalicos');
