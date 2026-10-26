
interface Pass {
    boolean isValid();
    double getFee();
}

class OneDayPass implements Pass {
    private final String visitorName;

    OneDayPass(String visitorName) {
        this.visitorName = visitorName;
    }

    public boolean isValid() {
        return visitorName != null && !visitorName.trim().isEmpty();
    }

    public double getFee() {
        return 150.0;
    }
}

abstract class Vehicle {
    protected String registrationNumber;

    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    String getRegistrationNumber() {
        return registrationNumber;
    }

    abstract double calculateFee(int hours, int entryHour);
}

class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
    }

    double calculateFee(int hours, int entryHour) {
        return hours * 30.0;
    }
}

class ElectricCar extends Vehicle {
    ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    double calculateFee(int hours, int entryHour) {
        return hours * 70.0;
    }
}

class EBike extends Vehicle {
    EBike(String registrationNumber) {
        super(registrationNumber);
    }

    double calculateFee(int hours, int entryHour) {
        return 20.0;
    }
}

class DeliveryVan extends Vehicle {
    DeliveryVan(String registrationNumber) {
        super(registrationNumber);
    }

    double calculateFee(int hours, int entryHour) {
        double fee = 0;

        for (int i = 0; i < hours; i++) {
            int currentHour = (entryHour + i) % 24;

            if (currentHour >= 9 && currentHour < 17) {
                fee += 60.0; // Double the car rate
            } else {
                fee += 30.0;
            }
        }

        return fee;
    }
}

class ParkingTicket {
    Vehicle vehicle;
    int entryHour;
    int hours;
    Pass pass;

    ParkingTicket(Vehicle vehicle, int entryHour, int hours, Pass pass) {
        this.vehicle = vehicle;
        this.entryHour = entryHour;
        this.hours = hours;
        this.pass = pass;
    }

    double calculateFee() {
        if (pass != null) {
            return pass.getFee();
        }

        return vehicle.calculateFee(hours, entryHour);
    }
}

class ParkingLot {
    private final ParkingTicket[][] floors;

    ParkingLot(int[] slotsPerFloor) {
        floors = new ParkingTicket[slotsPerFloor.length][];

        for (int i = 0; i < slotsPerFloor.length; i++) {
            floors[i] = new ParkingTicket[slotsPerFloor[i]];
        }
    }

    boolean park(Vehicle vehicle, int floor, int slot,
                 int entryHour, int hours, Pass pass) {

        if (vehicle == null) {
            System.out.println("Invalid vehicle");
            return false;
        }

        if (entryHour < 0 || entryHour > 23 || hours < 0) {
            System.out.println("Invalid parking time");
            return false;
        }

        if (pass != null && !pass.isValid()) {
            System.out.println("Invalid visitor pass");
            return false;
        }

        for (ParkingTicket[] row : floors) {
            for (ParkingTicket ticket : row) {
                if (ticket != null &&
                    ticket.vehicle.getRegistrationNumber()
                        .equals(vehicle.getRegistrationNumber())) {
                    System.out.println("Duplicate registration");
                    return false;
                }
            }
        }

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

        floors[floor - 1][slot - 1] =
            new ParkingTicket(vehicle, entryHour, hours, pass);

        System.out.println(vehicle.getClass().getSimpleName()
            + " parked on floor " + floor + ", slot " + slot);

        return true;
    }

    double exit(String registrationNumber) {
        for (int f = 0; f < floors.length; f++) {
            for (int s = 0; s < floors[f].length; s++) {
                ParkingTicket ticket = floors[f][s];

                if (ticket != null &&
                    ticket.vehicle.getRegistrationNumber()
                        .equals(registrationNumber)) {

                    double fee = ticket.calculateFee();
                    floors[f][s] = null;

                    System.out.println(registrationNumber
                        + " exited. Fee: Rs. " + fee);

                    return fee;
                }
            }
        }

        System.out.println("Vehicle not found: " + registrationNumber);
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] slotsPerFloor = {2, 3, 2, 4, 3};
        ParkingLot lot = new ParkingLot(slotsPerFloor);

        System.out.println("=== Fee rule tests ===");
        System.out.println("Car fee (2 hours): Rs. "
            + new Car("TEST01").calculateFee(2, 10));
        System.out.println("E-bike flat fee: Rs. "
            + new EBike("TEST02").calculateFee(2, 10));
        System.out.println("Van fee (8 AM, 2 hours): Rs. "
            + new DeliveryVan("TEST03").calculateFee(2, 8));
        System.out.println("Van fee (4 PM, 2 hours): Rs. "
            + new DeliveryVan("TEST04").calculateFee(2, 16));

        System.out.println("\n=== Parking and validation tests ===");

        lot.park(new Car("TN01A1"), 1, 1, 8, 2, null);

        // Duplicate registration
        lot.park(new Car("TN01A1"), 1, 2, 10, 1, null);

        // Visitor buys a one-day pass
        lot.park(new Car("VIS01"), 1, 2, 10, 3,
            new OneDayPass("University Visitor"));

        // Invalid and occupied slots
        lot.park(new EBike("TN02B2"), 1, 3, 10, 1, null);
        lot.park(new EBike("TN02B2"), 1, 1, 10, 1, null);

        // New vehicle categories on other floors
        lot.park(new DeliveryVan("TN03C3"), 5, 3, 16, 2, null);
        lot.park(new EBike("TN04D4"), 3, 2, 11, 2, null);

        // Zero-hour parking
        lot.park(new Car("TN05E5"), 2, 1, 12, 0, null);

        System.out.println("\n=== Exit and fee collection ===");
        double total = 0;
        total += lot.exit("TN01A1");
        total += lot.exit("VIS01");
        total += lot.exit("TN03C3");
        total += lot.exit("TN04D4");
        total += lot.exit("TN05E5");

        System.out.println("Total collection: Rs. " + total);

        System.out.println("\n=== Design impact analysis ===");
        System.out.println("New vehicle types use separate fee rules.");
        System.out.println("Visitor passes are integrated into fee calculation.");
        System.out.println("Different floors can have different slot capacities.");
        System.out.println("Time-based van fees are calculated hour by hour.");
    }
}
