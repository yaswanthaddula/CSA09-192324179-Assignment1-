# Task 7 – Method Overriding and Object Methods

## Aim
To understand method overriding, the `Object` class methods, the `final` keyword, and alternative approaches in Java.

## 1. Method Overriding

Method overriding occurs when a child class provides its own implementation of a method inherited from a parent class.

Example:

```java
class Vehicle {
    void displayType() {
        System.out.println("General Vehicle");
    }
}

class Car extends Vehicle {
    @Override
    void displayType() {
        System.out.println("Car");
    }
}
```

When `displayType()` is called on a `Car` object, the output is:

```text
Car
```

## 2. Object Class Methods

Every Java class inherits methods from the `Object` class.

- `toString()` returns a string representation of an object.
- `equals()` checks whether two objects are considered equal.
- `hashCode()` returns a hash code used by hash-based collections.

For the parking system, `toString()` can display vehicle details, while `equals()` can compare ticket or vehicle identifiers.

## 3. The `final` Keyword

The `final` keyword can prevent changes:

- A final variable cannot be reassigned after initialization.
- A final method cannot be overridden.
- A final class cannot be extended.

For example, a ticket ID can be declared `final` so that it cannot be changed after the ticket is created.

## 4. Alternative Approach

Instead of overriding a method in every subclass, a program could use conditional statements to check the vehicle type and display the appropriate details.

**Advantage:** Simple for a very small number of vehicle types.

**Disadvantage:** More conditions are needed when new vehicle types are added. Method overriding generally makes the design easier to extend.

## 5. Complexity Analysis

Simple overridden methods such as `displayType()` take O(1) time. The exact complexity of `equals()`, `toString()`, and `hashCode()` depends on their implementations.

## Conclusion

Method overriding allows different vehicle classes to provide their own behavior. The `Object` methods help represent and compare objects, while `final` protects values and class designs from unwanted changes.
