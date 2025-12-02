package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Order;

public class OrderDAO {

    public void insert(Order o) {
        String sql = "INSERT INTO orders(customer_id, product_id, pet_id, item_type, quantity, total) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, o.getCustomerId());
            if (o.getProductId() == 0) stmt.setNull(2, Types.INTEGER);
            else stmt.setInt(2, o.getProductId());
            if (o.getPetId() == 0) stmt.setNull(3, Types.INTEGER);
            else stmt.setInt(3, o.getPetId());
            stmt.setString(4, o.getItemType());
            stmt.setInt(5, o.getQuantity());
            stmt.setDouble(6, o.getTotal());
            stmt.executeUpdate();
        } catch (Exception e) { 
            e.printStackTrace();
            throw new RuntimeException("Failed to insert order: " + e.getMessage(), e);
        }
    }

    public ArrayList<Order> getAll() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRowToOrder(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public ArrayList<Order> getByCustomerId(int customerId) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRowToOrder(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete order: " + e.getMessage(), e);
        }
    }
    
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRowToOrder(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    
    private Order mapRowToOrder(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("id"),
                rs.getInt("customer_id"),
                rs.getInt("product_id"),
                rs.getInt("pet_id"),
                rs.getString("item_type"),
                rs.getInt("quantity"),
                rs.getDouble("total")
        );
    }
}