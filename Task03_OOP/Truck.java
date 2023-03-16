package Task03_OOP;

public class Truck extends Vehicle{
    private int bedLength;
    private double payloadCapacity;

    public Truck(String make, String model, int year, String color, double price, int bedLength, double payloadCapacity) {
        super(make, model, year, color, price);
        this.bedLength = bedLength;
        this.payloadCapacity = payloadCapacity;
    }

    public int getBedLength() {
        return bedLength;
    }

    public void setBedLength(int bedLength) {
        this.bedLength = bedLength;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }

    @Override
    public void showDetails() {
        System.out.println("\nTruck Details" +
                "\nMake: " + getMake() +
                "\nModel: " + getModel() +
                "\nYear: " + getYear() +
                "\nColor: " + getColor() +
                "\nPrice: " + getPrice() +
                "\nBed Length: " + getBedLength() +
                "\nPayload Capacity: " + getPayloadCapacity()
        );
    }
}
