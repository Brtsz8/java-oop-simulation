# 2D WORLD SIMULATION - JAVA IMPLEMENTATION
![image](https://github.com/user-attachments/assets/2ef56223-bf06-4047-97e5-6b4ac075d9cf)

This Java project is an academic implementation of a **2D turn-based world simulation**, where organisms such as animals and plants interact on a grid-based map. The simulation focuses on **object-oriented programming principles**, such as polymorphism, inheritance, and encapsulation.

## 🧠 Core Concepts

- Organisms live, move, fight, reproduce, and die in a 2D grid-based world.
- The world progresses in **discrete turns**.
- Each turn, organisms act based on **initiative** and **age**.
- The simulation uses a Java Swing to display the state of the world and interaction logs.

---

## ✅ Features Implemented

### 🔁 Turn-Based Simulation
- Organisms are sorted each turn by **initiative** and **age**.
- Every living organism executes its `akcja()` method.
- Dead organisms are removed after the turn ends.

### 🧬 Polymorphic Organism Behavior
- Base class: `Organizm`
- Derived classes:
  - **Animals**: `Wilk`, `Owca`, `Zolw`, `Lis`, `Antylopa`, `Czlowiek`
  - **Plants**: `Trawa`, `Mlecz`, `Guarana`, `Wilcze Jagody`, `Barszcz Sosnowskiego`
- Behavior is species-specific:
  - Movement and collision logic are overridden via polymorphism.
  - Some organisms (e.g., `Zolw`, `Lis`) have custom defense or movement logic.

### ❤️‍🔥 Reproduction System
- Organisms search for empty neighboring tiles to reproduce.
- Offspring inherit basic traits (position, strength).
- Plants reproduce passively; animals do so based on interaction.
  
### 🧍‍♂️ Human-Controlled Organism
- Special unit: `Czlowiek`
- Controlled by W/A/S/D keys.
- Has a **special ability** that can be manually activated.
- Cooldown mechanics included.

### 💾 Save & Load
- Game state can be **saved to** and **loaded from** a text file.
- Saves include all organism types, positions, and strength.
- Log window contents are also preserved.

### 📜 Logging System
- In-game actions are logged and shown in a scrollable pane.
- Logs include collisions, births, deaths, special abilities, and more.

---

## 🖥️ Technologies Used

- **Java** (OOP design, collections, file I/O)
- **Swing (Java)** – for UI
- **Object-Oriented Design Patterns**: polymorphism, encapsulation, strategy

---
## 🎮 Controls

| Action               | Key              |
|----------------------|------------------|
| Move Human           | `w` `a` `s` `d`  |
| Activate Ability     | `3`              |
| Save Game            | `z`              |
| Load Game            | `x`              |
| Scroll Logs          | `1` / `2`        |

---

