package Tools;

public class WoodworkTool extends Tool {
    public WoodworkTool(String name) {
        this.name = name;
        this.type = "Woodwork";
        this.cost = 9.99;
        this.description = this.type + " | Name: " + this.name + " | ";
        this.inStock = true;

    }

    public double cost() {
        return this.cost;
    }

    public String getDescription() {
        return this.description;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
