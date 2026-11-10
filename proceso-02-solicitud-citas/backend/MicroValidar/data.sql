INSERT INTO paciente (id_paciente, nombre, dni) VALUES (1, 'Juan Pérez García',       '12345678');
INSERT INTO paciente (id_paciente, nombre, dni) VALUES (2, 'María López Fernández',  '87654321');
INSERT INTO paciente (id_paciente, nombre, dni) VALUES (3, 'Carlos Ramírez Torres',  '11223344');
INSERT INTO paciente (id_paciente, nombre, dni) VALUES (4, 'Ana Torres Vílchez',     '44332211');
INSERT INTO paciente (id_paciente, nombre, dni) VALUES (5, 'Luis Fernández Rojas',   '55667788');

ALTER TABLE paciente ALTER COLUMN id_paciente RESTART WITH 100;