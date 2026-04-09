package model;

public class Car extends Vehicle{

    enum engineType{GASOLINE, DIESEL}
    private int numberOfCylinders;
    private int numberOfMaxSeats;

    public Car(String vehicleId, String mark, String model, double costPerDay, int numberOfCylinders, int numberOfMaxSeats) {
        super(vehicleId, mark, model, costPerDay);
        this.numberOfCylinders = numberOfCylinders;
        this.numberOfMaxSeats = numberOfMaxSeats;
    }

    @Override
    String showDetails() {
        return "";
    }

    @Override
    public String getId() {
        return this.getVehicleId();
    }

    @Override
    public String toString() {
        return super.toString() + numberOfMaxSeats;
    }

    @Override
    public String toCsv() {
        return String.format("CAR,%s,%s,%s,%b,%.2f,%d,%d", getId(), this.getMark(), this.getModel(), this.isAvailable(), this.getCostPerDay(), numberOfCylinders, numberOfMaxSeats);
    }


}


