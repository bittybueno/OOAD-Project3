package Rental;

import Customer.*;
import Tools.*;
import HardwareRentalStore.*;
import Tools.ToolDecorator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;


/* Facade in update() and expiredRental()*/

public class RentalRecord {
    public Customer customer;
    public ArrayList<Tool> rentedTools;
    public ArrayList<Tool> rentedToolsNoDec;
    public int numOfDays;
    public int numOfTools;
    public double fee;
    public HardwareRentalStore hardwareRentalStore;

    public RentalRecord(Customer customer, HardwareRentalStore hardwareRentalStore) {
        this.numOfTools = customer.getValidRentalSize();
        ArrayList<Tool> availableTools = hardwareRentalStore.availableTools();
        Map <Integer, ArrayList<Tool>> tools = getRandomAvailableTools(this.numOfTools, availableTools);
        this.rentedTools = tools.get(0);
        this.rentedToolsNoDec = tools.get(1);
        this.numOfDays = customer.getValidRentalTime();
        this.fee = getTotalCost(rentedTools);
        this.customer = customer;
        this.hardwareRentalStore = hardwareRentalStore;
    }

    /*
    Facade:
    - when a rental is created, many subsets within the system need to be updated. By
    creating an 'update' method, we are exposing a simple interface that handles all
    of the actions that must exist by the creation of a new rental.
     */

    public void update() {
        this.hardwareRentalStore.updateInventoryRent(this.rentedToolsNoDec);
        this.hardwareRentalStore.updateCustomerHistory(this);
        this.customer.activeRentals.add(this);
        this.customer.numRentals = this.customer.numRentals + this.numOfTools;
        this.numOfDays -= 1;

    }

    /*
    Facade:
    - when a rental is returned, many subsets within the system need to be updated. By
    creating a 'expiredRental' method, we are exposing a simple interface that handles all
    of the actions that must exist by the return of a  rental.
     */

    public void expiredRental() {
        this.hardwareRentalStore.oldRentals.add(this);
        this.hardwareRentalStore.activeRentals.remove(this);
        this.hardwareRentalStore.updateInventoryReturn(this.rentedToolsNoDec);
        this.customer.activeRentals.remove(this);
        this.customer.numRentals = this.customer.numRentals - this.numOfTools;
    }

    public void decreaseRentalDay() {
        this.numOfDays -= 1;
    }

    public void printRentalDesc(String id) {
        DecimalFormat df = new DecimalFormat("####.##");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("\n          ==========" + id + " Rental ==========\n");
        System.out.println("          | Customer: " + customer.name + " -- " + customer.type);
        double totalCost = 0;
        for (Tool tool : this.rentedTools) {
            System.out.println("          | Tool: " + tool.getDescription());
            totalCost = totalCost + tool.cost();
        }
        System.out.println("          | Days: " + this.numOfDays);
        System.out.println("          | Total Cost: $" + df.format(totalCost));
        System.out.println("\n          ============================\n");
    }

    public double getTotalCost(ArrayList<Tool> tools) {
        double cost = 0;
        for (Tool tool : tools) {
            cost = cost + tool.cost();
        }
        return cost;
    }

    public Map <Integer, ArrayList<Tool>> getRandomAvailableTools(int numOfTools, ArrayList<Tool> availableTools) {
        ArrayList<Tool> rentedToolsNoDec = new ArrayList<Tool>(numOfTools);
        for (int i = 0; i < numOfTools; i++) {
            if (availableTools.size() == 0) {break;}
            int randomIndex = (int)(Math.random() * availableTools.size());
            rentedToolsNoDec.add(availableTools.get(randomIndex));
            availableTools.remove(randomIndex);
        }

        // add decorator
        int numOfDecorators = (int) (Math.random() * (7 - 1)) + 1;
        Tool tool = rentedToolsNoDec.get(0);
        ArrayList<Tool> rentedTools = (ArrayList<Tool>)rentedToolsNoDec.clone();
        Tool temp = tool;
        for (int i = 0; i <= numOfDecorators; i++) {
            int index = (int) (Math.random() * (4 - 1)) + 1;
            if (index == 1) {
               temp  = new ExtensionCordDecorator(tool);
            }
            if (index == 2) {
                temp = new AccessoryKit(tool);
            }
            else {
                temp = new ProtectiveGearPackage(tool);
            }
            tool = temp;
        }
        rentedTools.set(0,temp);


        Map <Integer, ArrayList<Tool>> ret = new HashMap<Integer, ArrayList<Tool>>();
        ret.put(0, rentedTools);
        ret.put(1, rentedToolsNoDec);

        return ret;
    }
}
