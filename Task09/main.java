
abstract class Vehicle {
    protected String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public abstract double calculateFee(int hours);

    public void displayRegistration() {
        System.out.println("Registration: " + registrationNumber);
    }
}

interface Chargeable {
    void chargeBattery();

    default void showChargingStatus() {
        System.out.println("Charging capability is available.");
    }

    static void chargingInformation() {
        System.out.println("Electric vehicles can use charging facilities.");
    }
}

class TwoWheeler extends Vehicle {
    public TwoWheeler(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 10.0;
    }
}

class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 30.0;
    }
}

class ElectricCar extends Vehicle implements Chargeable {
    public ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 70.0;
    }

    @Override
    public void chargeBattery() {
        System.out.println("Electric car battery is charging.");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new TwoWheeler("TN01A1");
        Vehicle v2 = new Car("TN02B2");
        Vehicle v3 = new ElectricCar("TN03C3");

        v1.displayRegistration();
        System.out.println("TwoWheeler fee: Rs " + v1.calculateFee(3));

        v2.displayRegistration();
        System.out.println("Car fee: Rs " + v2.calculateFee(3));

        v3.displayRegistration();
        System.out.println("ElectricCar fee: Rs " + v3.calculateFee(3));

        Chargeable electricVehicle = (Chargeable) v3;
        electricVehicle.chargeBattery();
        electricVehicle.showChargingStatus();

        Chargeable.chargingInformation();
    }
}
