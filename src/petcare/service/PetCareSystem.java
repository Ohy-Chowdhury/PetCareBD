package petcare.service;

import java.util.ArrayList;
import petcare.exception.*;
import petcare.model.Pet;

public class PetCareSystem {

    private ArrayList<Pet> petList;
    private FileManager fileManager;

    public PetCareSystem() {

        petList = new ArrayList<Pet>();

        fileManager = new FileManager();
        loadAllPets();
    }

    public void addPet(Pet pet) {

        petList.add(pet);

    }

    public void viewPets() {

        if (petList.isEmpty()) {

            System.out.println("No Pets Available.");

            return;
        }

        for (Pet pet : petList) {

            System.out.println("Fee : " + pet.calculateAdoptionFee());

        }

    }

    public Pet searchPet(String petId) {

        for (Pet pet : petList) {

            if (pet.getPetID().equalsIgnoreCase(petId)) {

                return pet;

            }

        }

        return null;

    }
    
    public void adoptPet(String petID) throws AlreadyAdoptedException {

        Pet pet = searchPet(petID);

        if (pet == null) {

            System.out.println("Pet Not Found.");

            return;

        }
        
        
        if (pet.isAdopted()) {

        	throw new AlreadyAdoptedException("This pet is already adopted.");
        }

        pet.adopt();

        fileManager.savePets(petList);

        System.out.println("Pet adopted successfully.");

    }
    
    public void saveAllPets() {

        fileManager.savePets(petList);

    }
    
    public void loadAllPets() {

        petList = fileManager.loadPets();

    }
      
    public ArrayList<Pet> getPetList() {

        return petList;

    }
}

