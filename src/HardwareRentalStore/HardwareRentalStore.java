package HardwareRentalStore;

import Customer.*;
import Rental.*;
import Tools.*;
import java.math.*;
import MyUnitTest.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Iterator;
import java.text.DecimalFormat;

public class HardwareRentalStore {

    public ArrayList<Tool> tools;
    public ArrayList<RentalRecord> activeRentals;
    public ArrayList<RentalRecord> oldRentals;
    public Map <String, Integer> customerHistory;



    public HardwareRentalStore() {
        ArrayList<Tool> tools = new ArrayList<Tool>(20);
        this.tools = tools;

        ArrayList<RentalRecord> activeRentals = new ArrayList<RentalRecord>();
        this.activeRentals = activeRentals;

        ArrayList<RentalRecord> oldRentals = new ArrayList<RentalRecord>();
        this.oldRentals = oldRentals;

        Map <String, Integer> customerHistory = new HashMap<String, Integer>();
        customerHistory.put("Casual", 0);
        customerHistory.put("Regular", 0);
        customerHistory.put("Business", 0);
        customerHistory.put("Total", 0);
        this.customerHistory = customerHistory;


    }


    public  ArrayList<Customer> createCustomers(){

        ArrayList<Customer> customers = new ArrayList<Customer>();

        customers.add( new Casual("Carl"));
        customers.add( new Casual("Cathy"));
        customers.add( new Casual("Chuckie"));
        customers.add( new Casual("Chris"));
        customers.add( new Casual("Craig"));
        customers.add( new Casual("Chuck"));
        customers.add( new Casual("Claude"));
        customers.add( new Regular("Regina"));
        customers.add( new Regular("Red"));
        customers.add( new Regular("Rhonda"));
        customers.add( new Regular("Russ"));
        customers.add( new Business("Brad"));


        return customers;

    }

    public ArrayList<Tool> createTools(){
        // SIMPLY FACTORY PATTERN
        SimpleToolFactory toolFactory = new SimpleToolFactory();
        ToolStore toolStore = new ToolStore(toolFactory);


        this.tools.add(toolStore.createTool("Painting", "Paint Tool 1"));
        this.tools.add(toolStore.createTool("Painting", "Paint Tool 2"));
        this.tools.add(toolStore.createTool("Painting", "Paint Tool 3"));
        this.tools.add(toolStore.createTool("Painting", "Paint Tool 4"));
        this.tools.add(toolStore.createTool("Painting", "Paint Tool 5"));
        this.tools.add(toolStore.createTool("Concrete", "Concrete Tool 1"));
        this.tools.add(toolStore.createTool("Concrete", "Concrete Tool 2"));
        this.tools.add(toolStore.createTool("Concrete", "Concrete Tool 3"));
        this.tools.add(toolStore.createTool("Concrete", "Concrete Tool 4"));
        this.tools.add(toolStore.createTool("Concrete", "Concrete Tool 5"));
        this.tools.add(toolStore.createTool("Plumbing", "Plumbing Tool 1"));
        this.tools.add(toolStore.createTool("Plumbing", "Plumbing Tool 2"));
        this.tools.add(toolStore.createTool("Plumbing", "Plumbing Tool 3"));
        this.tools.add(toolStore.createTool("Plumbing", "Plumbing Tool 4"));
        this.tools.add(toolStore.createTool("Woodwork", "Woodwork Tool 1"));
        this.tools.add(toolStore.createTool("Woodwork", "Woodwork Tool 2"));
        this.tools.add(toolStore.createTool("Woodwork", "Woodwork Tool 3"));
        this.tools.add(toolStore.createTool("Woodwork", "Woodwork Tool 4"));
        this.tools.add(toolStore.createTool("Yardwork", "Yardwork Tool 1"));
        this.tools.add(toolStore.createTool("Yardwork", "Yardwork Tool 2"));
        this.tools.add(toolStore.createTool("Yardwork", "Yardwork Tool 3"));
        this.tools.add(toolStore.createTool("Yardwork", "Yardwork Tool 4"));
        this.tools.add(toolStore.createTool("Yardwork", "Yardwork Tool 5"));
        this.tools.add(toolStore.createTool("Yardwork", "Yardwork Tool 6"));

        return this.tools;

    }

    public void updateInventoryRent(ArrayList<Tool> rentedToolsNoDec) {
        // update inventory when a rental transaction has occured
        for (Tool tool : rentedToolsNoDec) {
            int i = this.tools.indexOf(tool);
            this.tools.get(i).setInStock(false);
        }
    }

    public void updateInventoryReturn(ArrayList<Tool> rentedToolsNoDec) {
        // update inventory when a rental has expired
        for (Tool tool : rentedToolsNoDec) {
            int i = this.tools.indexOf(tool);
            this.tools.get(i).setInStock(true);
        }
    }

    public ArrayList<Tool> availableTools() {
        // iterate through tools and gather an ArrayList
        // of which tools are available to rent
        ArrayList<Tool> availableTools = new ArrayList<Tool>();
        for (Tool tool : this.tools) {
            if (tool.inStock == true) {
                availableTools.add(tool);
            }
        }
        return availableTools;
    }

