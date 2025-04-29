import java.util.Scanner;

// Custom Exceptions
class AppointmentClashException extends Exception {
    public AppointmentClashException(String message) {
        super(message);
    }
}

class MissingRecordException extends Exception {
    public MissingRecordException(String message) {
        super(message);
    }
}

// Interfaces
interface Schedulable {
    void scheduleAppointment(String time) throws AppointmentClashException;
}

interface Diagnosable {
    void diagnose(String diagnosis);
}

// Abstract Class Person
abstract class Person {
    private String name;
    private int age;
    private String id;

    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getId() { return id; }

    public abstract void displayInfo();
}

// Doctor Class
class Doctor extends Person implements Schedulable, Diagnosable {
    private String specialty;
    private String scheduledTime = null;

    public Doctor(String name, int age, String id, String specialty) {
        super(name, age, id);
        this.specialty = specialty;
    }

    @Override
    public void scheduleAppointment(String time) throws AppointmentClashException {
        if (scheduledTime != null && scheduledTime.equals(time)) {
            throw new AppointmentClashException("Doctor already has an appointment at this time.");
        }
        scheduledTime = time;
        System.out.println("Appointment scheduled for Dr. " + getName() + " at " + time);
    }

    @Override
    public void diagnose(String diagnosis) {
        System.out.println("Dr. " + getName() + " diagnosed: " + diagnosis);
    }

    @Override
    public void displayInfo() {
        System.out.println("Doctor: " + getName() + ", Specialty: " + specialty + ", ID: " + getId());
    }
}

// Patient Class
class Patient extends Person implements Schedulable {
    private String disease;
    private String appointmentTime;
    private String history;

    public Patient(String name, int age, String id, String disease, String history) {
        super(name, age, id);
        this.disease = disease;
        this.history = history;
    }

    @Override
    public void scheduleAppointment(String time) {
        appointmentTime = time;
        System.out.println("Appointment scheduled for patient " + getName() + " at " + time);
    }

    public String getHistory() {
        return history;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient: " + getName() + ", Disease: " + disease + ", ID: " + getId());
    }
}

// Staff Class
class Staff extends Person {
    private String role;

    public Staff(String name, int age, String id, String role) {
        super(name, age, id);
        this.role = role;
    }

    @Override
    public void displayInfo() {
        System.out.println("Staff: " + getName() + ", Role: " + role + ", ID: " + getId());
    }
}

// Hospital System Class
class HospitalSystem {
    public void printDetails(Person[] people) throws MissingRecordException {
        if (people == null || people.length == 0) {
            throw new MissingRecordException("No people records found.");
        }
        for (Person p : people) {
            p.displayInfo();  // Polymorphism
        }
    }
}

// Main Class
public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospitalSystem hs = new HospitalSystem();

        try {
            // Doctor Input
            System.out.print("Enter Doctor's name: ");
            String dName = scanner.nextLine();
            System.out.print("Enter Doctor's age: ");
            int dAge = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter Doctor's ID: ");
            String dId = scanner.nextLine();
            System.out.print("Enter Doctor's specialty: ");
            String specialty = scanner.nextLine();
            Doctor doc = new Doctor(dName, dAge, dId, specialty);

            // Patient Input
            System.out.print("Enter Patient's name: ");
            String pName = scanner.nextLine();
            System.out.print("Enter Patient's age: ");
            int pAge = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter Patient's ID: ");
            String pId = scanner.nextLine();
            System.out.print("Enter Patient's disease: ");
            String disease = scanner.nextLine();
            System.out.print("Enter Patient's history: ");
            String history = scanner.nextLine();
            Patient patient = new Patient(pName, pAge, pId, disease, history);

            // Staff Input
            System.out.print("Enter Staff's name: ");
            String sName = scanner.nextLine();
            System.out.print("Enter Staff's age: ");
            int sAge = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter Staff's ID: ");
            String sId = scanner.nextLine();
            System.out.print("Enter Staff's role: ");
            String role = scanner.nextLine();
            Staff staff = new Staff(sName, sAge, sId, role);

            // Appointment Time
            System.out.print("Enter appointment time (e.g., 11AM): ");
            String time = scanner.nextLine();

            // Schedule and Diagnose
            doc.scheduleAppointment(time);
            patient.scheduleAppointment(time);
            doc.diagnose("Common cold and fever");

            // Print All People
            Person[] people = { doc, patient, staff };
            hs.printDetails(people);

        } catch (AppointmentClashException | MissingRecordException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
