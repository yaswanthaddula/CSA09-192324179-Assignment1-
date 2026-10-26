
abstract class Vehicle {
    protected String registrationNumber;

    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    abstract double calculateFee(int hours, int entryHour);
}

class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int hours, int entryHour) {
        return hours * 30.0;
    }
}

class ElectricCar extends Vehicle {
    ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int hours, int entryHour) {
        return hours * 70.0;
    }
}

class EBike extends Vehicle {
    EBike(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int hours, int entryHour) {
        return 20.0; // Flat fee
    }
}

class DeliveryVan extends Vehicle {
    DeliveryVan(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    double calculateFee(int hours, int entryHour) {
        if (entryHour >= 9 && entryHour < 17) {
            return hours * 60.0; // Double the car rate
        }
        return hours * 30.0;
    }
}

interface Pass {
    boolean isValid();
}

class OneDayPass implements Pass {
    private final String visitorName;

    OneDayPass(String visitorName) {
        this.visitorName = visitorName;
    }

    @Override
    public boolean isValid() {
        return visitorName != null && !visitorName.trim().isEmpty();
    }
}

class ParkingLot {
    private final Vehicle[][] floors;

    ParkingLot(int[] slotsPerFloor) {
        floors = new Vehicle[slotsPerFloor.length][];
        for (int i = 0; i < slotsPerFloor.length; i++) {
            floors[i] = new Vehicle[slotsPerFloor[i]];
        }
    }

    boolean park(Vehicle vehicle, int floor, int slot) {
        if (floor < 1 || floor > floors.length) {
            System.out.println("Invalid floor");
            return false;
        }

        if (slot < 1 || slot > floors[floor - 1].length) {
            System.out.println("Invalid slot");
            return false;
        }

        if (floors[floor - 1][slot - 1] != null) {
            System.out.println("Slot occupied");
            return false;
        }

        floors[floor - 1][slot - 1] = vehicle;
        System.out.println(vehicle.getClass().getSimpleName()
            + " parked on floor " + floor + ", slot " + slot);
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] slotsPerFloor = {2, 3, 2, 4, 3};
        ParkingLot lot = new ParkingLot(slotsPerFloor);

        Vehicle car = new Car("TN01A1");
        Vehicle ebike = new EBike("TN02B2");
        Vehicle van = new DeliveryVan("TN03C3");

        System.out.println("=== New vehicle categories ===");
        System.out.println("Car fee (2 hours): Rs. "
            + car.calculateFee(2, 10));
        System.out.println("E-bike flat fee: Rs. "
            + ebike.calculateFee(2, 10));
        System.out.println("Delivery van fee (2 hours, 10 AM): Rs. "
            + van.calculateFee(2, 10));
        System.out.println("Delivery van fee (2 hours, 6 PM): Rs. "
            + van.calculateFee(2, 18));

        System.out.println("\n=== Five-floor parking ===");
        lot.park(car, 1, 1);
        lot.park(ebike, 3, 2);
        lot.park(van, 5, 3);

        System.out.println("\n=== Visitor one-day pass ===");
        Pass pass = new OneDayPass("University Visitor");
        System.out.println("Pass valid: " + pass.isValid());
    }
}
