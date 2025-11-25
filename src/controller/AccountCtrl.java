package controller;

import dao.AccountDAO;
import model.Account;

public class AccountCtrl {

    private AccountDAO accountDAO = new AccountDAO();

    public void register(String username, String password, String role) {
        Account acc = new Account(0, username, password, role);
        accountDAO.insert(acc);
    }

    public Account getAccount(String username) {
        return accountDAO.findByUsername(username);
    }
}
