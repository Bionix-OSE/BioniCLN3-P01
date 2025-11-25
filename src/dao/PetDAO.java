package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Cat;
import model.Dog;
import model.Pet;

public class PetDAO {

    public void insert(Pet p) {
        String sql = "INSERT INTO pets(name, age, price, type, status) VALUES(?, ?, ?, ?, 'Available')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getName());
            stmt.setInt(2, p.getAge());
            stmt.setDouble(3, p.getPrice());
            stmt.setString(4, p.getType());
            stmt.executeUpdate();
        } catch (Exception e) { 
            e.printStackTrace(); 
            throw new RuntimeException("Failed to insert pet: " + e.getMessage(), e);
        }
    }

    // <-- BƯỚC 3: SỬA LẠI SQL ĐỂ CHỈ LẤY THÚ CƯNG CÒN HÀNG -->
    public ArrayList<Pet> getAll() {
        ArrayList<Pet> list = new ArrayList<>();
        // Chỉ lấy thú cưng có trạng thái 'Available'
        String sql = "SELECT * FROM pets WHERE status = 'Available'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double price = rs.getDouble("price");
                String type = rs.getString("type");

                Pet p = null;
                
                // [CHANGE] Logic Đa hình (Polymorphism) & Trừu tượng (Abstraction)
                // Dựa vào type để tạo ra đối tượng con cụ thể
                if ("dog".equalsIgnoreCase(type)) {
                    // Giả sử cột breed chưa có trong DB pets, ta để trống hoặc mặc định
                    p = new Dog(id, name, age, price, "Golden Retriever"); 
                } else if ("cat".equalsIgnoreCase(type)) {
                    // Giả sử cột color chưa có, để trống
                    p = new Cat(id, name, age, price, "White");
                } else {
                    // Trường hợp dữ liệu cũ không rõ loại, ta có thể bỏ qua hoặc gán Dog mặc định
                    // Nhưng KHÔNG ĐƯỢC gọi new Pet() nữa
                    p = new Dog(id, name, age, price, "Unknown");
                }

                if (p != null) {
                    list.add(p);
                }
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return list;
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete pet: " + e.getMessage(), e);
        }
    }
    
    // <-- BƯỚC 4: THÊM PHƯƠNG THỨC UPDATE TRẠNG THÁI -->
    public void updateStatus(int petId, String status) {
        String sql = "UPDATE pets SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, petId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update pet status: " + e.getMessage(), e);
        }
    }
}