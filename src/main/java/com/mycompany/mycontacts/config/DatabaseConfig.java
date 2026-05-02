package com.mycompany.mycontacts.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private final String url;
    private final String user;
    private final String password;

    private DatabaseConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DatabaseConfig load() {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConfig.class.getResourceAsStream("/db.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException ignored) {
            // Usar valores padrão se não houver arquivo de configuração.
        }

        String url = properties.getProperty("db.url", "jdbc:mysql://localhost:3306/mycontacts");
        String user = properties.getProperty("db.user", "root");
        String password = properties.getProperty("db.password", "");

        String envUrl = System.getenv("MYCONTACTS_DB_URL");
        String envUser = System.getenv("MYCONTACTS_DB_USER");
        String envPassword = System.getenv("MYCONTACTS_DB_PASSWORD");

        if (envUrl != null && !envUrl.isBlank()) {
            url = envUrl;
        }
        if (envUser != null && !envUser.isBlank()) {
            user = envUser;
        }
        if (envPassword != null) {
            password = envPassword;
        }

        return new DatabaseConfig(url, user, password);
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

