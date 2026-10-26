
import java.util.Arrays;

class ParkingTicket {
    String registrationNumber;
    String vehicleType;
    int slotNumber;
    int hours;

    ParkingTicket(String registrationNumber, String vehicleType,
                  int slotNumber, int hours) {
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.slotNumber = slotNumber;
        this.hours = hours;
    }

    double calculateFee() {
        if (vehicleType.equals("TwoWheeler")) {
            return hours * 10.0;
        } else if (vehicleType.equals("Car")) {
            return hours * 30.0;
        } else if (vehicleType.equals("ElectricCar")) {
            return hours * 70.0;
        }
        throw new IllegalArgumentException("Unknown vehicle type");
    }
}

class ParkingLot {
    private final ParkingTicket[] activeTickets;
    private double totalCollection;

    ParkingLot(int capacity) {
        activeTickets = new ParkingTicket[capacity];
        totalCollection = 0;
    }

    boolean park(ParkingTicket ticket) {
        if (!Arrays.asList(activeTickets).contains(null)) {
            System.out.println("Parking full");
            return false;
        }

        for (ParkingTicket active : activeTickets) {
            if (active != null &&
                active.registrationNumber.equals(
                    ticket.registrationNumber)) {
                System.out.println("Duplicate registration");
                return false;
            }
        }

        if (ticket.slotNumber < 1 ||
            ticket.slotNumber > activeTickets.length) {
            System.out.println("Invalid slot number");
            return false;
        }

        int index = ticket.slotNumber - 1;
        if (activeTickets[index] != null) {
            System.out.println("Slot already occupied");
            return false;
        }

        if (ticket.hours < 0) {
            System.out.println("Invalid parking duration");
            return false;
        }

        activeTickets[index] = ticket;
        System.out.println(ticket.vehicleType + " parked in slot "
                           + ticket.slotNumber);
        return true;
    }

    boolean exit(String registrationNumber) {
        for (int i = 0; i < activeTickets.length; i++) {
            ParkingTicket ticket = activeTickets[i];

            if (ticket != null &&
                ticket.registrationNumber.equals(registrationNumber)) {
                double fee = ticket.calculateFee();
                totalCollection += fee;

                System.out.println(ticket.registrationNumber
                    + " exited. Fee: Rs. " + fee);

                activeTickets[i] = null;
                return true;
            }
        }

        System.out.println("Ticket not found");
        return false;
    }

    double getTotalCollection() {
        return totalCollection;
    }

    void showFreeSlots() {
        int free = 0;
        for (ParkingTicket ticket : activeTickets) {
            if (ticket == null) {
                free++;
            }
        }
        System.out.println("Free slots: " + free);
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2);

        System.out.println("=== Normal operation ===");
        lot.park(new ParkingTicket("TN01A1", "TwoWheeler", 1, 3));
        lot.park(new ParkingTicket("TN02B2", "Car", 2, 3));
        lot.showFreeSlots();

        System.out.println("\n=== Boundary: last free slot ===");
        lot.exit("TN01A1");
        lot.park(new ParkingTicket("TN03C3", "ElectricCar", 1, 3));

        System.out.println("\n=== Invalid cases ===");
        lot.park(new ParkingTicket("TN03C3", "Car", 2, 1));
        lot.park(new ParkingTicket("TN04D4", "Car", 5, 1));
        lot.park(new ParkingTicket("TN05E5", "Bus", 2, 1));

        System.out.println("\n=== Zero-minute parking ===");
        lot.exit("TN03C3");
        lot.park(new ParkingTicket("TN06F6", "Car", 1, 0));
        lot.exit("TN06F6");

        System.out.println("\nTotal collection: Rs. "
                           + lot.getTotalCollection());
        lot.showFreeSlots();
    }
}
