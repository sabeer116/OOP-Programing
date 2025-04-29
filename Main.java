import java.util.Scanner;

// Rentable Interface
interface Rentable {
    double calculateRent(int days) throws InvalidRentalDurationException;
    void bookVehicle() throws OverbookingException;
}

// Custom Exception: Overbooking
class OverbookingException extends Exception {
    public OverbookingException(String message) {
        super(message);
    }
}

// Custom Exception: Invalid Rental Duration
class InvalidRentalDurationException extends Exception {
    public InvalidRentalDurationException(String message) {
        super(message);
    }
}

// Abstract Class Vehicle (Abstraction)
abstract class Vehicle implements Rentable {
    private String vehicleId;
    private String model;
    private boolean available;

    public Vehicle(String vehicleId, String model) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.available = true; // initially available
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void bookVehicle() throws OverbookingException {
        if (!available) {
            throw new OverbookingException("Vehicle is already booked!");
        }
        available = false;
        System.out.println("Vehicle booked successfully.");
    }

    @Override
    public abstract double calculateRent(int days) throws InvalidRentalDurationException;
}

// Car Class
class Car extends Vehicle {
    private final double ratePerDay = 50;

    public Car(String id, String model) {
        super(id, model);
    }

    @Override
    public double calculateRent(int days) throws InvalidRentalDurationException {
        if (days <= 0) throw new InvalidRentalDurationException("Rental days must be greater than 0.");
        return ratePerDay * days;
    }
}

// Bike Class
class Bike extends Vehicle {
    private final double ratePerDay = 20;

    public Bike(String id, String model) {
        super(id, model);
    }

    @Override
    public double calculateRent(int days) throws InvalidRentalDurationException {
        if (days <= 0) throw new InvalidRentalDurationException("Rental days must be greater than 0.");
        return ratePerDay * days;
    }
}

// Truck Class
class Truck extends Vehicle {
    private final double ratePerDay = 100;

    public Truck(String id, String model) {
        super(id, model);
    }

    @Override
    public double calculateRent(int days) throws InvalidRentalDurationException {
        if (days <= 0) throw new InvalidRentalDurationException("Rental days must be greater than 0.");
        return ratePerDay * days;
    }
}

// Encapsulated Customer Class
class Customer {
    private String name;
    private String license;

    public Customer(String name, String license) {
        this.name = name;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }
}

// Rental Service using Polymorphism
class RentalService {
    public void processRental(Vehicle vehicle, int days, Customer customer) {
        try {
            vehicle.bookVehicle(); // might throw OverbookingException
            double cost = vehicle.calculateRent(days); // might throw InvalidRentalDurationException
            System.out.println("Customer: " + customer.getName() + ", License: " + customer.getLicense());
            System.out.println("Vehicle Model: " + vehicle.getModel());
            System.out.println("Rental Cost: $" + cost);
        } catch (OverbookingException | InvalidRentalDurationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// Main class using Scanner
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalService service = new RentalService();

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter license number: ");
        String license = scanner.nextLine();

        Customer customer = new Customer(name, license);

        System.out.print("Enter vehicle type (Car, Bike, Truck): ");
        String type = scanner.nextLine();

        System.out.print("Enter vehicle ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        System.out.print("Enter number of rental days: ");
        int days = scanner.nextInt();

        Vehicle vehicle;

        // Polymorphism - same reference, different objects
        switch (type.toLowerCase()) {
            case "car":
                vehicle = new Car(id, model);
                break;
            case "bike":
                vehicle = new Bike(id, model);
                break;
            case "truck":
                vehicle = new Truck(id, model);
                break;
            default:
                System.out.println("Invalid vehicle type.");
                scanner.close();
                return;
        }

        service.processRental(vehicle, days, customer);
        scanner.close();
    }
}
