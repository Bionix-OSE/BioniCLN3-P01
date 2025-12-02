package model;

import dao.AccountDAO;
import model.Account;

public class Auth {
    private AccountDAO accountDAO = new AccountDAO();

    public Account login(String username, String password) {
        Account acc = accountDAO.findByUsername(username);
        if (acc == null)
            return null;

        if (acc.getPassword().equals(password)) {
            return acc;
        }

        return null;
    }
}
