import java.io.*;


class FeesManager {
    public DynamicArray students;
    private static final String FILE_NAME = "students.txt";

    public FeesManager() {
        students = new DynamicArray();
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        if (isDuplicate(student.id)) {
            System.out.println("Student with this ID already exists.");
            return;
        }
        students.add(student);
        saveStudentsToFile();
        System.out.println("Student added successfully.");
    }

    public void deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id == id) {
                students.delete(i);
                saveStudentsToFile();
                System.out.println("Student deleted successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void updateFees(int id, double newFeesPaid) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id == id) {
                students.get(i).feesPaid = newFeesPaid;
                saveStudentsToFile();
                System.out.println("Fees updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void mergeSortStudentsById() {
        if (students.size() > 1) {
            mergeSortHelper(0, students.size() - 1);
            saveStudentsToFile();
            System.out.println("Students sorted by ID using Merge Sort.");
        } else {
            System.out.println("Not enough students to sort.");
        }
    }

    private void mergeSortHelper(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSortHelper(left, mid);
            mergeSortHelper(mid + 1, right);

            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = students.get(left + i);
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = students.get(mid + 1 + j);
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].id <= rightArray[j].id) {
                students.set(k, leftArray[i]);
                i++;
            } else {
                students.set(k, rightArray[j]);
                j++;
            }
            k++;
        }

        while (i < n1) {
            students.set(k, leftArray[i]);
            i++;
            k++;
        }

        while (j < n2) {
            students.set(k, rightArray[j]);
            j++;
            k++;
        }
    }

    public void binarySearchStudentById(int id) {
        if (students.size() == 0) {
            System.out.println("No students to search.");
            return;
        }

        int index = binarySearchHelper(0, students.size() - 1, id);

        if (index != -1) {
            displayStudent(students.get(index));
        } else {
            System.out.println("Student not found.");
        }
    }

    private int binarySearchHelper(int left, int right, int id) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (students.get(mid).id == id) {
                return mid;
            } else if (students.get(mid).id < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public void displayStudents() {
        if (students.size() == 0) {
            System.out.println("No students to display.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            displayStudent(students.get(i));
        }
    }

    private void displayStudent(Student student) {
        System.out.print("ID: ");
        System.out.println(student.id);
        System.out.print("Name: ");
        printCharArray(student.name);
        System.out.print("Course: ");
        printCharArray(student.courseName);
        System.out.print("Fees Paid: ");
        System.out.println(student.feesPaid);
        System.out.print("Total Fees: ");
        System.out.println(student.totalFees);
        System.out.print("Receipt: ");
        printCharArray(student.receipt);
        System.out.println("---------------------------");
    }

    private boolean isDuplicate(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id == id) {
                return true;
            }
        }
        return false;
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                writer.write(student.id + "," + new String(student.name) + "," + new String(student.courseName) + "," + student.feesPaid + "," + student.totalFees + "," + new String(student.receipt));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);

                    // Check if student with the same ID already exists in the array
                    if (isDuplicate(id)) {
                        continue;  // Skip this student as it is a duplicate
                    }

                    char[] name = parts[1].toCharArray();
                    char[] courseName = parts[2].toCharArray();
                    double feesPaid = Double.parseDouble(parts[3]);
                    double totalFees = Double.parseDouble(parts[4]);
                    char[] receipt = parts[5].toCharArray();
                    students.add(new Student(id, name, courseName, feesPaid, totalFees, receipt));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }



    private void printCharArray(char[] arr) {
        for (char c : arr) {
            System.out.print(c);
        }
        System.out.println();
    }
}
