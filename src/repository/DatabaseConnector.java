package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static final String URL = "jdbc:sqlite:stones.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер SQLite не знайдено!");
        }

        String url = "jdbc:sqlite:stones.db";
        return DriverManager.getConnection(url);
    }

    public static void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");

            String sql = "CREATE TABLE IF NOT EXISTS stones (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "weight REAL," +
                    "price REAL," +
                    "transparency REAL," +
                    "type TEXT" +
                    ");";

            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("--- Система: База даних успішно ініціалізована ---");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер SQLite не знайдено в бібліотеках (lib): " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Помилка ініціалізації бази даних: " + e.getMessage());
        }
    }
}