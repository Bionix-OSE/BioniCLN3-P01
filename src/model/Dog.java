package model;

public class Dog extends Pet {
    // [REPORT] INHERITANCE (KẾ THỪA): Class Dog kế thừa từ class Pet (extends Pet)

    private String breed;

    public Dog() {
    }

    public Dog(int id, String name, int age, double price, String breed) {
        super(id, name, age, price, "dog"); // [REPORT] Gọi Constructor của lớp cha (Pet)
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
