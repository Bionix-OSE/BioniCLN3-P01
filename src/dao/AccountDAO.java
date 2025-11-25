package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Account;

public class AccountDAO {
    // [REPORT] DAO PATTERN (Data Access Object): Tách biệt logic truy cập dữ liệu
    // khỏi logic nghiệp vụ

    // Sửa đổi insert để ném lỗi (throw RuntimeException)
    public void insert(Account acc) {
        String sql = "INSERT INTO accounts(username,password,role) VALUES(?,?,?)";
        // [REPORT] Sử dụng try-with-resources để tự động đóng kết nối
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) { // [REPORT] PreparedStatement chống SQL Injection

            stmt.setString(1, acc.getUsername());
            stmt.setString(2, acc.getPassword());
            stmt.setString(3, acc.getRole());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // NÉM LỖI RA ĐỂ BÁO CHO NGƯỜI DÙNG
            throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
        }
    }

    // (Giữ nguyên các phương thức findByUsername và getAll)
    public Account findByUsername(String username) {
        String sql = "SELECT * FROM accounts WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Account> getAll() {
        ArrayList<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Account(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}