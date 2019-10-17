import Customer.*;
import HardwareRentalStore.HardwareRentalStore;
import Rental.RentalRecord;
import Tools.ExtensionCordDecorator;
import Tools.SimpleToolFactory;
import Tools.Tool;
import Tools.ToolStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Currency;

import static org.junit.Assert.*;

public class MyUnitTest {

    @Test
    public void testCreateTool() {
        SimpleToolFactory toolFactory = new SimpleToolFactory();
        ToolStore toolStore = new ToolStore(toolFactory);

        Tool tool = toolStore.createTool("Painting", "Test Name");


        try {
            assertEquals("Test Name", tool.name);        }
        catch (AssertionError e) {
            System.out.println("Test 1 - Failed");
            throw e;
        }
        System.out.println("Test 1 - Passed");

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateTool2() {
        SimpleToolFactory toolFactory = new SimpleToolFactory();
        ToolStore toolStore = new ToolStore(toolFactory);

        Tool tool = toolStore.createTool("Nonsense", "Test Name");

    }

    @Test
    public void testCasualCustomer() {
        Customer customer = new Casual("Cate");
        int time = customer.getValidRentalTime();
        int size = customer.getValidRentalSize();

        try {
            assertEquals("Cate", customer.name);}
        catch (AssertionError e) {
            System.out.println("Test 3 - Failed");
            throw e;
        }
        System.out.println("Test 3 - Passed");


    }


    @Test
    public void testBusinessCustomer() {
        Customer customer = new Business("Bob");
        int time = customer.getValidRentalTime();
        int size = customer.getValidRentalSize();

        try {
            assertEquals("Bob", customer.name);}
        catch (AssertionError e) {
            System.out.println("Test 4 - Failed");
            throw e;
        }
        System.out.println("Test 4 - Passed");


    }

    @Test
    public void testRegularCustomer() {
        Customer customer = new Regular("Reggie");
        int time = customer.getValidRentalTime();
        int size = customer.getValidRentalSize();

        try {
            assertEquals("Reggie", customer.name);}
        catch (AssertionError e) {
            System.out.println("Test 5 - Failed");
            throw e;
        }
        System.out.println("Test 5 - Passed");


    }

    @Test
    public void testGetAvailableTools() {
        HardwareRentalStore store = new HardwareRentalStore();
        SimpleToolFactory toolFactory = new SimpleToolFactory();
        ToolStore toolStore = new ToolStore(toolFactory);


        store.tools.add(toolStore.createTool("Painting", "Paint Tool 1"));
        store.tools.add(toolStore.createTool("Concrete", "Concrete Tool 1"));
        store.tools.add(toolStore.createTool("Yardwork", "Yardwork Tool 1"));


        Customer customer = new Business("Bob");
        RentalRecord rental = new RentalRecord(customer, store);
        rental.update();

        ArrayList<Tool> availableTools = new ArrayList<Tool>();

        availableTools = store.availableTools();


        try {
            assertEquals(0, availableTools.size());}
        catch (AssertionError e) {
            System.out.println("Test 7 - Failed");
            throw e;
        }
        System.out.println("Test 7 - Passed");
    }

    @Test
    public void testGetAvailableTools2() {
        HardwareRentalStore store = new HardwareRentalStore();
        SimpleToolFactory toolFactory = new SimpleToolFactory();
        ToolStore toolStore = new ToolStore(toolFactory);


        store.tools.add(toolStore.createTool("Painting", "Paint Tool 1"));
        store.tools.add(toolStore.createTool("Concrete", "Concrete Tool 1"));



        ArrayList<Tool> availableTools = new ArrayList<Tool>();

        availableTools = store.availableTools();


        try {
            assertEquals(2, availableTools.size());}
        catch (AssertionError e) {
            System.out.println("Test 6 - Failed");
            throw e;
        }
        System.out.println("Test 6 - Passed");
    }

    @Test
    public void testCostNoDecorator() {
        HardwareRentalStore store = new HardwareRentalStore();
        SimpleToolFactory toolFactory = new SimpleToolFactory();
        ToolStore toolStore = new ToolStore(toolFactory);


        store.tools.add(toolStore.createTool("Painting", "Paint Tool 1"));
        store.tools.add(toolStore.createTool("Concrete", "Concrete Tool 1"));


        Customer customer = new Business("Bob");
        RentalRecord rental = new RentalRecord(customer, store);

        ArrayList<Tool> availableTools = new ArrayList<Tool>();

        availableTools = store.availableTools();

        double cost = rental.getTotalCost(availableTools);


        try {
            assertEquals(9.1, cost, 1);}
        catch (AssertionError e) {
            System.out.println("Test 8 - Failed");
            throw e;
        }
        System.out.println("Test 8 - Passed");
    }

    @Test
    public void testCostWithDecorator() {
        SimpleToolFactory toolFactory = new SimpleToolFactory();
        ToolStore toolStore = new ToolStore(toolFactory);


        Tool tool = toolStore.createTool("Painting", "Paint Tool 1");
        Tool toolWithDecorator = new ExtensionCordDecorator(tool);

        double cost = toolWithDecorator.cost();




        try {
            assertEquals(7.61, cost, 1);}
        catch (AssertionError e) {
            System.out.println("Test 9 - Failed");
            throw e;
        }
        System.out.println("Test 9 - Passed");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyName() {
        Customer customer = new Business("");
    }

}