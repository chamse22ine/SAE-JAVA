# SAE-Sokoban

# Sokoban Project

Java Sokoban Game: Graphical version (Swing) and Console version

---

## 📚 Overview

**Sokoban** is a classic puzzle game where a player controls a robot to push boxes onto specific target spots ("destinations"). This project, developed as part of an Object-Oriented Programming (OOP) lab at the University of Artois, features:

- A console (text-based) version  
- A graphical version (using Swing and images)

Both interfaces share the same game logic, thanks to an MVC (Model-View-Controller) architecture.

---

## 🚀 Features

- Dynamic level loading from text files (`map/map1.txt`, etc.)
- Text-based and graphical board display
- Robot movement in four directions
- Collision handling: walls, boxes, destinations
- Move counter
- Win detection (all boxes on destinations)
- Potential improvements (undo move, level loading, reset, etc.)

---

## 🗂️ Project Structure
```
.
├── images/ # Images for the graphical interface
├── map/ # Text files for levels
├── src/
│ ├── modele/ # Game logic (model: Robot, Map, Box, etc.)
│ ├── vueTexte/ # Console view and interaction
│ └── vueGraphique/ # Graphical view (Swing)
└── README.md
```


---

## ⚙️ Installation & Running

### Requirements

- **Java 17 or higher** (the project uses modern syntax such as switch expressions with arrows).

### Compilation

In the project root directory:

```bash
javac -d bin src/modele/*.java src/vueTexte/*.java src/vueGraphique/*.java
```
## Run ▶️ Running the Game

# 🖥️ Console version

```bash
java -cp bin vueTexte.SokobanTexte
```
Or directly from the JAR file:
```bash
java -jar sokobanTexteAdaadour.jar
```

# 🖼️ Graphical version

```bash
java -cp bin vueGraphique.Sokoban
```
Or from the JAR file:
```
java -jar sokobanAdaadour.jar
```

## 🕹️ Controls
# Console version (AZERTY):

q: left

d: right

z: up

s: down

# Graphical version:

Arrow keys

📄 Map Format
Example map (map/map1.txt):

```
 #######
 #     #
 #.$@$.#
 #  .  #
 #######
```
# Legend:

"#" : wall

. : destination

$ : box

@ : robot

(space) : floor

📃 License
This project is provided for educational purposes. Any use or modification is allowed for learning or academic activities.

