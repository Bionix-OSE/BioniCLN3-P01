package model;

public class Dog extends Pet {
    

    private String breed;

    public Dog() {
    }

    public Dog(int id, String name, int age, double price, String breed) {
        super(id, name, age, price, "dog"); 
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