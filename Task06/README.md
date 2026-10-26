# Task 6 – Inheritance and UML Class Diagram

## Aim
To understand inheritance, the `super` keyword, and composition in the Campus Smart-Parking System.

## 1. Inheritance

Inheritance allows one class to reuse the properties and methods of another class.

Example:
- `Vehicle` is the parent class.
- `Car` extends `Vehicle`.
- `TwoWheeler` extends `Vehicle`.
- `ElectricCar` extends `Car`.

This structure avoids repeating common vehicle details.

## 2. Role of the `super` Keyword

The `super` keyword refers to the parent class.

Example:

```java
class Vehicle {
    String registrationNumber;

    Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}

class Car extends Vehicle {
    Car(String registrationNumber) {
        super(registrationNumber);
    }
}
```

The `super(registrationNumber)` statement calls the parent class constructor.

## 3. UML Class Diagram

```text
          Vehicle
             |
      ----------------
      |              |
   TwoWheeler       Car
                       |
                  ElectricCar
```

The diagram shows the inheritance relationship between the classes.

## 4. Composition Alternative

Instead of inheritance, a vehicle could contain a separate `VehicleType` object that stores its type and hourly rate.

**Advantage:** Vehicle types and rates can be changed more flexibly.

**Disadvantage:** Additional objects and code are required to manage the relationship.

## 5. Complexity Analysis

Inheritance itself does not significantly change the time complexity of accessing a field or calling a method. Ordinary field access and method calls are generally O(1).

## Conclusion

Inheritance supports code reuse and represents relationships between vehicle types. The `super` keyword initializes inherited data through the parent constructor. Composition is an alternative when flexible relationships are more important than a fixed class hierarchy.
