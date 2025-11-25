package model;

public class Customer {

    private int id;
    private String name;
    private String phone;
    private String email;
    private int accountId; // <-- BẮT BUỘC CÓ DÒNG NÀY

    public Customer() {}

    public Customer(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    // BẮT BUỘC CÓ CONSTRUCTOR 5 THAM SỐ NÀY
    public Customer(int id, String name, String phone, String email, int accountId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accountId = accountId;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    // BẮT BUỘC CÓ GETTER/SETTER NÀY
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
}