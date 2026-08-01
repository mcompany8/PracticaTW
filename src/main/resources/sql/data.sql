INSERT INTO cursos (id, nombre, fecha)
VALUES (1, 'Introducción a Java', '2026-02-02'),
       (2, 'Desarrollo Web con JEE', '2026-02-09'),
       (3, 'Bases de Datos Relacionales', '2026-02-16'),
       (4, 'Programación Orientada a Objetos', '2026-03-02'),
       (7, 'Introducción a Docker', '2026-03-09')
ON CONFLICT (id) DO NOTHING;