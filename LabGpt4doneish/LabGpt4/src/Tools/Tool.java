
package Tools;
import Model.Product;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Tool {

    private static Scanner scanner = new Scanner(System.in);

    public static String validateString(String prompt, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            }
            System.out.println(errorMessage);
        }
        return input;
    }

    public static LocalDate validateLocalDate(String prompt, String errorMessage) {
        LocalDate date;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(input);
                break;
            } catch (DateTimeParseException e) {
                System.out.println(errorMessage);
            }
        }
        return date;
    }

    public static int validateInt(String prompt, String errorMessage) {
        int number;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                number = Integer.parseInt(input);
                if (number > 0) {
                    break;
                } else {
                    System.out.println("Number should be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
        return number;
    }

    public static double validateDouble(String prompt, String errorMessage) {
        double number;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                number = Double.parseDouble(input);
                if (number >= 0) {
                    break;
                } else {
                    System.out.println("Number should be non-negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
        return number;
    }
    public static String validateAlphanumericString(String prompt, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (Pattern.matches("[a-zA-Z0-9]+", input)) {
                break;
            }
            System.out.println(errorMessage);
        }
        return input;
    }

    public static LocalDate validateNotFutureDate(String prompt, String errorMessage) {
        LocalDate date;
        while (true) {
            date = validateLocalDate(prompt, "Invalid date format. Please enter again.");
            if (!date.isAfter(LocalDate.now())) {
                break;
            }
            System.out.println(errorMessage);
        }
        return date;
    }

    public static LocalDate validateDateBefore(String prompt, LocalDate beforeThisDate, String errorMessage) {
    LocalDate date;
    while (true) {
        date = validateLocalDate(prompt, "Invalid date format. Please enter again.");
        if (date.isBefore(beforeThisDate)) {
            break;
        }
        System.out.println(errorMessage);
    }
    return date; // return the validated date, not a boolean
}


    public static String validateExistInMap(String prompt, Map<String, ?> map, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (map.containsKey(input)) {
                break;
            }
            System.out.println(errorMessage);
        }
        return input;
    }
    
    public static String validateExistInList(String prompt, List<Product> productList, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            final String currentInput = input;  // Declare a final variable to hold the current value of input
            
            if (productList.stream().anyMatch(product -> product.getProductCode().equals(currentInput))) {
                break;
            }
            System.out.println(errorMessage);
        }
        return input;
    }


    public static boolean validateYesOrNo(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt + " (y/n): ");
            input = scanner.nextLine().trim().toLowerCase();
            if ("y".equals(input) || "n".equals(input)) {
                break;
            }
            System.out.println("Invalid input. Enter 'y' for yes and 'n' for no.");
        }
        return "y".equals(input);
    }
    
    public static String checkNull(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return null;
        } else {
            return input;
        }
    }
}

