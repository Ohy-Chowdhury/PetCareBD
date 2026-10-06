# PetCareBD – Smart Pet Adoption & Care Management System

A Java-based pet adoption and care management system developed as a CSE coursework project.

## Features

- Add and view pets through a simple Java Swing GUI
- Search pets by Pet ID
- Adopt available pets
- Store and load pet information using a text file
- Calculate adoption fees based on pet type
- Apply age-based discounts through method overloading
- Handle invalid pet age and already-adopted cases with custom exceptions

## Technologies

- Java
- Java Swing
- Object-Oriented Programming (OOP)
- File Handling
- Exception Handling

## OOP Concepts Demonstrated

- **Encapsulation** – private fields with getters and setters
- **Inheritance** – `Dog`, `Cat`, and `Bird` extend `Pet`
- **Abstraction** – abstract `Pet` class and `Adoptable` interface
- **Polymorphism** – overridden adoption-fee methods
- **Method Overloading** – `calculateAdoptionFee()` and `calculateAdoptionFee(double discount)`
- **Method Overriding** – pet-specific adoption-fee calculations

## Project Structure

```text
PetCareBD/
├── src/
│   └── petcare/
│       ├── app/
│       ├── exception/
│       ├── interfaces/
│       ├── model/
│       ├── service/
│       ├── ui/
│       └── util/
├── pets.txt
├── .gitignore
└── README.md
```

## How to Run

1. Open the project in Eclipse or another Java IDE.
2. Make sure the Java source files are placed under the `src` folder.
3. Run `petcare.app.Main`.
4. The Java Swing interface will open.

## Author

**MD. Burhan Hasib Chowdhury Ohy**  
CSE Student, North South University
