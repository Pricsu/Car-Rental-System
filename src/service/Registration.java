package service;

import model.Client;
import model.Vehicle;

public interface Registration {
    void carRegistration(String mark, String model, double costPerDay, int numberOfCylinders, int numberOfMaxSeats);
    void motoRegistration(String mark, String model, double costPerDay, double cc);
    void clientRegistration(String firstName, String lastName);
    void rentalRegistration(Client client, Vehicle vehicle, int daysRented);
}
