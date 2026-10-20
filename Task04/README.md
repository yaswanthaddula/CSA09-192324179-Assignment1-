# Task 4 – Design Classes, Objects and Constructors

## Aim
To design classes and objects for the Campus Smart-Parking System and understand constructors, the `this` keyword and memory allocation.

## 1. Classes and Their Fields and Methods

| Class | Fields | Methods |
|---|---|---|
| Vehicle | Registration number, vehicle type | `getRegNo()` |
| ParkingSlot | Slot number, vehicle | `isAvailable()` |
| ParkingTicket | Ticket number, entry time | `getTicketNo()` |
| ParkingFacility | Floors and slots | `allocate()`, `release()` |
| ParkingPass | Pass number, validity | `isValid()` |

## 2. Constructors

A constructor initializes an object when it is created.

**Default constructor:**

```java
class ParkingTicket {
    ParkingTicket() {
        System.out.println("Ticket created");
    }
}
```

**Parameterized constructor:**

```java
class Vehicle {
    String regNo;

    Vehicle(String regNo) {
        this.regNo = regNo;
    }
}
```

**Overloaded constructors:**

A class can have multiple constructors with different parameter lists.

```java
class ParkingTicket {
    String ticketNo;

    ParkingTicket() {
        this("T001");
    }

    ParkingTicket(String ticketNo) {
        this.ticketNo = ticketNo;
    }
}
```

## 3. The `this` Keyword

The `this` keyword refers to the current object. It distinguishes an instance variable from a parameter with the same name.

Example: `this.regNo = regNo;`

Constructor chaining using `this()` avoids repeating initialization code.

## 4. Copy Constructor

A copy constructor creates an object using another object's values. It can be useful when creating a duplicate ticket record, but the copied ticket should receive a new unique ticket number.

## 5. Object Memory

- Reference variables are commonly stored in stack frames when they are local variables.
- Objects and arrays are allocated on the Java heap.
- A reference variable points to an object.
- An object becomes eligible for garbage collection when it is no longer reachable.

## 6. Alternative Concept and Complexity Analysis

Instead of constructors, objects could be created with a default constructor followed by setter calls. However, setters may allow an object to remain temporarily incomplete or invalid.

Static factory methods provide another option, such as `ParkingTicket.createTicket()`.

For creating `n` tickets, all three approaches generally require O(n) total time and O(n) space to retain the objects. Their main difference is validation, readability and control over object creation.

## Conclusion

Constructors and constructor chaining help initialize valid objects. Encapsulation and appropriate validation make the parking system safer and easier to maintain.
