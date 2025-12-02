package controller;

import java.util.ArrayList;

import dao.ProductDAO;
import model.Product;

public class ProductCtrl {

    private ProductDAO productDAO = new ProductDAO();
    
    
    public void addProduct(String name, double price, int quantity, String category) {
        Product p = new Product(0, name, price, quantity, category); 
        productDAO.insert(p);
    }

    public ArrayList<Product> getAllProducts() {
        return productDAO.getAll();
    }
    
    public void deleteProduct(int id) {
        productDAO.delete(id);
    }
    
    public void updateProductQuantity(int productId, int newQuantity) {
        productDAO.updateQuantity(productId, newQuantity);
    }
}