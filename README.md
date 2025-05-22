# 2D WORLD SIMULATION - JAVA IMPLEMENTATION
![image](https://github.com/user-attachments/assets/2ef56223-bf06-4047-97e5-6b4ac075d9cf)

This Java project is a partial implementation of a virtual world simulation, designed for an academic assignment. It implements the core logic for the organisms and their interactions in a 2D grid-based world.

Features Implemented
Organism Interaction and Movement

 - Each organism (animal or plant) is represented as an object with attributes such as position, strength, initiative, and age.

 - Movement is executed on each turn based on initiative and age sorting rules.

 - Collisions between organisms are handled, allowing for interaction logic (e.g., combat or reproduction).

 - Organism behavior is polymorphic, allowing subclasses to override movement and collision logic.

Reproduction Mechanism

 - Reproduction logic is implemented for eligible organisms.

 - Organisms search for free adjacent tiles to place offspring.

 - Reproduction happens during the organism's action cycle, respecting species-specific rules.

 - New organisms inherit basic characteristics from parents.# java-oop-simulation
implementation of a simulation game in java
