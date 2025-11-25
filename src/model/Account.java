package model;

public class Account {
    // [REPORT] ENCAPSULATION (ĐÓNG GÓI): Class Account sử dụng các thuộc tính
    // private để bảo vệ dữ liệu

    // Các thuộc tính private (Che giấu dữ liệu)
    private int id;
    private String username;
    private String password;
    private String role; // admin / customer

    public Account() {
    }

    public Account(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // [REPORT] Public Getters & Setters (Truy cập dữ liệu an toàn)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
