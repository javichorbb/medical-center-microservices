-- ============================================================================
-- ATENCIONES MÉDICAS INICIALES PARA PRUEBAS (Historial de pacientes)
-- ============================================================================

INSERT INTO atencion_medica (id_atencion, id_cita, id_historia, id_medico, fecha, hora, diagnostico, tratamiento) VALUES
  (1, 1, 1, 1, '2026-10-15', '10:30:00', 'Faringitis bacteriana aguda', 'Reposo e hidratación por 5 días'),
  (2, 2, 1, 1, '2026-10-25', '11:00:00', 'Control post-tratamiento de faringitis', 'Alta médica, paciente recuperado'),
  (3, 3, 2, 4, '2026-10-28', '09:00:00', 'Bronquitis aguda con broncoespasmo', 'Inhalaciones cada 6 horas y abundante líquido por 7 días'),
  (4, 4, 3, 7, '2026-11-02', '09:30:00', 'Hipertensión arterial estadio 1 y sospecha de dislipidemia', 'Dieta hiposódica estricta, caminata diaria de 30 minutos'),
  (5, 5, 4, 9, '2026-11-08', '10:00:00', 'Dermatitis alérgica de contacto', 'Evitar sustancias irritantes y aplicar crema hipoalergénica');

-- ============================================================================
-- DETALLE DE RECETA MÉDICA (Medicamentos prescritos en cada atención)
-- ============================================================================

INSERT INTO detalle_receta (id_atencion, id_medicamento, dosis, cantidad) VALUES
  (1, 1, '1 tableta cada 8 horas por 7 días', 21),
  (1, 2, '1 tableta cada 8 horas condicional al dolor o fiebre', 10),
  (3, 7, '2 inhalaciones cada 6 horas por 5 días', 1),
  (3, 3, '1 tableta cada 8 horas si hay fiebre', 15),
  (4, 5, '1 cápsula en ayunas por 14 días', 14),
  (5, 6, '1 tableta diaria por la noche durante 10 días', 10);

-- ============================================================================
-- DETALLE DE ANÁLISIS CLÍNICOS (Exámenes solicitados en cada atención)
-- ============================================================================

INSERT INTO detalle_analisis (id_atencion, id_analisis, indicacion) VALUES
  (1, 1, 'En ayunas por 8 horas'),
  (1, 2, 'Muestra matutina sin enjuague bucal previo'),
  (3, 6, 'Incidencia anteroposterior y lateral'),
  (4, 8, 'Reposo previo de 15 minutos'),
  (4, 5, 'Ayuno estricto de 12 horas'),
  (4, 4, 'En ayunas'),
  (5, 1, 'En ayunas por 8 horas');
