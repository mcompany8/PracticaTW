-- INSERT INTO cursos (id, nombre, fecha)
-- VALUES (1, 'Introducción a Java', '2026-02-02'),
--        (2, 'Desarrollo Web con JEE', '2026-02-09'),
--        (3, 'Bases de Datos Relacionales', '2026-02-16'),
--        (4, 'Programación Orientada a Objetos', '2026-03-02'),
--        (7, 'Introducción a Docker', '2026-03-09')
-- ON CONFLICT (id) DO NOTHING;

-- INSERT INTO usuarios (email, nombre, apellidos, password_hash, tipo_usuario, direccion, poblacion, provincia, codigo_postal)
-- VALUES ('mcompany8@gmail.com', 'Miguel', 'Company Palomo', 'patatas', 'Profesor', 'Granada', 'Granada', 'Granada', '18012')
-- ON CONFLICT (id) DO NOTHING;

INSERT INTO imagenes (ruta, titulo, autor)
VALUES ('programacion.png', 'Programación', 'juicy_fish'),
       ('bases_datos.png', 'Bases de Datos', 'Vectorslab'),
       ('redes.png', 'Redes', 'Eucalyp'),
       ('inteligencia_artificial.png', 'Inteligencia Artificial', 'Magnific'),
       ('ciberseguridad.png', 'Ciberseguridad', 'juicy_fish'),
       ('desarrollo_web.png','Desarrollo web','Magnific'),
       ('sistemas_operativos.png', 'Sistemas Operativos', 'Design Circle'),
       ('cloud_computing.png', 'Cloud Computing', 'popcornarts'),
       ('videojuegos.png', 'Videojuegos', 'Konkapp'),
       ('hardware.png', 'Hardware', 'Magnific')
ON CONFLICT (id) DO NOTHING;




