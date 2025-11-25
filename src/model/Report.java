package model;

import java.util.ArrayList;

import dao.OrderDAO;
import model.Order;

public class Report {

    private OrderDAO orderDAO = new OrderDAO();

    public double getTotalRevenue() {
        ArrayList<Order> list = orderDAO.getAll();
        double total = 0;

        for (Order o : list) {
            total += o.getTotal();
        }

        return total;
    }

    public int getTotalOrders() {
        return orderDAO.getAll().size();
    }
}
