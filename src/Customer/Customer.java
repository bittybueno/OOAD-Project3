package Customer;

import Rental.RentalRecord;

import java.util.ArrayList;

public abstract class Customer {

    // abstract class for customer

    public String name;
    public String type;
    public ArrayList<RentalRecord> activeRentals;
    public int numRentals;


    public Customer(String name, String type){

        if (name == "") {
            throw new IllegalArgumentException("No inputted name.");
        }

        this.name = name;
        this.type = type;
        this.activeRentals = new ArrayList<RentalRecord>();
        this.numRentals = 0;

    }

    // subclasses to define valid rental times and
    // rental sizes based on which type of customer they are
    public abstract int getValidRentalTime();
    public abstract int getValidRentalSize();
}

