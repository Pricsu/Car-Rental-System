package model;

public class Client implements Identifiable{
    private String clientId;
    private String firstName;
    private String lastName;

    public Client(String clientId, String firstName, String lastName) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toCSV() {
        return String.format("CLIENT,%s,%s,%s", getId(), firstName, lastName);
    }
}
