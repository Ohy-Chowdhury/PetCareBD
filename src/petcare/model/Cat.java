package petcare.model;

public class Cat extends Pet {

    public Cat() {
        super();
    }

    public Cat(String petId, String name, String breed, int age,
               String gender, double basePrice, String status) {

        super(petId, name, breed, age, gender, basePrice, status);
    }

    @Override
    public double calculateAdoptionFee() {

        return getBasePrice() + 1000;
    }
    
    public double calculateAdoptionFee(double discount) {
    	double price = getBasePrice()+1000;
    	return price - (price*discount/100);
    }
    public double getFinalAdoptionFee() {

        if (getAge() > 6) {
            return calculateAdoptionFee(10);
        }
        else 
        	return calculateAdoptionFee();
    }

}