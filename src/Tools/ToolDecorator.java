package Tools;

public abstract class ToolDecorator extends Tool {

    // DECORATOR DESIGN PATTERN


    public String name;
    public double cost;
    public String description;
    public abstract double cost();
    public abstract String getDescription();
}
