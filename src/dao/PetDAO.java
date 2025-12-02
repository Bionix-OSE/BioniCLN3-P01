package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Cat;
import model.Dog;
import model.Pet;

public class PetDAO {

        public void insert(Pet t) {
        
        String sql = "INSERT INTO pets (name, age, price, type, breed, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getName());
            pstmt.setInt(2, t.getAge());
            pstmt.setDouble(3, t.getPrice());
            pstmt.setString(4, t.getType());

            
            if (t instanceof Dog) {
                pstmt.setString(5, ((Dog) t).getBreed());
            } else if (t instanceof Cat) {
                
                pstmt.setString(5, ((Cat) t).getBreed());
            } else {
                pstmt.setString(5, "Unknown");
            }

            pstmt.setString(6, "Available");

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pet> getAll() {
    ArrayList<Pet> list = new ArrayList<>();
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
            
            String breedInfo = rs.getString("breed"); 
            
            if (breedInfo == null) breedInfo = "Unknown";

            Pet p = null;
            
            if ("dog".equalsIgnoreCase(type)) {
                p = new Dog(id, name, age, price, breedInfo); 
            } else if ("cat".equalsIgnoreCase(type)) {
                
                p = new Cat(id, name, age, price, breedInfo);
            } else {
                p = new Dog(id, name, age, price, "Unknown");
            }

            if (p != null) {
                p.setStatus(rs.getString("status"));
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
    
    public String getNameById(int id) {
        String name = "Unknown Pet";
        String sql = "SELECT name FROM pets WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
}