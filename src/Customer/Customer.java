package Customer;

import Rental.RentalRecord;

import java.util.ArrayList;

public abstract class Customer {
    public String name;
    public String type;
    public ArrayList<RentalRecord> activeRentals;
    public int numRentals;


    public Customer(String name, String type){

        if (name == "") {
            System.out.println("No inputted name.");
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.type = type;
        this.activeRentals = new ArrayList<RentalRecord>();
        this.numRentals = 0;

    }

    public abstract int getValidRentalTime();
    public abstract int getValidRentalSize();
}