    public void updateCustomerHistory(RentalRecord rental) {
        // casual index 0 --- reg index 1 -- biz index 2
        if (rental.customer.type == "Casual") {
            this.customerHistory.put("Casual", this.customerHistory.get("Casual") + 1);
        } else if (rental.customer.type == "Regular") {
            this.customerHistory.put("Regular", this.customerHistory.get("Regular") + 1);
        } else {
            this.customerHistory.put("Business", this.customerHistory.get("Business") + 1);
        }
        this.customerHistory.put("Total", this.customerHistory.get("Total") + 1);
    }

    public void printCustomerHistory() {
        System.out.println("Total Rentals for Casual Customers: " + this.customerHistory.get("Casual"));
        System.out.println("Total Rentals for Regular Customers: " + this.customerHistory.get("Regular"));
        System.out.println("Total Rentals for Business Customers: " + this.customerHistory.get("Business"));
        System.out.println("Total Rentals: " + this.customerHistory.get("Total"));
    }

    public double customerSimulation(ArrayList<Customer> customersArr) {

        // to keep track of which rentals returned
        ArrayList<RentalRecord> temp = new ArrayList<RentalRecord>();

        // selecting random number of customers
        Random rand = new Random();

        // to display count of rentals at the end of each day
        int active = 0;
        int returned = 0;


        // ITERATOR DESIGN PATTERN
        // used to iterate through every customer for each day
        Iterator<Customer> it = customersArr.iterator();
        while (it.hasNext()) {
            Customer customer = it.next();
            for (RentalRecord rental : customer.activeRentals) {
                if (rental.numOfDays == 0) {
                    temp.add(rental);
                    returned += 1;
                } else {
                    rental.printRentalDesc("Active");
                    rental.decreaseRentalDay();
                    active += 1;
                }
            }
        }

        // ITERATOR DESIGN PATTERN & FACADE
        // iterate through rentals to remove expired rentals from active rentals list
        // and trigger an expiredRental() which calls a number of different methods
        // on different classes to update (FACADE)
        Iterator<RentalRecord> itRent = temp.iterator();
        while (itRent.hasNext()) {
            RentalRecord rental = itRent.next();
            rental.printRentalDesc("Completed");
            int remove = rental.customer.activeRentals.indexOf(rental);
            RentalRecord rent = rental.customer.activeRentals.get(remove);
            rent.expiredRental();
        }

        // random number of visitors
        int r = (int) (Math.random() * (customersArr.size() - 1)) + 1;

        // to keep track of each daily profit
        double totalProfit = 0.0;

        // If there is inventory and the customer has less than 3 active rentals
        // complete a rental transaction. If not, skip.
        // A FACADE is used on the  update() method which calls a number of different methods
        // on different classes to update
        for (int i = 0; i < r; i++) {
            if (this.availableTools().size() == 0) {
                System.out.println("\n------ Empty Inventory ------ ");
                return 0.0;
            } else {
                // select random customer
                int randomIndex = rand.nextInt(customersArr.size());
                Customer customer = customersArr.get(randomIndex);
                if ((customer.numRentals < 3)) {
                    if (this.availableTools().size() > 0) {
                        RentalRecord rental = new RentalRecord(customer, this);
                        rental.printRentalDesc("New");
                        rental.update();
                        totalProfit += rental.fee;
                    }
                }
            }
        }

        // display
        System.out.println("Active Rentals: " + active);
        System.out.println("Returned Rentals: " + returned);
        this.printInventory();
        return totalProfit;
    }

    public void printInventory() {
        System.out.println("\n########## End of Day Inventory ##########\n");

        for (Tool tool : this.tools) {
            System.out.println(tool.name + " - " + tool.inStock);
        }
        System.out.println("\n###########################################\n");

    }

    public void daySimulaiton(ArrayList<Customer> customersArr) {
        // formatting purposes
        DecimalFormat df = new DecimalFormat("####.##");
        df.setRoundingMode(RoundingMode.DOWN);

        // cycle through each day
        double monthlyProfit = 0.0;
        for (int i = 1; i <=35; i++) {
            System.out.println("\n\n ********************** DAY " + i + " **********************\n\n");
            double profit = this.customerSimulation(customersArr);
            monthlyProfit += profit;
            System.out.println("Total Profit for the day: $" + df.format(profit));
        }

        System.out.println("\n\n\n********************** End of Month *************************\n\n");
        System.out.println("Total Profit for the month: $" + df.format(monthlyProfit));
        this.printCustomerHistory();

    }

    public static void main(String[] args) {

        // EXTRA CREDIT JUNIT TESTS
        MyUnitTest myUnitTest = new MyUnitTest();
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(MyUnitTest.class);

        // SET UP
        HardwareRentalStore hardwareRentalStore = new HardwareRentalStore();
        hardwareRentalStore.createTools();
        ArrayList<Customer> customersArr = hardwareRentalStore.createCustomers();

        // SIMULATION
        hardwareRentalStore.daySimulaiton(customersArr);




    }
}
