package Customer;

import Rental.RentalRecord;

public class Business extends Customer{
    public String type;
    public RentalRecord activeRental;

    public Business(String name) {

        super(name, "Business");
    }

    public void setActiveRental(RentalRecord activeRental) {
        this.activeRental = activeRental;
    }

    public  int getValidRentalTime(){
        return 7;
    }

    public  int getValidRentalSize(){
        return 3;
    }
}
