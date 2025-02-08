package org.example.utils;

public class ValidationUtil {

    private static final String emailRegex = "^[A-Za-z0-9+_.-]+@(?:gmail\\.com|yahoo\\.com|hotmail\\.com|outlook\\.com)$";
    private static final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$";
    public static Boolean validateUserDetails(String email, String password) {
        if (email == null || password == null) {
            return false;
        }


        return email.matches(emailRegex) && password.matches(passwordRegex);
    }
}
