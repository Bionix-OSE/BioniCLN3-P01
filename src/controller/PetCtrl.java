package controller;

import java.util.ArrayList;

import dao.PetDAO;
import model.Cat;
import model.Dog;
import model.Pet;

public class PetCtrl {

    private PetDAO petDAO = new PetDAO();

    public void addPet(String name, int age, double price, String type, String extraInfo) {
        Pet pet = null;

        if ("dog".equalsIgnoreCase(type)) {
            pet = new Dog(0, name, age, price, extraInfo); 
        } else if ("cat".equalsIgnoreCase(type)) {
            pet = new Cat(0, name, age, price, extraInfo); 
        } else {
            System.out.println("Error: Invalid pet type: " + type);
        }

        if (pet != null) {
            petDAO.insert(pet);
        }
    }

    public ArrayList<Pet> getAllPets() {
        return petDAO.getAll();
    }
    
    public void deletePet(int id) {
        petDAO.delete(id);
    }
    
    public void updatePetStatus(int petId, String status) {
        petDAO.updateStatus(petId, status);
    }
}