-- =========================
-- CURSOS
-- =========================
INSERT INTO cursos (id, titulo, descripcion, nivel, duracion_horas, responsable_id, imagen)
VALUES
    (1,  'Fundamentos de Programación',                     'Introducción a los conceptos básicos de programación: variables, tipos de datos, condicionales y bucles.',                  'BASICO',      20, 1, '01_fundamentos_de_programacion.jpeg'),
    (2,  'Lógica de Programación y Algoritmos',              'Desarrollo del pensamiento algorítmico mediante la resolución de problemas paso a paso.',                                   'BASICO',      25, 1, '02_logica_de_programacion_y_algoritmos.jpeg'),
    (3,  'Bases de Datos con SQL',                           'Diseño de esquemas relacionales y consultas SQL desde cero hasta nivel intermedio.',                                        'BASICO',      30, 2, '03_bases_de_datos_con_sql.jpeg'),
    (4,  'Desarrollo Web HTML, CSS y JavaScript',             'Construcción de páginas web interactivas usando el trío clásico de tecnologías frontend.',                                  'BASICO',      35, 3, '04_desarrollo_web_html_css_y_javascript.jpeg'),
    (5,  'Desarrollo Web con React.js',                      'Creación de interfaces de usuario modernas y reactivas con la librería React.',                                             'INTERMEDIO',  40, 3, '05_desarrollo_web_con_react_js.jpeg'),
    (6,  'Backend con Node.js y Express',                    'Construcción de servidores y APIs con Node.js y el framework Express.',                                                    'INTERMEDIO',  35, 3, '06_backend_con_node_js_y_express.jpeg'),
    (7,  'APIs RESTful con Node.js',                         'Diseño e implementación de APIs REST siguiendo buenas prácticas de arquitectura.',                                          'INTERMEDIO',  30, 5, '07_apis_restful_con_node_js.jpeg'),
    (8,  'Control de Versiones con Git y GitHub',             'Uso de Git para el control de versiones y colaboración en proyectos mediante GitHub.',                                      'BASICO',      15, 4, '08_control_de_versiones_con_git_y_github.jpeg'),
    (9,  'Pruebas de Software con Jest',                     'Escritura de tests unitarios y de integración en JavaScript con el framework Jest.',                                        'INTERMEDIO',  20, 4, '09_pruebas_de_software_con_jest.jpeg'),
    (10, 'Docker y Contenedores',                            'Empaquetado y despliegue de aplicaciones mediante contenedores Docker.',                                                    'INTERMEDIO',  25, 4, '10_docker_y_contenedores.jpeg'),
    (11, 'Estructuras de Datos Avanzadas',                   'Estudio de árboles, grafos y estructuras de datos avanzadas para resolver problemas complejos.',                            'AVANZADO',    40, 1, '14_estructuras_de_datos_avanzadas.jpeg')
