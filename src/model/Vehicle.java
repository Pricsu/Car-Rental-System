package model;

public abstract class Vehicle implements Identifiable{
    private String vehicleId;
    private String mark;
    private String model;
    private double costPerDay;
    private boolean isAvailable = true;

    public Vehicle(String vehicleId, String mark, String model, double costPerDay) {
        this.vehicleId = vehicleId;
        this.mark = mark;
        this.model = model;
        this.costPerDay = costPerDay;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    abstract String showDetails();

    @Override
    public String toString() {
        return vehicleId + " " + mark + " " + model + " " + costPerDay;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public abstract String toCsv();
}
