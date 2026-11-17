-- ============================================================================
-- ATENCIONES MÉDICAS (Historias 1 y 2 con 2 atenciones; Historias 3, 4 y 5 con 1 atención)
-- ============================================================================

-- Historia 1 (2 atenciones)
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (1, 1, 1, '2026-10-15', '10:30:00', 'Faringitis bacteriana aguda', 'Reposo e hidratacion por 5 dias', 1, 'Dr. Carlos Ramirez Lopez', 'Medicina General');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (2, 2, 1, '2026-10-22', '09:00:00', 'Control post-tratamiento de faringitis', 'Continuar hidratacion y evitar bebidas frias', 1, 'Dr. Carlos Ramirez Lopez', 'Medicina General');

-- Historia 2 (2 atenciones)
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (3, 3, 2, '2026-10-18', '11:20:00', 'Gastritis aguda leve', 'Dieta blanda por 7 dias y evitar irritantes', 2, 'Dra. Elena Mendoza Ruiz', 'Gastroenterologia');
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (4, 4, 2, '2026-11-01', '10:00:00', 'Control de gastritis en remision', 'Continuar dieta saludable y horarios regulares', 2, 'Dra. Elena Mendoza Ruiz', 'Gastroenterologia');

-- Historia 3 (1 atención)
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (5, 5, 3, '2026-10-20', '15:40:00', 'Hipertension arterial controlada', 'Dieta hiposodica y caminata diaria de 30 minutos', 3, 'Dr. Jorge Salazar Vega', 'Cardiologia');

-- Historia 4 (1 atención)
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (6, 6, 4, '2026-10-25', '08:40:00', 'Rinitis alergica estacional', 'Evitar exposicion al polvo y polen', 4, 'Dra. Patricia Quispe Leon', 'Otorrinolaringologia');

-- Historia 5 (1 atención)
INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, fecha, hora, diagnostico, tratamiento, id_medico, nombre, especialidad) VALUES (7, 7, 5, '2026-10-28', '16:00:00', 'Lumbalgia mecanica aguda', 'Reposo relativo y compresas tibias', 5, 'Dr. Miguel Angel Paredes', 'Traumatologia');

-- ============================================================================
-- RECETAS MÉDICAS (1 receta por cada Atención Médica: IDs 1 al 7)
-- ============================================================================

INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (1, 1, 1, 'Amoxicilina 500mg', 'Capsulas', '1 capsula cada 8 horas por 7 dias', 21);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (2, 2, 10, 'Paracetamol 500mg', 'Tabletas', '1 tableta cada 8 horas condicional a malestar', 10);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (3, 3, 3, 'Omeprazol 20mg', 'Capsulas', '1 capsula en ayunas por 14 dias', 14);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (4, 4, 4, 'Sucralfato 1g', 'Suspension', '10 ml antes de cada comida por 7 dias', 1);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (5, 5, 5, 'Losartan 50mg', 'Tabletas', '1 tableta cada 24 horas por la manana', 30);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (6, 6, 6, 'Loratadina 10mg', 'Tabletas', '1 tableta cada 24 horas por 10 dias', 10);
INSERT INTO receta_medica (id_receta, id_atencion, id_medicamento, nombre, presentacion, dosis, cantidad) VALUES (7, 7, 7, 'Naproxeno 550mg', 'Tabletas', '1 tableta cada 12 horas por 5 dias', 10);

-- ============================================================================
-- ANÁLISIS CLÍNICOS (1 análisis clínico por cada Atención Médica: IDs 1 al 7)
-- ============================================================================

INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (1, 1, 1, 'Hemograma completo', 'Laboratorio', 'En ayunas por 8 horas');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (2, 2, 9, 'Proteina C Reactiva (PCR)', 'Laboratorio', 'No requiere ayuno');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (3, 3, 3, 'Test de aliento para Helicobacter pylori', 'Laboratorio', 'En ayunas sin antibioticos previos');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (4, 4, 8, 'Endoscopia digestiva alta de control', 'Gastroenterologia', 'Ayuno total de 8 horas');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (5, 5, 4, 'Perfil lipidico completo', 'Laboratorio', 'En ayunas de 12 horas');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (6, 6, 6, 'Dosaje de IgE total', 'Inmunologia', 'No requiere ayuno');
INSERT INTO analisis_clinico (id_detalle_analisis, id_atencion, id_analisis, descripcion, tipo, indicacion) VALUES (7, 7, 7, 'Radiografia de columna lumbosacra', 'Imagenologia', 'Acudir sin objetos metalicos');
