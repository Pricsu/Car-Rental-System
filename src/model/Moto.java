package model;

public class Moto extends Vehicle{

    private String engineType = "Gasoline";
    private double cc;
    public Moto(String vehicleId, String mark, String model, double costPerDay, double cc) {
        super(vehicleId, mark, model, costPerDay);
        this.cc = cc;
    }

    @Override
    String showDetails() {
        return "";
    }

    @Override
    public String getId() {
        return this.getVehicleId();
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return super.toString() + engineType;
    }

    @Override
    public String toCsv() {
        return String.format("MOTO,%s,%s,%s,%b,%.2f,%.2f", getId(), this.getMark(), this.getModel(), this.isAvailable(), this.getCostPerDay(), cc);
    }
}
