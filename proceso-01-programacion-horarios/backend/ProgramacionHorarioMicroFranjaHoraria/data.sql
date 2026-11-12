-- ============================================================================
-- FRANJAS HORARIAS DE ATENCIÓN (Intervalos de 20 minutos como máximo)
-- Correlacionadas con ProgramacionHorario (IDs 1 al 9)
-- ============================================================================

-- Programación 1: Dr. Carlos Ramírez López (Medicina General - Consultorio 101 - 2026-11-20)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (1, 1, '08:00:00', '08:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (2, 1, '08:20:00', '08:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (3, 1, '08:40:00', '09:00:00', 'DISPONIBLE');

-- Programación 2: Dra. María Gómez Torres (Medicina General - Consultorio 102 - 2026-11-20)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (4, 2, '09:00:00', '09:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (5, 2, '09:20:00', '09:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (6, 2, '09:40:00', '10:00:00', 'DISPONIBLE');

-- Programación 3: Dr. José Pérez Díaz (Medicina General - Consultorio 103 - 2026-11-21)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (7, 3, '14:00:00', '14:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (8, 3, '14:20:00', '14:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (9, 3, '14:40:00', '15:00:00', 'DISPONIBLE');

-- Programación 4: Dra. Ana Silva Rojas (Pediatría - Consultorio 201 - 2026-11-20)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (10, 4, '08:00:00', '08:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (11, 4, '08:20:00', '08:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (12, 4, '08:40:00', '09:00:00', 'DISPONIBLE');

-- Programación 5: Dr. Luis Torres Vega (Pediatría - Consultorio 202 - 2026-11-21)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (13, 5, '10:00:00', '10:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (14, 5, '10:20:00', '10:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (15, 5, '10:40:00', '11:00:00', 'DISPONIBLE');

-- Programación 6: Dr. Jorge Ruiz Salazar (Cardiología - Consultorio 301 - 2026-11-20)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (16, 6, '08:00:00', '08:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (17, 6, '08:20:00', '08:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (18, 6, '08:40:00', '09:00:00', 'DISPONIBLE');

-- Programación 7: Dra. Carmen Díaz Flores (Cardiología - Consultorio 302 - 2026-11-21)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (19, 7, '15:00:00', '15:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (20, 7, '15:20:00', '15:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (21, 7, '15:40:00', '16:00:00', 'DISPONIBLE');

-- Programación 8: Dr. Ricardo Paredes Luna (Dermatología - Consultorio 104 - 2026-11-22)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (22, 8, '09:00:00', '09:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (23, 8, '09:20:00', '09:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (24, 8, '09:40:00', '10:00:00', 'DISPONIBLE');

-- Programación 9: Dr. Pedro Salinas Mora (Traumatología - Consultorio 203 - 2026-11-22)
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (25, 9, '11:00:00', '11:20:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (26, 9, '11:20:00', '11:40:00', 'DISPONIBLE');
INSERT INTO franja_horaria (id_franja, id_programacion, hora_inicio, hora_fin, estado) VALUES (27, 9, '11:40:00', '12:00:00', 'DISPONIBLE');
