package service;

import model.*;
import repository.RepositoryGeneric;

import java.io.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Services implements RentService, Registration{

    private final Scanner scanner = new Scanner(System.in);
    private RepositoryGeneric<Client> clients = new RepositoryGeneric<>();
    private RepositoryGeneric<Vehicle> vehicles = new RepositoryGeneric<>();
    private RepositoryGeneric<Rental> rentals = new RepositoryGeneric<>();

    public Services() {
        loadAllFiles();
    }

    @Override
    public void rentVehicle(String idVehicle, String idClient, int numberOfDays) {
        Client client = (Client) findId(clients, idClient);
        Vehicle vehicle = (Vehicle) findId(vehicles, idVehicle);

        // Check if the client and vehicle exists
        if ((client == null || vehicle == null) ){
            System.out.println("Client or Vehicle was not found in the database");
            return;
        }

        // Check if the vehicle is Available
        if (!vehicle.isAvailable()){
            System.out.println("Searched Vehicle is not available");
            return;
        }

        double totalCost = vehicle.getCostPerDay() * numberOfDays;

        rentalRegistration(client, vehicle, numberOfDays);
        vehicle.setAvailable(false);

        saveAll();
    }



    @Override
    public void retrieveVehicle(String idRental) {
        Rental rental = (Rental) findId(rentals, idRental);
        if (rental != null){
            rental.getVehicle().setAvailable(true);
            rentals.remove(rental);
        }else {
            System.out.println("Vehicle is not in the database");
        }
        saveAll();
    }

    //Registration methods to Database
    @Override
    public void carRegistration(String mark, String model, double costPerDay, int numberOfCylinders, int numberOfMaxSeats) {
        while (true){
            String id = generateId();
            if (findId(vehicles, id) == null){
                vehicles.add(new Car(id, mark, model,costPerDay, numberOfCylinders, numberOfMaxSeats));
                break;
            }
        }

        saveAll();
    }

    @Override
    public void motoRegistration(String mark, String model, double costPerDay, double cc) {
        while (true){
            String id = generateId();
            if (findId(vehicles, id) == null){
                vehicles.add(new Moto(id, mark, model,costPerDay, cc));
                break;
            }
        }
        saveAll();
    }

    @Override
    public void clientRegistration(String firstName, String lastName) {
        Client tempClient = new Client("", firstName, lastName); // Temporary object for check
        if (findClientByName(clients, tempClient) != null) {
            System.out.println("Error: Client " + firstName + " " + lastName + " is already registered!");
            return; // Exit the method immediately
        }
        while (true){
            String id = generateId();
            if (findId(clients, id) == null){
                Client client = new Client(id, firstName, lastName);
                clients.add(client);
                break;
            }
        }
        saveAll();
    }

    //RentalRegistration
    @Override
    public void rentalRegistration(Client client, Vehicle vehicle, int daysRented) {

        class RentalValidator{
            boolean canRent(){
                return vehicle.isAvailable() && daysRented > 0 && daysRented <=30;
            }
            void rentalStatus(){
                System.out.println("Validating rental for: " + client.getFirstName());
            }
        }

        RentalValidator rentalValidator = new RentalValidator();
        rentalValidator.rentalStatus();

        if (rentalValidator.canRent()){
            for (Rental rental : rentals.getList()){
                if (client.getId().equalsIgnoreCase(rental.getClient().getId())){
                    System.out.println("Client has already a rented vehicle or the vehicle is already rented");
                    return;
                }
            }

            while (true){
                String rentalId = generateId();
                if (findId(rentals, rentalId) == null){
                    Rental rental = new Rental(rentalId, client, vehicle, daysRented);
                    rentals.add(rental);
                    rental.getReceipt();
                    break;
                }
            }
            saveAll();
        }
    }

    //Generate random id
    private static String generateId(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++){
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

    // Used id to retrieve an Identifiable Object from a repository with the id tracking
    private static Identifiable findId(RepositoryGeneric<? extends Identifiable> repository, String id){
        for (var item : repository.getList()){
            if (item.getId().equalsIgnoreCase(id)){
                return item;
            }
        }
        return null;
    }

    // Make an extra checking for Clients
    private static Client findClientByName(RepositoryGeneric<? extends Client> clients, Client client){
        for (Client c : clients.getList()) {
            if (c.getFirstName().equalsIgnoreCase(client.getFirstName()) && c.getLastName().equalsIgnoreCase(client.getLastName())) {
                return c;
            }
        }
        return null;
    }


    //Making the DATABASE Logic
    // I used Generic Method to write a file of one kind of Object
    private <T extends Identifiable> void saveRepositoryToCsv(RepositoryGeneric<T> repositoryGeneric, String fileName){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            for (T item : repositoryGeneric.getList()){
                if (item instanceof Vehicle){
                    writer.println(((Vehicle) item).toCsv());
                }else if(item instanceof Client){
                    writer.println(((Client) item).toCSV());
                }else if(item instanceof Rental){
                    writer.println(((Rental) item).toCSV());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Write all files at once, we use that in every modification we made to ensure safety
    private void saveAll(){
        saveRepositoryToCsv(clients, "src/data/clients.txt");
        saveRepositoryToCsv(vehicles, "src/data/vehicles.txt");
        saveRepositoryToCsv(rentals, "src/data/rentals.txt");
    }

    // I used Generic Method to load a file as an Identifiable Repository
    public <T extends Identifiable> void loadRepository(String fileName){
        // Create a File object
        File file = new File(fileName);
        try (Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();

                String[] data = line.split(",");
                String type = data[0];

                switch (type){
                    case "CLIENT":
                        clients.add(new Client(data[1], data[2],data[3]));
                        break;
                    case "CAR":
                        Car car = new Car(data[1],data[2],data[3],Double.parseDouble(data[5]),
                                Integer.parseInt(data[6]),Integer.parseInt(data[7]));
                        vehicles.add(car);
                        break;
                    case "MOTO":
                        vehicles.add(new Moto(data[1],data[2],data[3], Double.parseDouble(data[5]), Double.parseDouble(data[6])));
                        break;
                    case "RENTAL":
                        String rentalId = data[1];
                        Client client = (Client) findId(clients, data[2]);
                        Vehicle vehicle = (Vehicle) findId(vehicles, data[3]);
                        int days = Integer.parseInt(data[4]);
                        if (client != null && vehicle != null){
                            Rental rental = new Rental(rentalId, client, vehicle, days);
                            rental.setStartDay(LocalDate.parse(data[5]));
                        }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Load all files at once, use in the constructor
    private void loadAllFiles(){
        loadRepository("src/data/clients.txt");
        loadRepository("src/data/vehicles.txt");
        loadRepository("src/data/rentals.txt");
    }
}

