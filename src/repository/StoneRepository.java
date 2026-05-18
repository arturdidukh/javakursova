package repository;

import model.Stone;
import model.PreciousStone;
import model.SemiPreciousStone;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StoneRepository {

    private void log(String level, String message) {
        try (FileWriter fw = new FileWriter("app.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            pw.println(time + " [" + level + "] " + message);
        } catch (Exception e) {
            System.err.println("Помилка логування: " + e.getMessage());
        }
    }

    public void save(Stone stone) {
        String sql = "INSERT INTO stones (name, weight, price, transparency, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, stone.getName());
            pstmt.setDouble(2, stone.getCaratWeight());
            pstmt.setDouble(3, stone.getPricePerCarat() * stone.getCaratWeight());
            pstmt.setInt(4, stone.getTransparency());
            pstmt.setString(5, stone.getClass().getSimpleName());

            pstmt.executeUpdate();
            log("INFO", "Збережено камінь: " + stone.getName());
        } catch (SQLException e) {
            log("ERROR", "Помилка збереження: " + e.getMessage());
        }
    }

    public List<Stone> findAll() {
        List<Stone> stones = new ArrayList<>();
        String sql = "SELECT * FROM stones";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                double weight = rs.getDouble("weight");
                double totalPrice = rs.getDouble("price");
                int transparency = rs.getInt("transparency");
                String type = rs.getString("type");
                double pricePerCarat = totalPrice / weight;

                if ("PreciousStone".equals(type)) {
                    stones.add(new PreciousStone(name, weight, pricePerCarat, transparency));
                } else {
                    stones.add(new SemiPreciousStone(name, weight, pricePerCarat, transparency));
                }
            }
            log("INFO", "Завантажено каменів: " + stones.size());
        } catch (SQLException e) {
            log("ERROR", "Помилка завантаження: " + e.getMessage());
        }
        return stones;
    }

    public void deleteAll() {
        String sql = "DELETE FROM stones";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            log("WARN", "БАЗУ ОЧИЩЕНО");
        } catch (SQLException e) {
            log("ERROR", "Помилка видалення: " + e.getMessage());
        }
    }
}