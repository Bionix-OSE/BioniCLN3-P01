package controller;

import dao.OrderDAO;
import model.Order;
import model.Product;

import java.util.ArrayList;

public class OrderCtrl {
    // [REPORT] CONTROLLER LAYER: Điều phối logic đặt hàng và xử lý nghiệp vụ

    private OrderDAO orderDAO = new OrderDAO();
    private ProductCtrl productController = new ProductCtrl();
    private PetCtrl petController = new PetCtrl();

    // (Các phương thức add...() giữ nguyên)
    public void addProductOrder(int customerId, int productId, int quantity, double total) {
        Order o = new Order(0, customerId, productId, 0, "product", quantity, total);
        orderDAO.insert(o); // [REPORT] Gọi DAO để lưu xuống database
    }

    public void addPetOrder(int customerId, int petId, double total) {
        Order o = new Order(0, customerId, 0, petId, "pet", 1, total);
        orderDAO.insert(o);
    }

    // (Các phương thức get...() giữ nguyên)
    public ArrayList<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    public ArrayList<Order> getOrdersByCustomerId(int customerId) {
        return orderDAO.getByCustomerId(customerId);
    }

    public void deleteOrder(int id) {
        // [REPORT] BUSINESS LOGIC: Xử lý hoàn kho (Restock) trước khi xóa đơn hàng
        // 1. Lấy thông tin đơn hàng TRƯỚC khi xóa
        Order order = orderDAO.getOrderById(id);
        if (order == null) {
            throw new RuntimeException("Order not found, cannot cancel.");
        }

        // 2. Hoàn kho (Restock)
        try {
            if ("product".equals(order.getItemType())) {
                // 2a. Nếu là sản phẩm -> Cộng lại số lượng
                int productId = order.getProductId();
                int canceledQuantity = order.getQuantity();

                // Lấy số lượng hiện tại
                int currentStock = 0;
                // (getAllProducts() sẽ lấy tất cả sản phẩm, không sao)
                for (Product p : productController.getAllProducts()) {
                    if (p.getId() == productId) {
                        currentStock = p.getQuantity();
                        break;
                    }
                }

                int newStock = currentStock + canceledQuantity;
                productController.updateProductQuantity(productId, newStock);

            } else if ("pet".equals(order.getItemType())) {
                // 2b. Nếu là thú cưng -> Đổi status về "Available"
                petController.updatePetStatus(order.getPetId(), "Available");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to restock item: " + e.getMessage(), e);
        }

        // 3. Sau khi hoàn kho thành công, MỚI xóa đơn hàng
        orderDAO.delete(id);
    }
}