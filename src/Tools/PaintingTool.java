package Tools;

public class PaintingTool extends Tool {
    public PaintingTool(String name) {
        this.name = name;
        this.type = "Painting";
        this.cost = 3.57;
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
