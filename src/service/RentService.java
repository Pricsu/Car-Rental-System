package service;

public interface RentService {
    void rentVehicle(String idVehicle, String idClient, int numberOfDays);
    void retrieveVehicle(String idVehicle);
}
