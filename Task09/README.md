# Task 9 – Introduce Abstraction

## Aim

To implement abstraction in the Campus Smart-Parking System using an abstract class and an interface.

## 1. Abstract Class

The `Vehicle` class is abstract because it represents a general vehicle. It contains the common registration number and the abstract method `calculateFee(int hours)`.

The subclasses `TwoWheeler`, `Car`, and `ElectricCar` provide their own implementations of the fee-calculation method.

## 2. Interface

The `Chargeable` interface represents the ability to charge a battery. The `ElectricCar` class implements this interface because it supports battery charging.

The interface contains:
- An abstract method: `chargeBattery()`
- A default method: `showChargingStatus()`
- A static method: `chargingInformation()`

## 3. Why an Abstract Class and an Interface Are Used

An abstract class is suitable for `Vehicle` because all vehicles share common state and behaviour. Its constructor initializes the registration number.

An interface is suitable for `Chargeable` because charging is an optional capability. For example, an electric car and a future electric bike could both implement this interface without sharing the same immediate superclass.

Java allows a class to extend one class and implement multiple interfaces. For example, an electric delivery van could extend `Vehicle` and implement both `Chargeable` and another interface such as `Insurable`.

## 4. Alternative Design

If `Vehicle` became an interface, it could declare fee-calculation and registration-related methods, with default methods for shared behaviour. However, interfaces cannot have constructors or ordinary per-object instance fields.

If `Chargeable` became an abstract class, its constructor and instance state would be supported. However, a class could no longer extend another class in addition to `Chargeable`, because Java supports single class inheritance.

Therefore, the original design is more suitable for sharing common vehicle state while allowing independent capabilities through interfaces.

## 5. Complexity Analysis

| Operation | Time Complexity |
|---|---|
| Display registration | O(1) |
| Calculate parking fee | O(1) |
| Charge battery | O(1) |
| Store n vehicle objects | O(n) total space |

The time complexity of each method is constant in this implementation. Actual method-call performance may vary due to JVM optimisations. Swapping the abstract class and interface does not automatically change the asymptotic time complexity or object space usage.

## Conclusion

The design demonstrates abstraction through the `Vehicle` abstract class and the `Chargeable` interface. It also supports inheritance and polymorphism by allowing different vehicle types to calculate their fees using the same method signature.
