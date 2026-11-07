abstract class Employee {
    protected String name;
    protected int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    private double salary;

    FullTime(String name, int id, double salary) {
        super(name, id);
        this.salary = salary;
    }

    double monthlySalary() {
        return salary;
    }
}

class PartTime extends Employee {
    private int hours;
    private double rate;

    PartTime(String name, int id, int hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {
    private double stipend;

    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    double monthlySalary() {
        return stipend;
    }
}

public class PayrollDemo {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Amit", 101, 40000),
            new PartTime("Neha", 102, 80, 200),
            new Intern("Raj", 103, 8000)
        };

        double total = 0;

        for (Employee e : employees) {
            double salary = e.monthlySalary();
            total += salary;

            System.out.println(e.name + " (" + e.id
                    + "): " + salary);

            if (e instanceof Intern) {
                System.out.println("Intern receives a stipend.");
            }
        }

        System.out.println("Total payroll: " + total);
    }
}

