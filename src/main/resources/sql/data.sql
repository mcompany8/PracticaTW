-- =========================
-- CURSOS
-- =========================
INSERT INTO cursos (id, titulo, descripcion, nivel, duracion_horas, responsable_id, imagen)
VALUES (1, 'Fundamentos de Programación',
        'Introducción a los conceptos básicos de programación: variables, tipos de datos, condicionales y bucles.',
        'BASICO', 20, 1, '01_fundamentos_de_programacion.jpeg'),
       (2, 'Lógica de Programación y Algoritmos',
        'Desarrollo del pensamiento algorítmico mediante la resolución de problemas paso a paso.', 'BASICO', 25, 1,
        '02_logica_de_programacion_y_algoritmos.jpeg'),
       (3, 'Bases de Datos con SQL',
        'Diseño de esquemas relacionales y consultas SQL desde cero hasta nivel intermedio.', 'BASICO', 30, 2,
        '03_bases_de_datos_con_sql.jpeg'),
       (4, 'Desarrollo Web HTML, CSS y JavaScript',
        'Construcción de páginas web interactivas usando el trío clásico de tecnologías frontend.', 'BASICO', 35, 3,
        '04_desarrollo_web_html_css_y_javascript.jpeg'),
       (5, 'Desarrollo Web con React.js',
        'Creación de interfaces de usuario modernas y reactivas con la librería React.', 'INTERMEDIO', 40, 3,
        '05_desarrollo_web_con_react_js.jpeg'),
       (6, 'Backend con Node.js y Express', 'Construcción de servidores y APIs con Node.js y el framework Express.',
        'INTERMEDIO', 35, 3, '06_backend_con_node_js_y_express.jpeg'),
       (7, 'APIs RESTful con Node.js',
        'Diseño e implementación de APIs REST siguiendo buenas prácticas de arquitectura.', 'INTERMEDIO', 30, 5,
        '07_apis_restful_con_node_js.jpeg'),
       (8, 'Control de Versiones con Git y GitHub',
        'Uso de Git para el control de versiones y colaboración en proyectos mediante GitHub.', 'BASICO', 15, 4,
        '08_control_de_versiones_con_git_y_github.jpeg'),
       (9, 'Pruebas de Software con Jest',
        'Escritura de tests unitarios y de integración en JavaScript con el framework Jest.', 'INTERMEDIO', 20, 4,
        '09_pruebas_de_software_con_jest.jpeg'),
       (10, 'Docker y Contenedores', 'Empaquetado y despliegue de aplicaciones mediante contenedores Docker.',
        'INTERMEDIO', 25, 4, '10_docker_y_contenedores.jpeg'),
       (11, 'Estructuras de Datos Avanzadas',
        'Estudio de árboles, grafos y estructuras de datos avanzadas para resolver problemas complejos.', 'AVANZADO',
        40, 1, '11_estructuras_de_datos_avanzadas.jpeg')
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
VALUES (1, 1),
       (2, 1),
       (3, 3),
       (4, 2),
       (5, 2),
       (6, 2),
       (6, 1),
       (7, 2),
       (7, 1),
       (8, 1),
       (8, 4),
       (9, 1),
       (9, 2),
       (10, 4),
       (11, 1),
       (11, 3)
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
VALUES (6, 3, '2026-01-12'),
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

