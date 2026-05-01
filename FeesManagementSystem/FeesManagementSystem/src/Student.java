class Student {
    int id;
    char[] name;
    char[] courseName;
    double feesPaid;
    double totalFees;
    char[] receipt;

    public Student(int id, char[] name, char[] courseName, double feesPaid, double totalFees, char[] receipt) {
        this.id = id;
        this.name = name;
        this.courseName = courseName;
        this.feesPaid = feesPaid;
        this.totalFees = totalFees;
        this.receipt = receipt;
    }
}
