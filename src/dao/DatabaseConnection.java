package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    // [REPORT] DATABASE CONNECTION: Class quản lý kết nối tới CSDL MySQL

    private static final String URL = "jdbc:mysql://localhost:3306/petshop_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // [REPORT] Static Method: Phương thức tĩnh để lấy kết nối dùng chung
    // (Singleton-like)
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}
