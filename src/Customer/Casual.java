package Customer;

import Rental.RentalRecord;

import java.util.ArrayList;

public class Casual extends Customer{
//    public String type;

    public Casual(String name) {
        super(name, "Casual");
    }

    public  int getValidRentalTime(){
        int r = (int) (Math.random() * (3 - 1)) + 1;
        return r;
    }

    public  int getValidRentalSize(){
        int r = (int) (Math.random() * (3 - 1)) + 1;
        if (r + this.numRentals > 3) {
            return 1;
        }
        return r;
    }

}
