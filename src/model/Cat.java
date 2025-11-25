package model;

public class Cat extends Pet {
    // [REPORT] INHERITANCE (KẾ THỪA): Class Cat kế thừa từ class Pet

    private String color;

    public Cat() {
    }

    public Cat(int id, String name, int age, double price, String color) {
        super(id, name, age, price, "cat"); // [REPORT] Gọi Constructor của lớp cha
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
