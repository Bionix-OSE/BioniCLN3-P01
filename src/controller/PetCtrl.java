package controller;

import java.util.ArrayList;

import dao.PetDAO;
import model.Cat;
import model.Dog;
import model.Pet;

public class PetCtrl {

    private PetDAO petDAO = new PetDAO();

    // [CHANGE] Sửa logic để tạo đối tượng cụ thể
    public void addPet(String name, int age, double price, String type) {
        Pet pet = null;
        
        if ("dog".equalsIgnoreCase(type)) {
            pet = new Dog(0, name, age, price, "N/A"); // Breed tạm thời chưa nhập
        } else if ("cat".equalsIgnoreCase(type)) {
            pet = new Cat(0, name, age, price, "N/A"); // Color tạm thời chưa nhập
        } else {
            // Mặc định nếu không phải dog/cat (ví dụ nhập sai)
            pet = new Dog(0, name, age, price, "Unknown");
        }
        
        // DAO sẽ nhận vào 'Pet' nhưng thực chất nó đang xử lý 'Dog' hoặc 'Cat' (Đa hình)
        petDAO.insert(pet);
    }

    public ArrayList<Pet> getAllPets() {
        return petDAO.getAll();
    }
    
    public void deletePet(int id) {
        petDAO.delete(id);
    }
    
    // <-- BƯỚC 5: THÊM PHƯƠNG THỨC GỌI UPDATE -->
    public void updatePetStatus(int petId, String status) {
        petDAO.updateStatus(petId, status);
    }
}