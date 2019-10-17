package Tools;

import java.util.ArrayList;
import java.util.Random;

public abstract class Tool {
    public String name;
    public String type;
    public double cost;
    public String description;
    public boolean inStock;

    public abstract double cost();

    public abstract String getDescription();
    public abstract void setInStock(boolean inStock);

}
