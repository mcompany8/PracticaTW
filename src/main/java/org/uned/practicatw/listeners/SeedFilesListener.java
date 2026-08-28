package org.uned.practicatw.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;
import org.uned.practicatw.config.AppConfig;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


@Slf4j
public class SeedFilesListener implements ServletContextListener {

    /**
     * @param sce the ServletContextEvent containing the ServletContext that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {

            URL url = getClass().getClassLoader().getResource("files");
            System.out.println("URL del recurso 'files': " + url);
            if (url == null) return;

            Path origenDir = Path.of(url.toURI());
            Path destinoDir = Path.of(AppConfig.UPLOAD_DIR);
            Files.createDirectories(destinoDir);

            try (Stream<Path> stream = Files.list(origenDir)) {
                var archivos = stream.filter(Files::isRegularFile).toList();
                System.out.println("Ficheros encontrados: " + archivos.size());
                for (Path origen : archivos){
                    Path destino = destinoDir.resolve(origen.getFileName());
                    Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    log.info("Copiado seed file: ");

                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param sce the ServletContextEvent containing the ServletContext that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
