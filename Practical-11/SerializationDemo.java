import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    transient String password;

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name
                + ", Password: " + password;
    }
}

public class SerializationDemo {
    public static void main(String[] args) throws Exception {
        Student[] students = {
            new Student(1, "Amit", "abc123"),
            new Student(2, "Neha", "xyz456")
        };

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("students.dat"))) {
            out.writeObject(students);
        }

        Student[] loaded;

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("students.dat"))) {
            loaded = (Student[]) in.readObject();
        }

        System.out.println("Loaded students:");

        for (Student s : loaded) {
            System.out.println(s);
        }
    }
}
