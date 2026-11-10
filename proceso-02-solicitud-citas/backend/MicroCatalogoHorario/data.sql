-- ============================================================================
-- HORARIOS PARA MÉDICOS DE MICRONATALOGOMEDICO (IDs 1 al 16)
-- ============================================================================

-- Médico 1: Dr. Carlos Ramírez López (Medicina General) - Consultorio MG-101
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (1,  1, '2026-10-20', '08:00:00', '08:30:00', 'MG-101', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (2,  1, '2026-10-20', '08:30:00', '09:00:00', 'MG-101', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (3,  1, '2026-10-20', '09:00:00', '09:30:00', 'MG-101', 'RESERVADO');

-- Médico 2: Dra. María Gómez Torres (Medicina General) - Consultorio MG-102
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (4,  2, '2026-10-20', '10:00:00', '10:30:00', 'MG-102', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (5,  2, '2026-10-20', '10:30:00', '11:00:00', 'MG-102', 'DISPONIBLE');

-- Médico 3: Dr. José Pérez Díaz (Medicina General) - Consultorio MG-103
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (6,  3, '2026-10-20', '14:00:00', '14:30:00', 'MG-103', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (7,  3, '2026-10-20', '14:30:00', '15:00:00', 'MG-103', 'DISPONIBLE');

-- Médico 4: Dra. Ana Silva Rojas (Pediatría) - Consultorio PED-201
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (8,  4, '2026-10-21', '08:00:00', '08:30:00', 'PED-201', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (9,  4, '2026-10-21', '08:30:00', '09:00:00', 'PED-201', 'DISPONIBLE');

-- Médico 5: Dr. Luis Torres Vega (Pediatría) - Consultorio PED-202
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (10, 5, '2026-10-21', '10:00:00', '10:30:00', 'PED-202', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (11, 5, '2026-10-21', '10:30:00', '11:00:00', 'PED-202', 'DISPONIBLE');

-- Médico 6: Dra. Patricia Vega Mendoza (Pediatría) - Consultorio PED-203
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (12, 6, '2026-10-21', '15:00:00', '15:30:00', 'PED-203', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (13, 6, '2026-10-21', '15:30:00', '16:00:00', 'PED-203', 'DISPONIBLE');

-- Médico 7: Dr. Jorge Ruiz Salazar (Cardiología) - Consultorio CARD-301
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (14, 7, '2026-10-22', '09:00:00', '09:30:00', 'CARD-301', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (15, 7, '2026-10-22', '09:30:00', '10:00:00', 'CARD-301', 'DISPONIBLE');

-- Médico 8: Dra. Carmen Díaz Flores (Cardiología) - Consultorio CARD-302
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (16, 8, '2026-10-22', '11:00:00', '11:30:00', 'CARD-302', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (17, 8, '2026-10-22', '11:30:00', '12:00:00', 'CARD-302', 'DISPONIBLE');

-- Médico 9: Dr. Ricardo Paredes Luna (Dermatología) - Consultorio DERM-401
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (18, 9, '2026-10-23', '08:00:00', '08:30:00', 'DERM-401', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (19, 9, '2026-10-23', '08:30:00', '09:00:00', 'DERM-401', 'DISPONIBLE');

-- Médico 10: Dra. Sofía Herrera Cano (Dermatología) - Consultorio DERM-402
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (20, 10, '2026-10-23', '10:00:00', '10:30:00', 'DERM-402', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (21, 10, '2026-10-23', '10:30:00', '11:00:00', 'DERM-402', 'DISPONIBLE');

-- Médico 11: Dr. Miguel Ángel Castro (Ginecología) - Consultorio GIN-501
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (22, 11, '2026-10-24', '09:00:00', '09:30:00', 'GIN-501', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (23, 11, '2026-10-24', '09:30:00', '10:00:00', 'GIN-501', 'DISPONIBLE');

-- Médico 12: Dra. Lucía Fernández Ríos (Ginecología) - Consultorio GIN-502
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (24, 12, '2026-10-24', '11:00:00', '11:30:00', 'GIN-502', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (25, 12, '2026-10-24', '11:30:00', '12:00:00', 'GIN-502', 'DISPONIBLE');

-- Médico 13: Dr. Pedro Salinas Mora (Traumatología) - Consultorio TRAUM-601
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (26, 13, '2026-10-27', '08:00:00', '08:30:00', 'TRAUM-601', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (27, 13, '2026-10-27', '08:30:00', '09:00:00', 'TRAUM-601', 'DISPONIBLE');

-- Médico 14: Dra. Elena Vargas Pinto (Traumatología) - Consultorio TRAUM-602
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (28, 14, '2026-10-27', '10:00:00', '10:30:00', 'TRAUM-602', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (29, 14, '2026-10-27', '10:30:00', '11:00:00', 'TRAUM-602', 'DISPONIBLE');

-- Médico 15: Dr. Andrés Molina Cruz (Oftalmología) - Consultorio OFT-701
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (30, 15, '2026-10-28', '08:30:00', '09:00:00', 'OFT-701', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (31, 15, '2026-10-28', '09:00:00', '09:30:00', 'OFT-701', 'DISPONIBLE');

-- Médico 16: Dra. Rosa Chávez Núñez (Neurología) - Consultorio NEUR-801
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (32, 16, '2026-10-29', '14:00:00', '14:30:00', 'NEUR-801', 'DISPONIBLE');
INSERT INTO horario (id_horario, id_medico, fecha, hora_inicio, hora_fin, consultorio, estado) VALUES (33, 16, '2026-10-29', '14:30:00', '15:00:00', 'NEUR-801', 'DISPONIBLE');

ALTER TABLE horario ALTER COLUMN id_horario RESTART WITH 100;