package Tools;

public class PlumbingTool extends Tool {
    public PlumbingTool(String name) {
        this.name = name;
        this.type = "Plumbing";
        this.cost = 7.77;
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
