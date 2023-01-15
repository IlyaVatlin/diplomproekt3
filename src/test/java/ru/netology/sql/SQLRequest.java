package ru.netology.sql;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;


public class SQLRequest {
    private static final String DB_URL = System.getProperty("datasource.url");

    private SQLRequest() {
    }

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(DB_URL, "app", "pass");
    }

    @SneakyThrows
    public static void clearDB() {
        QueryRunner runner = new QueryRunner();
        try (var connection = getConnection()) {
            runner.execute(connection, "DELETE FROM credit_request_entity");
            runner.execute(connection, "DELETE FROM order_entity");
            runner.execute(connection, "DELETE FROM payment_entity");
        }
    }

    @SneakyThrows
    public static String getDebitPaymentStatus() {
        QueryRunner runner = new QueryRunner();
        String status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var connection = getConnection()) {
            String result = runner.query(connection, status, new ScalarHandler<>());
            return result;
        }
    }

    @SneakyThrows
    public static String getCreditPaymentStatus() {
        QueryRunner runner = new QueryRunner();
        String status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var connection = getConnection()) {
            String result = runner.query(connection, status, new ScalarHandler<>());
            return result;
        }
    }
}
