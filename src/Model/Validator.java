package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validateEmail(String email) {
        String regex="^[a-zA-Z0-9_%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean validatePassword(String password) {
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password should have at least one lowercase character");
            return false;
        }
        if (!password.matches("(.*[A-Z]).*")) {
            System.out.println("Password should at least one uppercase character");
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            System.out.println("Password should at least one digit");
            return false;
        }
        if (password.length()<6){
            System.out.println("Password should have at least 6 characters");
            return false;
        }
        return true;
    }
}
