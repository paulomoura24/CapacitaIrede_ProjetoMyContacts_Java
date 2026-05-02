package com.mycompany.mycontacts.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static String databaseUrl = "";
    private static String databaseUser = "";
    private static String databasePassword = "";

    static {
        DatabaseConfig config = DatabaseConfig.load();
        databaseUrl = config.getUrl();
        databaseUser = config.getUser();
        databasePassword = config.getPassword();
    }

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (databaseUser.isBlank() && databasePassword.isBlank()) {
            return DriverManager.getConnection(databaseUrl);
        }
        return DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
    }

    public static void setDatabaseUrl(String url) {
        setDatabaseUrl(url, "", "");
    }

    public static void setDatabaseUrl(String url, String user, String password) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL de conexão não pode ser vazia.");
        }
        databaseUrl = url;
        databaseUser = user == null ? "" : user;
        databasePassword = password == null ? "" : password;
    }

    public static boolean isMySql() {
        return databaseUrl.startsWith("jdbc:mysql:");
    }
}

