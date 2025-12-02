package model;

public class Cat extends Pet {
    

    private String breed;

    public Cat() {
    }

    public Cat(int id, String name, int age, double price, String breed) {
        super(id, name, age, price, "cat");
        this.breed = breed;
    }
    
    @Override
    public String getBreed() {
        return breed;
    }
    
    @Override
    public void setBreed(String breed) {
        this.breed = breed;
    }
}