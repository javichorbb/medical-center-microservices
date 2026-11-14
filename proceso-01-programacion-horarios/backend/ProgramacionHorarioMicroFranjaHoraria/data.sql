-- ============================================================================
-- FRANJAS HORARIAS DE ATENCIÓN (Intervalos de 20 minutos como máximo)
-- Correlacionadas con ProgramacionHorario (IDs 1 al 13)
-- ============================================================================

-- Programación 1: Dr. Carlos Ramírez López (Medicina General - Consultorio 101 - 2026-11-20)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (1, 1, '08:00:00', '08:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (2, 1, '08:20:00', '08:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (3, 1, '08:40:00', '09:00:00', 'RESERVADO');

-- Programación 2: Dr. Carlos Ramírez López (Medicina General - Consultorio 101 - 2026-11-23)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (4, 2, '10:00:00', '10:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (5, 2, '10:20:00', '10:40:00', 'DISPONIBLE');

-- Programación 3: Dra. María Gómez Torres (Medicina General - Consultorio 102 - 2026-11-20)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (6, 3, '09:00:00', '09:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (7, 3, '09:20:00', '09:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (8, 3, '09:40:00', '10:00:00', 'DISPONIBLE');

-- Programación 4: Dr. José Pérez Díaz (Medicina General - Consultorio 103 - 2026-11-21)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (9, 4, '14:00:00', '14:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (10, 4, '14:20:00', '14:40:00', 'DISPONIBLE');

-- Programación 5: Dra. Ana Silva Rojas (Pediatría - Consultorio 201 - 2026-11-20)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (11, 5, '08:00:00', '08:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (12, 5, '08:20:00', '08:40:00', 'DISPONIBLE');

-- Programación 6: Dr. Luis Torres Vega (Pediatría - Consultorio 202 - 2026-11-21)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (13, 6, '10:00:00', '10:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (14, 6, '10:20:00', '10:40:00', 'DISPONIBLE');

-- Programación 7: Dr. Jorge Ruiz Salazar (Cardiología - Consultorio 301 - 2026-11-20)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (15, 7, '08:00:00', '08:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (16, 7, '08:20:00', '08:40:00', 'DISPONIBLE');

-- Programación 8: Dra. Carmen Díaz Flores (Cardiología - Consultorio 302 - 2026-11-21)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (17, 8, '15:00:00', '15:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (18, 8, '15:20:00', '15:40:00', 'RESERVADO');

-- Programación 9: Dr. Ricardo Paredes Luna (Dermatología - Consultorio 104 - 2026-11-22)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (19, 9, '09:00:00', '09:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (20, 9, '09:20:00', '09:40:00', 'DISPONIBLE');

-- Programación 10: Dr. Miguel Ángel Castro (Ginecología - Consultorio 401 - 2026-11-22)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (21, 10, '10:00:00', '10:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (22, 10, '10:20:00', '10:40:00', 'DISPONIBLE');

-- Programación 11: Dr. Pedro Salinas Mora (Traumatología - Consultorio 203 - 2026-11-22)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (23, 11, '11:00:00', '11:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (24, 11, '11:20:00', '11:40:00', 'DISPONIBLE');

-- Programación 12: Dr. Andrés Molina Cruz (Oftalmología - Consultorio 501 - 2026-11-23)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (25, 12, '09:00:00', '09:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (26, 12, '09:20:00', '09:40:00', 'DISPONIBLE');

-- Programación 13: Dra. Rosa Chávez Núñez (Neurología - Consultorio 502 - 2026-11-23)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (27, 13, '16:00:00', '16:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (28, 13, '16:20:00', '16:40:00', 'DISPONIBLE');
