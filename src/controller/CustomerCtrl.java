package controller;

import java.util.ArrayList;

import dao.CustomerDAO;
import model.Customer;

public class CustomerCtrl {

    private CustomerDAO customerDAO = new CustomerDAO();

    
    public void addCustomer(String name, String phone, String email, int accountId) {
        Customer c = new Customer(0, name, phone, email, accountId); // Phải dùng constructor 5 tham số
        customerDAO.insert(c);
    }

    
    public ArrayList<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }
    
    
    public Customer getCustomerByAccountId(int accountId) {
        return customerDAO.findByAccountId(accountId);
    }
}