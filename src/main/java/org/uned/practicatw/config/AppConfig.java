package org.uned.practicatw.config;

import java.nio.file.Path;

public final class AppConfig {

    public static final Path UPLOAD_DIR = Path.of(System.getProperty("java.io.tmpdir")).resolve("practicatw");
    public static final Path CONTENIDO_DIR = UPLOAD_DIR.resolve("contenido");
    public static final Path IMAGENES_DIR = UPLOAD_DIR.resolve("imagenes");
    public static final Path DESCRIPCIONES_DIR = UPLOAD_DIR.resolve("descripciones");

    private AppConfig(){}

}
