package Tools;

import java.util.ArrayList;
import java.util.Random;

public abstract class Tool {

    // abstract class for tool

    public String name;
    public String type;
    public double cost;
    public String description;
    public boolean inStock;


    // subclasses are to define methods
    // based on which type of tool they are
    public abstract double cost();
    public abstract String getDescription();

    public abstract void setInStock(boolean inStock);

}
