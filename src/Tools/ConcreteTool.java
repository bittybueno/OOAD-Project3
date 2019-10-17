
package Tools;

public class ConcreteTool extends Tool {
    public ConcreteTool(String name) {
        this.name = name;
        this.type = "Concrete";
        this.cost = 5.53;
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
