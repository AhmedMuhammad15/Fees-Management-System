import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation {

    public static int getValidInteger() {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        boolean valid = false;

        while (!valid) {
            try {
                value = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        return value;
    }

    public static double getValidDouble() {
        Scanner scanner = new Scanner(System.in);
        double value = 0.0;
        boolean valid = false;

        while (!valid) {
            try {
                value = scanner.nextDouble();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        return value;
    }

    public static char[] getValidCharArray() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean valid = false;

        while (!valid) {
            try {
                input = scanner.nextLine();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Input cannot be empty.");
                }
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Please try again.");
            }
        }
        return input.toCharArray();
    }
}

