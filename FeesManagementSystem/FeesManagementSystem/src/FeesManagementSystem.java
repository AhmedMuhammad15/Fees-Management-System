import java.util.Scanner;

public class FeesManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FeesManager manager = new FeesManager();
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Fees");
            System.out.println("4. Display All Students");
            System.out.println("5. Sort Students by ID (Merge Sort)");
            System.out.println("6. Search Student by ID (Binary Search)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = InputValidation.getValidInteger();  // Using InputValidation class
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = InputValidation.getValidInteger();  // Get valid integer input

                    System.out.print("Enter Name: ");
                    char[] name = InputValidation.getValidCharArray();  // Get valid char array for Name
                    System.out.print("Enter Course Name: ");
                    char[] courseName = InputValidation.getValidCharArray();  // Get valid char array for Course
                    System.out.print("Enter Fees Paid: ");
                    double feesPaid = InputValidation.getValidDouble();  // Get valid double input
                    System.out.print("Enter Total Fees: ");
                    double totalFees = InputValidation.getValidDouble();  // Get valid double input
                    System.out.print("Enter Receipt: ");
                    char[] receipt = InputValidation.getValidCharArray();  // Get valid char array for Receipt

                    Student student = new Student(id, name, courseName, feesPaid, totalFees, receipt);
                    manager.addStudent(student);
                }
                case 2 -> {
                    System.out.print("Enter ID of student to delete: ");
                    int id = InputValidation.getValidInteger();
                    manager.deleteStudent(id);
                }
                case 3 -> {
                    System.out.print("Enter ID of student to update fees: ");
                    int id = InputValidation.getValidInteger();
                    System.out.print("Enter new Fees Paid: ");
                    double feesPaid = InputValidation.getValidDouble();
                    manager.updateFees(id, feesPaid);
                }
                case 4 -> manager.displayStudents();
                case 5 -> manager.mergeSortStudentsById();
                case 6 -> {
                    System.out.print("Enter ID to search using Binary Search: ");
                    int id = InputValidation.getValidInteger();
                    manager.binarySearchStudentById(id);
                }
                case 7 -> {
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
