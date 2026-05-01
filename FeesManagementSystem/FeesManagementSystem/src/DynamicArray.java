class DynamicArray {
    private Student[] array;
    private int size;
    private int capacity;

    public DynamicArray() {
        capacity = 2;
        array = new Student[capacity];
        size = 0;
    }

    public void add(Student student) {
        if (size == capacity) {
            grow();
        }
        array[size++] = student;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public void set(int index, Student student) {
        if (index >= 0 && index < size) {
            array[index] = student;
        }
    }

    public Student get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    private void grow() {
        capacity *= 2;
        Student[] newArray = new Student[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}