--     (12, 'Google Cloud Platform Fundamentos',                'Introducción a los servicios principales de Google Cloud Platform.',                                                       'BASICO',      25, 4, '12_google_cloud_platform_fundamentos.jpeg'),
--     (13, 'Seguridad Informática para Desarrolladores',        'Buenas prácticas de seguridad aplicadas al desarrollo de software.',                                                       'INTERMEDIO',  30, 5, '13_seguridad_informatica_para_desarrolladores.jpeg'),
--     (14, 'Despliegue en la Nube con AWS',                    'Despliegue y gestión de aplicaciones en Amazon Web Services.',                                                             'AVANZADO',    35, 4, '11_despliegue_en_la_nube_con_aws.jpeg'),
--     (15, 'Introducción a la Inteligencia Artificial',         'Conceptos fundamentales de IA: búsqueda, representación del conocimiento y aprendizaje automático.',                        'BASICO',      30, 2, '15_introduccion_a_la_inteligencia_artificial.jpeg'),
--     (16, 'Análisis de Datos con Python y Pandas',             'Manipulación, limpieza y análisis de datos tabulares utilizando la librería Pandas.',                                       'INTERMEDIO',  30, 2, '16_analisis_de_datos_con_python_y_pandas.jpeg'),
--     (17, 'Desarrollo Móvil con Flutter',                     'Creación de aplicaciones móviles multiplataforma con el framework Flutter.',                                                'INTERMEDIO',  35, 3, '17_desarrollo_movil_con_flutter.jpeg'),
--     (18, 'Metodologías Ágiles con Scrum',                     'Fundamentos de Scrum y gestión ágil de proyectos de software.',                                                             'BASICO',      15, 5, '18_metodologias_agiles_con_scrum.jpeg'),
--     (19, 'Patrones de Diseño de Software',                   'Estudio de los patrones de diseño más comunes en la ingeniería de software orientada a objetos.',                            'AVANZADO',    30, 1, '19_patrones_de_diseno_de_software.jpeg'),
--     (20, 'DevOps: Integración y Despliegue Continuo (CI/CD)', 'Automatización de la integración y el despliegue continuo mediante pipelines CI/CD.',                                       'AVANZADO',    35, 4, '20_devops_integracion_y_despliegue_continuo_ci_cd.jpeg')
ON CONFLICT (id) DO NOTHING;

SELECT setval('cursos_id_seq', COALESCE((SELECT MAX(id) FROM cursos), 1));

-- =========================
-- TEMATICAS
-- =========================
INSERT INTO tematicas (id, titulo, imagen)
VALUES (1, 'Programación y Desarrollo de Software', '01_programacion_desarrollo_software.png'),
       (2, 'Desarrollo Web y Móvil', '02_desarrollo_web_movil.png'),
       (3, 'Bases de Datos y Gestión de Datos', '03_bases_datos.png'),
       (4, 'Cloud, DevOps e Infraestructura', '04_cloud_devops.png')
--        (5, 'Ciberseguridad', '05_ciberseguridad.png'),
--        (6, 'Inteligencia Artificial y Ciencia de Datos', '06_ia_ciencia_datos.png')
ON CONFLICT (id) DO NOTHING;


-- =========================
-- CURSO_TEMATICA (relación N:M)
-- =========================
INSERT INTO cursos_tematicas (curso_id, tematica_id)
VALUES (1,  1),
       (2,  1),
       (3,  3),
       (4,  2),
       (5,  2),
       (6,  2), (6,  1),
       (7,  2), (7,  1),
       (8,  1), (8,  4),
       (9,  1), (9,  2),
       (10, 4),
       (11, 1), (11, 3)
--        (12, 4),
--        (13, 5), (13, 1),
--        (14, 4),
--        (15, 6),
--        (16, 6), (16, 3),
--        (17, 2),
--        (18, 1),
--        (19, 1),
--        (20, 4), (20, 1)
ON CONFLICT DO NOTHING;

-- =========================
-- INSCRIPCIONES
-- =========================

-- Inscripciones (matrículas) de alumnos en cursos
-- 20 cursos (id 1-20), 40 alumnos (id 6-45)
-- Cada alumno matriculado en 0 a 3 cursos, sin repeticiones
-- fecha_inscripcion: fecha aleatoria previa al inicio del curso

