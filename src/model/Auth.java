package model;

import dao.AccountDAO;
import model.Account;

public class Auth {
    // [REPORT] SERVICE LAYER: Chứa logic nghiệp vụ (Business Logic) như đăng nhập,
    // tính toán

    private AccountDAO accountDAO = new AccountDAO();

    public Account login(String username, String password) {
        // [REPORT] Gọi DAO để tìm user trong database
        Account acc = accountDAO.findByUsername(username);
        if (acc == null)
            return null;

        // [REPORT] Kiểm tra mật khẩu (Logic so sánh đơn giản)
        if (acc.getPassword().equals(password)) {
            return acc;
        }

        return null;
    }
}
