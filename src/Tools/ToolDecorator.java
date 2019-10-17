package Tools;

public abstract class ToolDecorator extends Tool {
    public String name;
    public double cost;
    public String description;
    public abstract double cost();
    public abstract String getDescription();
}
