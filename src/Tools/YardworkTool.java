package Tools;

public class YardworkTool extends Tool {
    public YardworkTool(String name) {
        this.name = name;
        this.type = "Yardwork";
        this.cost = 7.75;
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
