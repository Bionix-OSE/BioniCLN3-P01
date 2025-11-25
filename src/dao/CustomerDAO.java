package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Customer;

public class CustomerDAO {

    // Sửa đổi insert để ném lỗi (throw RuntimeException)
    public void insert(Customer c) {
        String sql = "INSERT INTO customers(name, phone, email, account_id) VALUES(?, ?, ?, ?)"; 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getName());
            stmt.setString(2, c.getPhone());
            stmt.setString(3, c.getEmail());
            
            if (c.getAccountId() == 0) {
                 stmt.setNull(4, Types.INTEGER);
            } else {
                 stmt.setInt(4, c.getAccountId());
            }
            stmt.executeUpdate();
        } catch (Exception e) { 
            e.printStackTrace(); 
            // NÉM LỖI RA ĐỂ BÁO CHO NGƯỜI DÙNG
            throw new RuntimeException("Failed to create customer profile: " + e.getMessage(), e); 
        }
    }

    // (Giữ nguyên các phương thức getAll và findByAccountId)
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("account_id")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public Customer findByAccountId(int accountId) {
        String sql = "SELECT * FROM customers WHERE account_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("account_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}