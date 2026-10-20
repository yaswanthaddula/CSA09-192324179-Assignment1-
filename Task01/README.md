# Task 1 – Understand the Problem and Identify the Objects

## Aim
To analyse the Campus Smart-Parking System and identify its inputs, outputs, candidate classes, responsibilities, business rules and assumptions.

## Problem Analysis
The system manages vehicles entering and leaving a multi-level campus parking facility. It allocates slots, issues tickets, records entry times and calculates parking fees.

## Inputs
- Vehicle registration number
- Vehicle type
- Entry and exit times
- Floor and slot details
- Monthly pass details

## Outputs
- Allocated floor and slot
- Unique parking ticket
- Parking duration
- Parking fee
- Slot availability

## Candidate Classes
- Vehicle
- TwoWheeler
- Car
- ElectricCar
- ParkingSlot
- ParkingFloor
- ParkingTicket
- ParkingFacility
- ParkingPass
- FeeCalculator

## Business Rules
1. A slot cannot hold two vehicles simultaneously.
2. Each ticket must have a unique number.
3. Allocation fails when no suitable slot is free.
4. Released slots become available again.
5. Invalid floor and slot numbers must be rejected.
6. Valid monthly passes allow eligible students and staff to park free.
7. Visitors pay the applicable parking fee.
8. EV charging fees are calculated separately.

## Assumptions
- Array indexes start at zero.
- A free slot is represented by `null`.
- Registration numbers identify vehicles.
- Parking fees depend on duration and vehicle type.

## Alternative Concept
Vehicle objects group related data together. Parallel arrays store registration numbers, vehicle types and entry times separately.

Both approaches require O(n) time for a linear search and O(n) space for n vehicles. Objects are easier to maintain when new attributes are added.

## Conclusion
An object-oriented design makes the parking system easier to maintain, extend and validate.

