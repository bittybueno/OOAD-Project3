package Customer;

import Rental.RentalRecord;

public class Regular extends Customer{
    public RentalRecord activeRental;

    public Regular(String name) {
        super(name, "Regular");
    }

    public void setActiveRental(RentalRecord activeRental) {
        this.activeRental = activeRental;
    }

    public  int getValidRentalTime(){
        int r = (int) (Math.random() * (6 - 3)) + 3;
        return r;
    }

    public  int getValidRentalSize(){
        int r = (int) (Math.random() * (4 - 1)) + 1;
        int ret;
        if (r +  this.numRentals > 3 ) {
            return 3-this.numRentals;
        }
        return r;
    }
}
