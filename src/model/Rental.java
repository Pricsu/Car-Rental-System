package model;

import java.time.Duration;
import java.time.LocalDate;

public class Rental implements Identifiable{

    private String id;
    private Client client;
    private Vehicle vehicle;
    private int daysRented;
    private LocalDate startDay;
    private LocalDate endDate;


    public Rental(String id, Client client, Vehicle vehicle, int daysRented) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.daysRented = daysRented;
        startDay = LocalDate.now();
        endDate = startDay.plusDays(daysRented);
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public long getRemainedDays(){
        LocalDate currentDate = LocalDate.now();
        return Duration.between(currentDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
    }

    @Override
    public String getId() {
        return id;
    }

    public String toCSV() {
        return String.format("RENTAL,%s,%s,%s,%d,%s", getId(), client.getId(), vehicle.getId(), getRemainedDays(), startDay);
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void getReceipt(){
        this.new Receipt().printReceipt();
    }

    // Use Inner Class to implement Receipt
    public class Receipt{
        private double taxRate = 0.19;

        public void printReceipt(){
            double fullPrice = (vehicle.getCostPerDay() * daysRented) * taxRate;
            System.out.println("--- RECEIPT ---");
            System.out.println("Rental ID: " + id);
            System.out.println("Total with Tax: " + fullPrice);
        }
    }
}
