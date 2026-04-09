import service.Services;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Services services = new Services();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to The Car Rental System");
            System.out.println("---".repeat(20));
            System.out.println("1. Client Registration");
            System.out.println("2. Vehicle Registration");
            System.out.println("3. Rent Car");
            System.out.println("4. Retrieve Car");
            System.out.println("Choose a number between1-4: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Enter your FirstName: ");
                    String firstName = scanner.nextLine().trim();
                    System.out.println("Enter your LastName: ");
                    String lastName = scanner.nextLine().trim();
                    services.clientRegistration(firstName, lastName);
                    break;

                case "2":
                    System.out.println("Moto or Car: ");
                    String vehicleChoice = scanner.nextLine();

                    System.out.println("Enter the Mark: ");
                    String mark = scanner.nextLine();

                    System.out.println("Enter the Model: ");
                    String model = scanner.nextLine();

                    System.out.println("Enter the cost per day: ");
                    double costPerDay = Double.parseDouble(scanner.nextLine());

                    if (vehicleChoice.equalsIgnoreCase("Car")) {
                        System.out.println("Enter the number of Cylinders: ");
                        int numberOfCylinders = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter the number of Max Seats: ");
                        int numberOfMaxSeats = Integer.parseInt(scanner.nextLine());

                        services.carRegistration(mark, model, costPerDay, numberOfCylinders, numberOfMaxSeats);
                    } else {
                        System.out.println("Enter the cc of the motor: ");
                        double cc = Double.parseDouble(scanner.nextLine());
                        services.motoRegistration(mark, model, costPerDay, cc);
                    }
                    break;

                case "3":
                    System.out.println("Enter the Client Id: ");
                    String clientId = scanner.nextLine();

                    System.out.println("Enter the Vehicle Id: ");
                    String vehicleId = scanner.nextLine();

                    System.out.println("Enter the number of days you want to rent: ");
                    int daysRented = Integer.parseInt(scanner.nextLine());

                    services.rentVehicle(vehicleId, clientId, daysRented);
                    break;
                case "4":
                    System.out.println("Enter the Rental Id: ");
                    String rentalId = scanner.nextLine();
                    services.retrieveVehicle(rentalId);
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}

