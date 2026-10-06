package petcare.model;

public class Bird extends Pet {

    public Bird() {
        super();
    }

    public Bird(String petId, String name, String breed, int age,
                String gender, double basePrice, String status) {

        super(petId, name, breed, age, gender, basePrice, status);
    }

    @Override
    public double calculateAdoptionFee() {

        return getBasePrice() + 500;
    }
    
    public double calculateAdoptionFee(double discount) {
    	double price = getBasePrice()+500;
    	return price - (price*discount/100);
    }
    public double getFinalAdoptionFee() {

        if (getAge() > 6) {
            return calculateAdoptionFee(15);
        }
        else
        	return calculateAdoptionFee();
    }

}