INSERT INTO contenidos (curso_id, uri, titulo, orden, fecha_subida)
VALUES (1, 'FP00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (1, 'FP01_Variables y tipos de datos.pdf', 'Variables y tipos de datos', 2, '2026-09-01'),
       (1, 'FP02_Operadores y expresiones.pdf', 'Operadores y tipos de expresiones', 3, '2026-09-01'),
       (1, 'FP03_Condicionales.pdf', 'Condicionales', 4, '2026-09-01'),
       (1, 'FP04_Bucles.pdf', 'Bucles', 5, '2026-09-01'),
       (1, 'FP05_Funciones.pdf', 'Funciones', 6, '2026-09-01'),
       (1, 'FP06_Depuración básica.pdf', 'Depuración básica', 7, '2026-09-01'),
       (2, 'LP00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (2, 'LP01_Descomposición de problemas.pdf', 'Descomposición de problemas', 2, '2026-09-01'),
       (2, 'LP02_Pseudocódigo.pdf', 'Pseudocódigo', 3, '2026-09-01'),
       (2, 'LP03_Diagramas de flujo.pdf', 'Diagramas de flujo', 4, '2026-09-01'),
       (2, 'LP04_Estructuras de control.pdf', 'Estructuras de control', 5, '2026-09-01'),
       (2, 'LP05_Modularización.pdf', 'Modularización', 6, '2026-09-01'),
       (2, 'LP06_Eficiencia básica.pdf', 'Eficiencia básica', 7, '2026-09-01'),
       (3, 'BD00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (3, 'BD01_Tablas, claves y restricciones.pdf', 'Tablas, claves y restricciones', 2, '2026-09-01'),
       (3, 'BD02_Normalización.pdf', 'Normalización', 3, '2026-09-01'),
       (3, 'BD03_SELECT.pdf', 'SELECT', 4, '2026-09-01'),
       (3, 'BD04_Filtros.pdf', 'Filtros', 5, '2026-09-01'),
       (3, 'BD05_Agregaciones.pdf', 'Agregaciones', 6, '2026-09-01'),
       (3, 'BD06_Joins.pdf', 'Joins', 7, '2026-09-01'),
       (3, 'BD07_Subconsultas.pdf', 'Subconsultas', 8, '2026-09-01'),
       (3, 'BD08_Operaciones CRUD.pdf', 'Operaciones CRUD', 9, '2026-09-01'),
       (4, 'HCJ00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (4, 'HCJ01_HTML semántico.pdf', 'HTML semántico', 2, '2026-09-01'),
       (4, 'HCJ02_CSS, Flexbox y Grid.pdf', 'CSS, Flebox y Grid', 3, '2026-09-01'),
       (4, 'HCJ03_Diseño responsive.pdf', 'Diseño responsive', 4, '2026-09-01'),
       (4, 'HCJ04_JavaScript.pdf', 'JavaScript', 5, '2026-09-01'),
       (4, 'HCJ05_DOM.pdf', 'DOM', 6, '2026-09-01'),
       (4, 'HCJ06_Eventos.pdf', 'Eventos', 7, '2026-09-01'),
       (4, 'HCJ07_Formularios.pdf', 'Formularios', 8, '2026-09-01'),
       (4, 'HCJ08_Consumo básico de APIs.pdf', 'Consumo básico de APIs', 9, '2026-09-01'),
       (5, 'DWR00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (5, 'DWR01_JSX.pdf', 'JSX', 2, '2026-09-01'),
       (5, 'DWR02_Componentes y props.pdf', 'Componentes y props', 3, '2026-09-01'),
       (5, 'DWR03_Estado.pdf', 'Estado', 4, '2026-09-01'),
       (5, 'DWR04_Eventos.pdf', 'Eventos', 5, '2026-09-01'),
       (5, 'DWR05_Hooks.pdf', 'Hooks', 6, '2026-09-01'),
       (5, 'DWR06_Formularios.pdf', 'Formularios', 7, '2026-09-01'),
       (5, 'DWR07_Listas.pdf', 'Listas', 8, '2026-09-01'),
       (5, 'DWR08_Navegación.pdf', 'Navegación', 9, '2026-09-01'),
       (5, 'DWR09_Consumo de APIs.pdf', 'Consumo de APIs', 10, '2026-09-01'),
       (5, 'DWR10_Arquitectura frontend.pdf', 'Arquitectura frontend', 11, '2026-09-01'),
       (6, 'BNE00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (6, 'BNE01_Node.js y módulos.pdf', 'Node.js y módulos', 2, '2026-09-01'),
       (6, 'BNE02_Asincronía.pdf', 'Asincronía', 3, '2026-09-01'),
       (6, 'BNE03_Express.pdf', 'Express', 4, '2026-09-01'),
       (6, 'BNE04_Rutas.pdf', 'Rutas', 5, '2026-09-01'),
       (6, 'BNE05_Middleware.pdf', 'Middleware', 6, '2026-09-01'),
       (6, 'BNE06_Controladores.pdf', 'Controladores', 7, '2026-09-01'),
       (6, 'BNE07_Validación.pdf', 'Validación', 8, '2026-09-01'),
       (6, 'BNE08_Errores.pdf', 'Errores', 9, '2026-09-01'),
       (6, 'BNE09_Configuración.pdf', 'Configuración', 10, '2026-09-01'),
       (6, 'BNE10_Persistencia.pdf', 'Persistencia', 11, '2026-09-01'),
       (7, 'ARN00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (7, 'ARN01_Recursos y endpoints.pdf', 'Recursos y endpoints', 2, '2026-09-01'),
       (7, 'ARN02_Métodos HTTP.pdf', 'Métodos HTTP', 3, '2026-09-01'),
       (7, 'ARN03_Códigos de estado.pdf', 'Códigos de estado', 4, '2026-09-01'),
       (7, 'ARN04_JSON.pdf', 'JSON', 5, '2026-09-01'),
       (7, 'ARN05_Validación.pdf', 'Validación', 6, '2026-09-01'),
       (7, 'ARN06_Paginación.pdf', 'Paginación', 7, '2026-09-01'),
       (7, 'ARN07_Filtros.pdf', 'Filtros', 8, '2026-09-01'),
       (7, 'ARN08_Autenticación.pdf', 'Autenticación', 9, '2026-09-01'),
       (7, 'ARN09_Documentación.pdf', 'Documentación', 10, '2026-09-01'),
       (8, 'CV00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (8, 'CV01_Repositorios.pdf', 'Repositorios', 2, '2026-09-01'),
       (8, 'CV02_Staging.pdf', 'Staging', 3, '2026-09-01'),
       (8, 'CV03_Commits.pdf', 'Commits', 4, '2026-09-01'),
       (8, 'CV04_Ramas.pdf', 'Ramas', 5, '2026-09-01'),
       (8, 'CV05_Merge y rebase.pdf', 'Merge y rebase', 6, '2026-09-01'),
       (8, 'CV06_Conflictos.pdf', 'Conflictos', 7, '2026-09-01'),
       (8, 'CV07_Remotos.pdf', 'Remotos', 8, '2026-09-01'),
       (8, 'CV08_Pull requests.pdf', 'Pull requests', 9, '2026-09-01'),
       (8, 'CV09_Revisión de código.pdf', 'Revisión de código', 10, '2026-09-01'),
       (9, 'PSJ00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (9, 'PSJ01_Pruebas unitarias e integración.pdf', 'Pruebas unitarias e integración', 2, '2026-09-01'),
       (9, 'PSJ02_Suites.pdf', 'Suites', 3, '2026-09-01'),
       (9, 'PSJ03_Aserciones.pdf', 'Aserciones', 4, '2026-09-01'),
       (9, 'PSJ04_Preparación.pdf', 'Preparación', 5, '2026-09-01'),
       (9, 'PSJ05_Mocks y spies.pdf', 'Mocks y spies', 6, '2026-09-01'),
       (9, 'PSJ06_Asincronía.pdf', 'Asincronía', 7, '2026-09-01'),
       (9, 'PSJ07_Cobertura.pdf', 'Cobertura', 8, '2026-09-01'),
       (9, 'PSJ08_Organización.pdf', 'Organización', 9, '2026-09-01'),
       (10, 'DC00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (10, 'DC01_Imágenes y contenedores.pdf', 'Imágenes y contenedores', 2, '2026-09-01'),
       (10, 'DC02_Dockerfile.pdf', 'Dockerfile', 3, '2026-09-01'),
       (10, 'DC03_Capas.pdf', 'Capas', 4, '2026-09-01'),
       (10, 'DC04_Volúmenes.pdf', 'Volúmenes', 5, '2026-09-01'),
       (10, 'DC05_Redes.pdf', 'Redes', 6, '2026-09-01'),
       (10, 'DC06_Variables.pdf', 'Variables', 7, '2026-09-01'),
       (10, 'DC07_Registros.pdf', 'Registros', 8, '2026-09-01'),
       (10, 'DC08_Docker Compose.pdf', 'Docker Compose', 9, '2026-09-01'),
       (10, 'DC09_Seguridad básica.pdf', 'Seguridad básica', 10, '2026-09-01'),
       (11, 'EDA00_Guía docente.pdf', 'Guía docente', 1, '2026-09-01'),
       (11, 'EDA01_Árboles.pdf', 'Árboles', 2, '2026-09-01'),
       (11, 'EDA02_Heaps.pdf', 'Heaps', 3, '2026-09-01'),
       (11, 'EDA03_Tablas hash.pdf', 'Tablas hash', 4, '2026-09-01'),
       (11, 'EDA04_Grafos.pdf', 'Grafos', 5, '2026-09-01'),
       (11, 'EDA05_Recorridos.pdf', 'Recorridos', 6, '2026-09-01'),
       (11, 'EDA06_Búsquedas.pdf', 'Búsquedas', 7, '2026-09-01'),
       (11, 'EDA07_Complejidad temporal y espacial.pdf', 'Complejidad temporal y espacial', 8, '2026-09-01'),
       (11, 'EDA08_Criterios de selección.pdf', 'Criterios de selección', 9, '2026-09-01')