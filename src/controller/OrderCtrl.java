package controller;

import dao.OrderDAO;
import model.Order;
import model.Product;

import java.util.ArrayList;

public class OrderCtrl {
    

    private OrderDAO orderDAO = new OrderDAO();
    private ProductCtrl productController = new ProductCtrl();
    private PetCtrl petController = new PetCtrl();

    public void addProductOrder(int customerId, int productId, int quantity, double total) {
        Order o = new Order(0, customerId, productId, 0, "product", quantity, total);
        orderDAO.insert(o);
    }

    public void addPetOrder(int customerId, int petId, double total) {
        Order o = new Order(0, customerId, 0, petId, "pet", 1, total);
        orderDAO.insert(o);
    }

    public ArrayList<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    public ArrayList<Order> getOrdersByCustomerId(int customerId) {
        return orderDAO.getByCustomerId(customerId);
    }

    public void deleteOrder(int id) {
        Order order = orderDAO.getOrderById(id);
        if (order == null) {
            throw new RuntimeException("Order not found, cannot cancel.");
        }

        try {
            if ("product".equals(order.getItemType())) {
                int productId = order.getProductId();
                int canceledQuantity = order.getQuantity();

                int currentStock = 0;
                for (Product p : productController.getAllProducts()) {
                    if (p.getId() == productId) {
                        currentStock = p.getQuantity();
                        break;
                    }
                }

                int newStock = currentStock + canceledQuantity;
                productController.updateProductQuantity(productId, newStock);

            } else if ("pet".equals(order.getItemType())) {
                petController.updatePetStatus(order.getPetId(), "Available");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to restock item: " + e.getMessage(), e);
        }

        orderDAO.delete(id);
    }
}