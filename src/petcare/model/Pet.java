package petcare.model;

import petcare.exception.*; 
import petcare.interfaces.Adoptable;
import petcare.util.PetStatus;

public abstract class Pet implements Adoptable {

	private String petID;
	private String name;
	private String breed;
	private int age;
	private String gender;
	private double basePrice;
	private String status;
	
	public Pet () {
		
	}
	
	public Pet (String petID, String name, String breed, int age,
				String gender, double basePrice, String status) {
		this.petID=petID;
		this.name=name;
		this.breed=breed;
		this.age=age;
		this.gender=gender;
		this.basePrice=basePrice;
		this.status=status;
	}
	
	public String getPetID () {
		return petID;
	}
	
	public void setPetID (String petID) {
		this.petID=petID;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws InvalidPetAgeException {

        if (age <= 0) {
            throw new InvalidPetAgeException("Pet age must be greater than 0.");
        }
        else
        	this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Base price cannot be negative.");
        }
        this.basePrice = basePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public abstract double calculateAdoptionFee();
    
    public abstract double calculateAdoptionFee(double discount);
    
    public abstract double getFinalAdoptionFee();
    
    
    public String toString() {
        return "Pet ID: " + petID + "\n" +
               "Name: " + name + "\n" +
               "Breed: " + breed + "\n" +
               "Age: " + age + "\n" +
               "Gender: " + gender + "\n" +
               "Type: " + getClass().getSimpleName() + "\n" +
               "Status: " + status + "\n" +
               "Adoption Fee: " + getFinalAdoptionFee();
    }
    
    @Override
    public void adopt() {
    	status = PetStatus.ADOPTED;
    }

    @Override
    public boolean isAdopted() {
    	return status.equals(PetStatus.ADOPTED);
    }
}
