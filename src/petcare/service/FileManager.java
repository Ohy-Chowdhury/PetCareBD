package petcare.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import petcare.model.Cat;
import petcare.model.Dog;
import petcare.model.Bird;
import petcare.model.Pet;

public class FileManager {

	private static final String FILE_NAME = "pets.txt";

    public void savePets(ArrayList<Pet> pets) {

        try {
            FileWriter myWriter = new FileWriter(FILE_NAME);

            for (Pet pet : pets) {

                myWriter.write(
                    pet.getClass().getSimpleName() + "," +
                    pet.getPetID() + "," +
                    pet.getName() + "," +
                    pet.getBreed() + "," +
                    pet.getAge() + "," +
                    pet.getGender() + "," +
                    pet.getBasePrice() + "," +
                    pet.getStatus() + "\n"
                );
            }

            myWriter.close();

            System.out.println("Pets saved successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while saving pets.");
        }
    }

    public ArrayList<Pet> loadPets() {

        ArrayList<Pet> pets = new ArrayList<Pet>();

        try {

            File myObj = new File(FILE_NAME);

            if (!myObj.exists()) {
                return pets;
            }

            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                String[] parts = data.split(",");

                if (parts.length < 8) {
                    continue;
                }

                String type = parts[0];
                String petID = parts[1];
                String name = parts[2];
                String breed = parts[3];
                int age = Integer.parseInt(parts[4]);
                String gender = parts[5];
                double basePrice = Double.parseDouble(parts[6]);
                String status = parts[7];

                Pet pet = null;

                if (type.equals("Dog")) {
                    pet = new Dog(petID, name, breed, age, gender, basePrice, status);

                } else if (type.equals("Cat")) {
                    pet = new Cat(petID, name, breed, age, gender, basePrice, status);

                } else if (type.equals("Bird")) {
                    pet = new Bird(petID, name, breed, age, gender, basePrice, status);
                }

                if (pet != null) {
                    pets.add(pet);
                }
            }

            myReader.close();

        } catch (IOException e) {

            System.out.println("An error occurred while reading the file.");

        } catch (NumberFormatException e) {

            System.out.println("Invalid data found in file.");

        }

        return pets;
    }
}