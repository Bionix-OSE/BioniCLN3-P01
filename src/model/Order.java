package model;

public class Order {

    private int id;
    private int customerId;
    
    // Nâng cấp:
    private int productId; // Sẽ là 0 nếu mua pet
    private int petId;     // Sẽ là 0 nếu mua product
    private String itemType; // 'product' hoặc 'pet'
    
    private int quantity;
    private double total;

    public Order() {}

   

    // Constructor MỚI (đầy đủ)
    public Order(int id, int customerId, int productId, int petId, String itemType, int quantity, double total) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.petId = petId;
        this.itemType = itemType;
        this.quantity = quantity;
        this.total = total;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}