package org.uned.practicatw.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

@WebListener
public class DatabaseInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

//        String host = System.getenv("DB_HOST");
//        String port = System.getenv("DB_PORT");
//        String name = System.getenv("DB_NAME");
//        String user = System.getenv("DB_USER");
//        String password = System.getenv("DB_PASSWORD");

        String host = "localhost";
        String port = "5432";
        String name = "practica";
        String user = "postgres";
        String password = "postgres";


        String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            ejecutarScript(conn, "/sql/data.sql");
        } catch (SQLException e) {
            throw new RuntimeException("Error inicializando la base de datos.", e);
        }
    }

    private void ejecutarScript(Connection conn, String script) throws SQLException {
        try (InputStream is = getClass().getResourceAsStream(script)) {
            if (is == null) throw new RuntimeException("No se encontró " + script);
            String sql = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
            try (Statement stmt = conn.createStatement()) {
                for (String s : sql.split(";")) {
                    stmt.execute(s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
