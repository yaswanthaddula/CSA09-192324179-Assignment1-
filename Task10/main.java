
class Vehicle {
    private String registrationNumber;
    private Integer duration;
    protected String vehicleType;

    public Vehicle(String registrationNumber, Integer duration,
                   String vehicleType) {
        setRegistrationNumber(registrationNumber);
        setDuration(duration);
        this.vehicleType = vehicleType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null ||
            registrationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Invalid registration number");
        }
        this.registrationNumber = registrationNumber;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        if (duration == null || duration < 0) {
            throw new IllegalArgumentException(
                "Duration cannot be negative or null");
        }
        this.duration = duration;
    }

    void displayType() {
        System.out.println("Vehicle type: " + vehicleType);
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("TN01A1", 3, "Car");

        System.out.println("Registration: " +
            vehicle.getRegistrationNumber());
        System.out.println("Duration: " +
            vehicle.getDuration() + " hours");
        vehicle.displayType();

        // Autoboxing: int becomes Integer.
        vehicle.setDuration(4);
        System.out.println("Updated duration: " +
            vehicle.getDuration() + " hours");

        try {
            vehicle.setDuration(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " +
                e.getMessage());
        }

        // Wrapper classes: compare values with equals().
        Integer a = 1000;
        Integer b = 1000;
        System.out.println("Values equal: " + a.equals(b));
    }
}
