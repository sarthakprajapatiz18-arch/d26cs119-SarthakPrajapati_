public class PasswordChecker {
    public static String strength(String pw) {
        int score = 0;

        if (pw.length() >= 8) {
            System.out.println("Length: Passed");
            score++;
        } else {
            System.out.println("Length: Failed");
        }

        if (pw.matches(".*[A-Z].*")) {
            System.out.println("Uppercase: Passed");
            score++;
        } else {
            System.out.println("Uppercase: Failed");
        }

        if (pw.matches(".*[0-9].*")) {
            System.out.println("Digit: Passed");
            score++;
        } else {
            System.out.println("Digit: Failed");
        }

        if (pw.matches(".*[^a-zA-Z0-9].*")) {
            System.out.println("Special character: Passed");
            score++;
        } else {
            System.out.println("Special character: Failed");
        }

        if (score <= 1) return "Weak";
        if (score <= 3) return "Medium";
        return "Strong";
    }
}

class Driver {
    public static void main(String[] args) {
        String[] passwords = {"abc", "Abcd1234!"};

        for (String pw : passwords) {
            System.out.println("\nPassword: " + pw);
            System.out.println("Strength: "
                    + PasswordChecker.strength(pw));
        }
    }
}
