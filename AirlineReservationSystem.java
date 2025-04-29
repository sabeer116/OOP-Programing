import java.util.*;

// Custom Exceptions
class FlightFullException extends Exception {
    public FlightFullException(String message) {
        super(message);
    }
}

class InvalidSeatException extends Exception {
    public InvalidSeatException(String message) {
        super(message);
    }
}

// Interfaces
interface Bookable {
    void bookSeat(int seatNumber) throws FlightFullException, InvalidSeatException;
}

interface Cancellable {
    void cancelSeat(int seatNumber);
}

// Abstract Flight class
abstract class Flight implements Bookable, Cancellable {
    protected String flightNumber;
    protected String origin;
    protected String destination;
    protected int totalSeats;
    protected boolean[] seats;

    public Flight(String flightNumber, String origin, String destination, int totalSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats];
    }

    public abstract void displayFlightInfo();

    @Override
    public void bookSeat(int seatNumber) throws FlightFullException, InvalidSeatException {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            throw new InvalidSeatException("Invalid seat number.");
        }
        if (seats[seatNumber - 1]) {
            throw new FlightFullException("Seat already booked.");
        }
        seats[seatNumber - 1] = true;
        System.out.println("Seat " + seatNumber + " successfully booked on flight " + flightNumber);
    }

    @Override
    public void cancelSeat(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= totalSeats && seats[seatNumber - 1]) {
            seats[seatNumber - 1] = false;
            System.out.println("Seat " + seatNumber + " has been cancelled.");
        } else {
            System.out.println("Invalid or unbooked seat.");
        }
    }
}

// DomesticFlight subclass
class DomesticFlight extends Flight {
    public DomesticFlight(String flightNumber, String origin, String destination, int totalSeats) {
        super(flightNumber, origin, destination, totalSeats);
    }

    @Override
    public void displayFlightInfo() {
        System.out.println("Domestic Flight - " + flightNumber + ": " + origin + " to " + destination);
    }
}

// InternationalFlight subclass
class InternationalFlight extends Flight {
    public InternationalFlight(String flightNumber, String origin, String destination, int totalSeats) {
        super(flightNumber, origin, destination, totalSeats);
    }

    @Override
    public void displayFlightInfo() {
        System.out.println("International Flight - " + flightNumber + ": " + origin + " to " + destination);
    }
}

// Abstract Customer class
abstract class Customer {
    private String name;
    private String passportNumber; // Sensitive data

    public Customer(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getMaskedPassportNumber() {
        return "****" + passportNumber.substring(passportNumber.length() - 4);
    }

    public abstract void displayCustomerType();
}

// RegularCustomer subclass
class RegularCustomer extends Customer {
    public RegularCustomer(String name, String passportNumber) {
        super(name, passportNumber);
    }

    public void displayCustomerType() {
        System.out.println("Regular Customer: " + getName() + " (Passport: " + getMaskedPassportNumber() + ")");
    }
}

// VIPCustomer subclass
class VIPCustomer extends Customer {
    public VIPCustomer(String name, String passportNumber) {
        super(name, passportNumber);
    }

    @Override
    public void displayCustomerType() {
        System.out.println("VIP Customer: " + getName() + " (Passport: " + getMaskedPassportNumber() + ")");
    }
}

// Main Class
public class AirlineReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Flight Selection
            System.out.println("Choose Flight Type (1. Domestic, 2. International): ");
            int flightType = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter Flight Number: ");
            String flightNumber = scanner.nextLine();
            System.out.print("Enter Origin: ");
            String origin = scanner.nextLine();
            System.out.print("Enter Destination: ");
            String destination = scanner.nextLine();
            System.out.print("Enter Total Seats: ");
            int seats = scanner.nextInt(); scanner.nextLine();

            Flight flight;
            if (flightType == 1) {
                flight = new DomesticFlight(flightNumber, origin, destination, seats);
            } else {
                flight = new InternationalFlight(flightNumber, origin, destination, seats);
            }

            // Customer Input
            System.out.println("Enter Customer Type (1. Regular, 2. VIP): ");
            int customerType = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Passport Number: ");
            String passport = scanner.nextLine();

            Customer customer;
            if (customerType == 1) {
                customer = new RegularCustomer(name, passport);
            } else {
                customer = new VIPCustomer(name, passport);
            }

            // Booking
            flight.displayFlightInfo();
            customer.displayCustomerType();

            System.out.print("Enter seat number to book: ");
            int seatToBook = scanner.nextInt();
            flight.bookSeat(seatToBook);

            // Cancellation (optional)
            System.out.print("Do you want to cancel the seat? (yes/no): ");
            scanner.nextLine(); // consume newline
            String cancelChoice = scanner.nextLine();
            if (cancelChoice.equalsIgnoreCase("yes")) {
                flight.cancelSeat(seatToBook);
            }

        } catch (FlightFullException | InvalidSeatException e) {
            System.out.println("Booking Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
