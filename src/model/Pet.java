package model;

public abstract class Pet {
    
    protected int id;
    protected String name;
    protected int age;
    protected double price;
    protected String type;
    protected String status;
    public Pet() {
    }

    public Pet(int id, String name, int age, double price, String type) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.price = price;
        this.type = type;
        this.status = "Available";
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public abstract String getBreed();
    
    public abstract void setBreed(String breed);
}