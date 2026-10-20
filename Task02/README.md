# Task 2 – Choose the Data Representation

## Aim
To select suitable Java data types and array structures for the Campus Smart-Parking System.

## 1. Data Types

| Data | Java Data Type | Reason |
|---|---|---|
| Floor number | `int` | Stores a whole number |
| Slot number | `int` | Stores a whole number |
| Vehicle registration number | `String` | Contains letters and numbers |
| Entry time | `LocalDateTime` | Stores date and time |
| Parking duration | `long` | Stores duration in minutes |
| Parking fee | `double` | Stores decimal values |
| Slot occupancy | `String` | Stores a vehicle number or `null` |

## 2. Array Representation

A jagged two-dimensional array is suitable because each floor can have a different number of slots.

```java
String[][] slots = new String[3][];
slots[0] = new String[20];
slots[1] = new String[30];
slots[2] = new String[50];
```

Each row represents a floor, and each element represents a slot. A `null` value indicates a free slot.

## 3. Type Conversion and Precision

Integer division can produce an incorrect fee.

For example:

```java
int minutes = 90;
int hours = minutes / 60;
System.out.println(hours);
```

Output:

```text
1
```

The result is incorrect for a rule that charges every started hour in full, because 90 minutes should count as 2 billable hours.

Correct code:

```java
int minutes = 90;
int hours = (minutes + 59) / 60;
System.out.println(hours);
```

Output:

```text
2
```

This calculates the number of started hours using integer arithmetic.

## 4. Input Method

The `Scanner` class can read input from the keyboard.

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
System.out.print("Enter vehicle number: ");
String vehicleNo = sc.nextLine();
System.out.println("Vehicle number: " + vehicleNo);
```

## 5. Alternative Concept and Complexity Analysis

A one-dimensional array can represent all slots when every floor has the same number of slots.

The index can be calculated as:

`index = floor * slotsPerFloor + slot`

However, this formula does not directly work for floors with different numbers of slots. A jagged array is simpler for that situation.

An `ArrayList<String[]>` can also store a separate array for each floor.

| Representation | Slot access | Space |
|---|---|---|
| Jagged array | O(1) | O(S) |
| One-dimensional array | O(1) | O(S) |
| `ArrayList<String[]>` | O(1) indexed access | O(S), plus collection overhead |

Here, S is the total number of slots. Locating a free slot by scanning may take O(S) time in the worst case for each representation.

## Conclusion

A jagged two-dimensional array is suitable because different floors can contain different numbers of parking slots. Appropriate data types and correct integer arithmetic help prevent invalid data and incorrect parking fees.
