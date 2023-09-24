tool.java

import Model.Product;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

public class Tool 

    private static Scanner scanner = new Scanner(System.in);

/*
 * don't use rn
 */
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
    
    public static String validateStringUpdate(String input, String errorMessage) {
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

==========================="CAN FKING USE"=================================


    public static LocalDate validateLocalDate(String prompt, String errorMessage) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    LocalDate date;
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            date = LocalDate.parse(input, formatter);
            break;
        } catch (DateTimeParseException e) {
            System.out.println(errorMessage + " (" + e.getMessage() + ")");  // Adding exception message
        }
    }
    return date;
}
public static double validateDouble(String prompt, String formatErrorMessage, String negativeErrorMessage) {
    double number;
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        try {
            number = Double.parseDouble(input);
            if (number >= 0) {
                break;
            } else {
                System.out.println(negativeErrorMessage);
            }
        } catch (NumberFormatException e) {
            System.out.println(formatErrorMessage);
        }
    }
    return number;
}
public static String validateAlphanumericString(String prompt, String errorMessage) {
    String input;
    while (true) {
        System.out.print(prompt);
        input = scanner.nextLine().trim();
        if (!input.isEmpty() && Pattern.matches("[a-zA-Z0-9 ]+", input)) {
            break;
        }
        System.out.println(errorMessage);
    }
    return input;
}
public static String validateCode(String prompt, String errorMessage) {
    String input;
    while (true) {
        System.out.print(prompt);
        input = scanner.nextLine().trim();
        if (Pattern.matches("[a-zA-Z0-9]+", input)) {
            break;
        }
        System.out.println(errorMessage);
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
    public static LocalDate validateDateAfter(String prompt, LocalDate afterThisDate, String errorMessage) {
    LocalDate date;
    while (true) {
        date = validateLocalDate(prompt, "Invalid date format. Please enter again.");
        if (date.isAfter(afterThisDate)) {
            break;
        }
        System.out.println(errorMessage);
    }
    return date;
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
    public static int validateIntRange(String prompt, int min, int max, String errorMessage) {
    Scanner scanner = new Scanner(System.in);
    int input;

    while (true) {
        System.out.print(prompt);
        try {
            input = Integer.parseInt(scanner.nextLine());
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer.");
        }
    }
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
    public static LocalDate parseDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    LocalDate date;
    try {
        date = LocalDate.parse(dateString, formatter);
    } catch (DateTimeParseException e) {
        return null;
    }
    return date;
}

    public static LocalDate getValidManufactureDate() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        String dateString = scanner.nextLine().trim();
        if (dateString.isEmpty()) {
            return null;  // user skipped by hitting enter
        }
        LocalDate date = parseDate(dateString);
        if (date != null && !date.isAfter(LocalDate.now())) {
            return date;
        } else {
            System.out.println("Please enter a valid manufacture date (not in the future) in the format d/M/yyyy:");
        }
    }
}

    public static LocalDate getValidExpiryDate(LocalDate manufactureDate) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        String dateString = scanner.nextLine().trim();
        if (dateString.isEmpty() && manufactureDate != null) {
            return null;  // user skipped by hitting enter
        }
        LocalDate date = parseDate(dateString);
        if (date != null && (manufactureDate == null || !date.isBefore(manufactureDate))) {
            return date;
        } else {
            System.out.println("Please enter a valid expiry date (after manufacture date) in the format d/M/yyyy:");
        }
    }
}
=============================="ALREADY ADDED"==============================================
    public static LocalDate validateLocalDateUpdate(String input, String prompt, String errorMessage) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    while (true) {
        if (input != null && !input.isEmpty()) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println(errorMessage + " (" + e.getMessage() + ")");
            }
        }
        System.out.print(prompt);
        input = scanner.nextLine().trim();
    }
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
    public static double validateDoubleUpdate(String input, String prompt, String errorMessage) {
    while (true) {
        if (input != null && !input.isEmpty()) {
            try {
                double number = Double.parseDouble(input);
                if (number >= 0) {
                    return number;
                } else {
                    System.out.println("Number should be non-negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
        System.out.print(prompt);
        input = scanner.nextLine().trim();
    }
}
    public static LocalDate validateNotFutureDateUpdate(String input, String prompt, String errorMessage) {
    LocalDate date = validateLocalDateUpdate(input, prompt, "Invalid date format. Please enter again.");
    while (true) {
        if (date != null && !date.isAfter(LocalDate.now())) {
            return date;
        }
        System.out.println(errorMessage);
        date = validateLocalDate(prompt, "Invalid date format. Please enter again.");
    }
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
    public static LocalDate validateDateAfterUpdate(String input, LocalDate startDate, String prompt, String errorMessage) {
    LocalDate date = validateLocalDateUpdate(input, prompt, "Invalid date format. Please enter again.");
    while (true) {
        if (date != null && !date.isBefore(startDate)) {
            return date;
        }
        System.out.println(errorMessage);
        date = validateLocalDate(prompt, "Invalid date format. Please enter again.");
    }
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
    
    public static String checkNull(String prompt, String errorMessage) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return null;
        } else {
            return input;
        }
    }
    
    
    
   



}

