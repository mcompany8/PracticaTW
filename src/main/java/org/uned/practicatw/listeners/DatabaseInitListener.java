package org.uned.practicatw.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.uned.practicatw.dao.CursoDAOImpl;
import org.uned.practicatw.model.*;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.ServiceFactory;
import org.uned.practicatw.service.UsuarioService;
import org.uned.practicatw.utils.PasswordUtil;

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

public class DatabaseInitListener implements ServletContextListener {

    private UsuarioService usuarioService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        this.usuarioService = ServiceFactory.getUsuarioService();

        String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String name = System.getenv("DB_NAME");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;


        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            insertUsuarios();
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

    private void insertUsuarios() {

        // ===================== PROFESORES (4) =====================

        usuarioService.crear(
                Profesor.builder()
                        .email("mgarcia@dummy.es")
                        .nombre("Marta")
                        .apellidos("García Ruiz")
                        .direccion("Calle Alcalá 120")
                        .codigopostal("28009")
                        .poblacion("Madrid")
                        .provincia("Madrid")
                        .password(PasswordUtil.hashPassword("pass1"))
                        .build()
        );

        usuarioService.crear(
                Profesor.builder()
                        .email("jlopez@dummy.es")
                        .nombre("Javier")
                        .apellidos("López Martín")
                        .direccion("Avenida Diagonal 450")
                        .codigopostal("08013")
                        .poblacion("Barcelona")
                        .provincia("Barcelona")
                        .password(PasswordUtil.hashPassword("pass2"))
                        .build()
        );

        usuarioService.crear(
                Profesor.builder()
                        .email("crodriguez@dummy.es")
                        .nombre("Carmen")
                        .apellidos("Rodríguez Sánchez")
                        .direccion("Calle Colón 15")
                        .codigopostal("46004")
                        .poblacion("Valencia")
                        .provincia("Valencia")
                        .password(PasswordUtil.hashPassword("pass3"))
                        .build()
        );

        usuarioService.crear(
                Profesor.builder()
                        .email("dfernandez@dummy.es")
                        .nombre("David")
                        .apellidos("Fernández Gil")
                        .direccion("Calle Sierpes 8")
                        .codigopostal("41004")
                        .poblacion("Sevilla")
                        .provincia("Sevilla")
                        .password(PasswordUtil.hashPassword("pass4"))
                        .build()
        );

        // ===================== ADMINISTRADOR (1) =====================

        usuarioService.crear(
                Administrador.builder()
                        .email("admin@dummy.es")
                        .nombre("Laura")
                        .apellidos("Moreno Díaz")
                        .direccion("Calle Gran Vía 1")
                        .codigopostal("28013")
                        .poblacion("Madrid")
                        .provincia("Madrid")
                        .password(PasswordUtil.hashPassword("pass5"))
                        .build()
        );

        // ===================== ALUMNOS (40) =====================

        usuarioService.crear(
                Estudiante.builder()
                        .email("falvarez@dummy.es")
                        .nombre("Francisco")
                        .apellidos("Álvarez González")
                        .direccion("Calle Atocha 30")
                        .codigopostal("28001")
                        .poblacion("Madrid")
                        .provincia("Madrid")
                        .password(PasswordUtil.hashPassword("pass6"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("mgonzalez@dummy.es")
                        .nombre("María")
                        .apellidos("González Pérez")
                        .direccion("Calle Serrano 55")
                        .codigopostal("28006")
                        .poblacion("Madrid")
                        .provincia("Madrid")
                        .password(PasswordUtil.hashPassword("pass7"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("apaz@dummy.es")
                        .nombre("Antonio")
                        .apellidos("Paz Domínguez")
                        .direccion("Calle Larios 10")
                        .codigopostal("29005")
                        .poblacion("Málaga")
                        .provincia("Málaga")
                        .password(PasswordUtil.hashPassword("pass8"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("lmartinez@dummy.es")
                        .nombre("Lucía")
                        .apellidos("Martínez Ortega")
                        .direccion("Calle Preciados 22")
                        .codigopostal("28013")
                        .poblacion("Madrid")
                        .provincia("Madrid")
                        .password(PasswordUtil.hashPassword("pass9"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("jromero@dummy.es")
                        .nombre("Jorge")
                        .apellidos("Romero Castro")
                        .direccion("Rambla de Catalunya 90")
                        .codigopostal("08008")
                        .poblacion("Barcelona")
                        .provincia("Barcelona")
                        .password(PasswordUtil.hashPassword("pass10"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("srodriguez@dummy.es")
                        .nombre("Sara")
                        .apellidos("Rodríguez Vega")
                        .direccion("Calle Mayor 12")
                        .codigopostal("28013")
                        .poblacion("Madrid")
                        .provincia("Madrid")
                        .password(PasswordUtil.hashPassword("pass11"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("dsantos@dummy.es")
                        .nombre("Daniel")
                        .apellidos("Santos Iglesias")
                        .direccion("Calle del Franco 25")
                        .codigopostal("15702")
                        .poblacion("Santiago de Compostela")
                        .provincia("A Coruña")
                        .password(PasswordUtil.hashPassword("pass12"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("pnavarro@dummy.es")
                        .nombre("Paula")
                        .apellidos("Navarro Blanco")
                        .direccion("Calle Zaragoza 18")
                        .codigopostal("50001")
                        .poblacion("Zaragoza")
                        .provincia("Zaragoza")
                        .password(PasswordUtil.hashPassword("pass13"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("cortiz@dummy.es")
                        .nombre("Carlos")
                        .apellidos("Ortiz Serrano")
                        .direccion("Calle Ancha 5")
                        .codigopostal("39001")
                        .poblacion("Santander")
                        .provincia("Cantabria")
                        .password(PasswordUtil.hashPassword("pass14"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("ejimenez@dummy.es")
                        .nombre("Elena")
                        .apellidos("Jiménez Cano")
                        .direccion("Calle Betis 3")
                        .codigopostal("41010")
                        .poblacion("Sevilla")
                        .provincia("Sevilla")
                        .password(PasswordUtil.hashPassword("pass15"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("rgil@dummy.es")
                        .nombre("Raúl")
                        .apellidos("Gil Herrera")
                        .direccion("Calle Alfonso XIII 8")
                        .codigopostal("30001")
                        .poblacion("Murcia")
                        .provincia("Murcia")
                        .password(PasswordUtil.hashPassword("pass16"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("ncastro@dummy.es")
                        .nombre("Nuria")
                        .apellidos("Castro Ramos")
                        .direccion("Calle Tetuán 40")
                        .codigopostal("41001")
                        .poblacion("Sevilla")
                        .provincia("Sevilla")
                        .password(PasswordUtil.hashPassword("pass17"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("amoreno@dummy.es")
                        .nombre("Alejandro")
                        .apellidos("Moreno Delgado")
                        .direccion("Calle San Fernando 14")
                        .codigopostal("11004")
                        .poblacion("Cádiz")
                        .provincia("Cádiz")
                        .password(PasswordUtil.hashPassword("pass18"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("irubio@dummy.es")
                        .nombre("Irene")
                        .apellidos("Rubio Peña")
                        .direccion("Calle Compañía 2")
                        .codigopostal("18001")
                        .poblacion("Granada")
                        .provincia("Granada")
                        .password(PasswordUtil.hashPassword("pass19"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("pmolina@dummy.es")
                        .nombre("Pablo")
                        .apellidos("Molina Cortés")
                        .direccion("Calle Tendillas 6")
                        .codigopostal("14001")
                        .poblacion("Córdoba")
                        .provincia("Córdoba")
                        .password(PasswordUtil.hashPassword("pass20"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("cvazquez@dummy.es")
                        .nombre("Cristina")
                        .apellidos("Vázquez Lorenzo")
                        .direccion("Rúa do Vilar 20")
                        .codigopostal("15705")
                        .poblacion("Santiago de Compostela")
                        .provincia("A Coruña")
                        .password(PasswordUtil.hashPassword("pass21"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("sherrera@dummy.es")
                        .nombre("Sergio")
                        .apellidos("Herrera Campos")
                        .direccion("Calle Laurel 9")
                        .codigopostal("03001")
                        .poblacion("Alicante")
                        .provincia("Alicante")
                        .password(PasswordUtil.hashPassword("pass22"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("bdiaz@dummy.es")
                        .nombre("Beatriz")
                        .apellidos("Díaz Aguilar")
                        .direccion("Calle Colón 12")
                        .codigopostal("46001")
                        .poblacion("Valencia")
                        .provincia("Valencia")
                        .password(PasswordUtil.hashPassword("pass23"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("vsanz@dummy.es")
                        .nombre("Víctor")
                        .apellidos("Sanz Guerrero")
                        .direccion("Calle Espoz y Mina 3")
                        .codigopostal("01001")
                        .poblacion("Vitoria-Gasteiz")
                        .provincia("Álava")
                        .password(PasswordUtil.hashPassword("pass24"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("adominguez@dummy.es")
                        .nombre("Ana")
                        .apellidos("Domínguez Vidal")
                        .direccion("Calle Espartero 20")
                        .codigopostal("48005")
                        .poblacion("Bilbao")
                        .provincia("Vizcaya")
                        .password(PasswordUtil.hashPassword("pass25"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("mmendez@dummy.es")
                        .nombre("Miguel")
                        .apellidos("Méndez Soto")
                        .direccion("Calle Real 8")
                        .codigopostal("33001")
                        .poblacion("Oviedo")
                        .provincia("Asturias")
                        .password(PasswordUtil.hashPassword("pass26"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("lguerra@dummy.es")
                        .nombre("Laura")
                        .apellidos("Guerra Ferrer")
                        .direccion("Calle Uría 15")
                        .codigopostal("33003")
                        .poblacion("Oviedo")
                        .provincia("Asturias")
                        .password(PasswordUtil.hashPassword("pass27"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("fnunez@dummy.es")
                        .nombre("Fernando")
                        .apellidos("Núñez Cabrera")
                        .direccion("Calle Triana 11")
                        .codigopostal("38001")
                        .poblacion("Santa Cruz de Tenerife")
                        .provincia("Santa Cruz de Tenerife")
                        .password(PasswordUtil.hashPassword("pass28"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("epascual@dummy.es")
                        .nombre("Eva")
                        .apellidos("Pascual Reyes")
                        .direccion("Calle Triana 25")
                        .codigopostal("35001")
                        .poblacion("Las Palmas de Gran Canaria")
                        .provincia("Las Palmas")
                        .password(PasswordUtil.hashPassword("pass29"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("ivarela@dummy.es")
                        .nombre("Iván")
                        .apellidos("Varela Pastor")
                        .direccion("Calle del Sol 4")
                        .codigopostal("47001")
                        .poblacion("Valladolid")
                        .provincia("Valladolid")
                        .password(PasswordUtil.hashPassword("pass30"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("mvidal@dummy.es")
                        .nombre("Marina")
                        .apellidos("Vidal Crespo")
                        .direccion("Calle Duque de la Victoria 9")
                        .codigopostal("47001")
                        .poblacion("Valladolid")
                        .provincia("Valladolid")
                        .password(PasswordUtil.hashPassword("pass31"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("hportillo@dummy.es")
                        .nombre("Hugo")
                        .apellidos("Portillo Marín")
                        .direccion("Calle San Bernardo 30")
                        .codigopostal("06001")
                        .poblacion("Badajoz")
                        .provincia("Badajoz")
                        .password(PasswordUtil.hashPassword("pass32"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("cbravo@dummy.es")
                        .nombre("Claudia")
                        .apellidos("Bravo Mora")
                        .direccion("Calle Menéndez Pelayo 5")
                        .codigopostal("39002")
                        .poblacion("Santander")
                        .provincia("Cantabria")
                        .password(PasswordUtil.hashPassword("pass33"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("agarrido@dummy.es")
                        .nombre("Andrés")
                        .apellidos("Garrido Nieto")
                        .direccion("Calle Nueva 12")
                        .codigopostal("13001")
                        .poblacion("Ciudad Real")
                        .provincia("Ciudad Real")
                        .password(PasswordUtil.hashPassword("pass34"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("nleon@dummy.es")
                        .nombre("Noelia")
                        .apellidos("León Salas")
                        .direccion("Calle Real 40")
                        .codigopostal("13001")
                        .poblacion("Ciudad Real")
                        .provincia("Ciudad Real")
                        .password(PasswordUtil.hashPassword("pass35"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("orios@dummy.es")
                        .nombre("Óscar")
                        .apellidos("Ríos Peral")
                        .direccion("Calle Zurbano 2")
                        .codigopostal("06001")
                        .poblacion("Badajoz")
                        .provincia("Badajoz")
                        .password(PasswordUtil.hashPassword("pass36"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("ealonso@dummy.es")
                        .nombre("Elisa")
                        .apellidos("Alonso Duarte")
                        .direccion("Calle Independencia 18")
                        .codigopostal("50004")
                        .poblacion("Zaragoza")
                        .provincia("Zaragoza")
                        .password(PasswordUtil.hashPassword("pass37"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("jbermudez@dummy.es")
                        .nombre("Juan")
                        .apellidos("Bermúdez Cuesta")
                        .direccion("Calle Mayor 7")
                        .codigopostal("24001")
                        .poblacion("León")
                        .provincia("León")
                        .password(PasswordUtil.hashPassword("pass38"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("tarroyo@dummy.es")
                        .nombre("Teresa")
                        .apellidos("Arroyo Pardo")
                        .direccion("Calle Ancha 15")
                        .codigopostal("24003")
                        .poblacion("León")
                        .provincia("León")
                        .password(PasswordUtil.hashPassword("pass39"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("gramirez@dummy.es")
                        .nombre("Gonzalo")
                        .apellidos("Ramírez Escudero")
                        .direccion("Calle Corredera 5")
                        .codigopostal("06002")
                        .poblacion("Badajoz")
                        .provincia("Badajoz")
                        .password(PasswordUtil.hashPassword("pass40"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("vsuarez@dummy.es")
                        .nombre("Verónica")
                        .apellidos("Suárez Andrade")
                        .direccion("Calle Uría 30")
                        .codigopostal("33004")
                        .poblacion("Oviedo")
                        .provincia("Asturias")
                        .password(PasswordUtil.hashPassword("pass41"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("mgallardo@dummy.es")
                        .nombre("Manuel")
                        .apellidos("Gallardo Vargas")
                        .direccion("Calle Feria 20")
                        .codigopostal("41003")
                        .poblacion("Sevilla")
                        .provincia("Sevilla")
                        .password(PasswordUtil.hashPassword("pass42"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("ysoto@dummy.es")
                        .nombre("Yolanda")
                        .apellidos("Soto Contreras")
                        .direccion("Calle Betis 12")
                        .codigopostal("41010")
                        .poblacion("Sevilla")
                        .provincia("Sevilla")
                        .password(PasswordUtil.hashPassword("pass43"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("rfuentes@dummy.es")
                        .nombre("Rubén")
                        .apellidos("Fuentes Márquez")
                        .direccion("Calle Larios 22")
                        .codigopostal("29005")
                        .poblacion("Málaga")
                        .provincia("Málaga")
                        .password(PasswordUtil.hashPassword("pass44"))
                        .build()
        );

        usuarioService.crear(
                Estudiante.builder()
                        .email("ptorres@dummy.es")
                        .nombre("Paloma")
                        .apellidos("Torres Prieto")
                        .direccion("Calle Compañía 8")
                        .codigopostal("18002")
                        .poblacion("Granada")
                        .provincia("Granada")
                        .password(PasswordUtil.hashPassword("pass45"))
                        .build()
        );
    }
}
