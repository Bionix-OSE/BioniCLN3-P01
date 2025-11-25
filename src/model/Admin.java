package model;

public class Admin {
    private String adminId;
    private String fullName;
    private String email;
    private String phone;
    private String department;
    private String username; // liên kết với account

    // Constructor đầy đủ
    public Admin(String adminId, String fullName, String email, String phone, String department, String username) {
        this.adminId = adminId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.username = username;
    }

    // Constructor rỗng (nếu cần)
    public Admin() {}

    // Getter & Setter
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Có thể override toString() nếu muốn hiển thị trong GUI
    @Override
    public String toString() {
        return fullName + " (" + adminId + ")";
    }
}
