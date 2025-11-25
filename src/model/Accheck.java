package model;

public class Accheck {

    public boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^(.+)@(.+)$");
    }

    public boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