INSERT INTO inscripciones (estudiante_id, curso_id, fecha_inscripcion)
VALUES
    (6, 3, '2026-01-12'),
    (6, 8, '2026-02-05'),

    (7, 2, '2026-01-18'),
    (7, 6, '2026-02-03'),
    (7, 11, '2026-02-14'),

    (8, 1, '2026-01-25'),

    (10, 3, '2026-02-01'),
    (10, 7, '2026-02-07'),
    (10, 10, '2026-02-18'),

    (11, 2, '2026-01-20'),
    (11, 4, '2026-02-12'),

    (12, 5, '2026-01-15'),

    (13, 1, '2026-01-27'),
    (13, 8, '2026-02-08'),
    (13, 11, '2026-02-16'),

    (15, 4, '2026-01-22'),
    (15, 9, '2026-02-05'),

    (16, 3, '2026-01-14'),
    (16, 5, '2026-02-11'),
    (16, 10, '2026-02-19'),

    (17, 2, '2026-02-03'),

    (18, 1, '2026-01-19'),
    (18, 7, '2026-02-10'),

    (19, 6, '2026-01-28'),
    (19, 8, '2026-02-15'),

    (20, 11, '2026-01-21'),

    (21, 2, '2026-02-02'),
    (21, 3, '2026-02-12'),
    (21, 9, '2026-02-17'),

    (22, 5, '2026-01-13'),
    (22, 8, '2026-02-09'),

    (23, 4, '2026-01-30'),

    (24, 1, '2026-02-16'),
    (24, 6, '2026-02-19'),

    (25, 3, '2026-01-17'),
    (25, 10, '2026-02-04'),

    (26, 2, '2026-01-24'),
    (26, 11, '2026-02-08'),

    (28, 5, '2026-01-16'),

    (29, 4, '2026-01-20'),
    (29, 9, '2026-02-06'),

    (30, 7, '2026-01-31'),
    (30, 8, '2026-02-13'),

    (31, 2, '2026-01-26'),

    (32, 1, '2026-01-18'),
    (32, 3, '2026-02-02'),
    (32, 6, '2026-02-18'),

    (33, 5, '2026-02-05'),

    (34, 4, '2026-01-29'),
    (34, 10, '2026-02-11'),

    (35, 8, '2026-01-14'),

    (36, 2, '2026-01-22'),
    (36, 7, '2026-02-01'),

    (37, 11, '2026-02-10'),

    (38, 3, '2026-01-15'),
    (38, 9, '2026-02-16'),

    (39, 6, '2026-01-12'),

    (40, 1, '2026-02-07'),
    (40, 5, '2026-02-19'),

    (41, 2, '2026-01-27'),
    (41, 10, '2026-02-15'),

    (42, 4, '2026-01-21'),

    (43, 8, '2026-01-25'),
    (43, 11, '2026-02-17'),

    (44, 7, '2026-02-06'),

    (45, 3, '2026-01-13'),
    (45, 5, '2026-02-10'),

    (46, 1, '2026-01-30'),

    (47, 9, '2026-02-04'),
    (47, 10, '2026-02-14'),

    (48, 2, '2026-01-18'),

    (49, 4, '2026-02-02'),
    (49, 8, '2026-02-09'),

    (50, 6, '2026-01-16'),

    (51, 3, '2026-01-28'),
    (51, 11, '2026-02-18'),

    (52, 5, '2026-01-20'),

    (53, 2, '2026-02-01'),
    (53, 7, '2026-02-12'),

    (55, 1, '2026-01-22'),
    (55, 9, '2026-02-11'),

    (57, 8, '2026-01-17'),

    (58, 4, '2026-02-08'),
    (58, 10, '2026-02-19'),

    (60, 3, '2026-01-24'),

    (61, 5, '2026-01-29'),
    (61, 6, '2026-02-16'),

    (63, 2, '2026-01-15'),

    (65, 7, '2026-01-28'),
    (65, 11, '2026-02-13'),

    (68, 1, '2026-02-05'),

    (70, 4, '2026-01-23'),
    (70, 8, '2026-02-07'),

    (72, 2, '2026-01-19'),
    (72, 9, '2026-02-17'),

    (75, 6, '2026-01-31'),

    (77, 3, '2026-02-02'),
    (77, 10, '2026-02-15'),

    (79, 5, '2026-01-26'),

    (80, 1, '2026-02-03'),
    (80, 11, '2026-02-18')
ON CONFLICT (estudiante_id, curso_id) DO NOTHING;