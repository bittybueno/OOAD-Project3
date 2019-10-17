package Tools;

public class AccessoryKit extends ToolDecorator {
    Tool tool;

    public AccessoryKit(Tool tool) {
        this.tool = tool;
        this.name = "Accessory Kit";
        this.cost = 8.00;
        this.description = "**Addition** | Name: " + this.name + " | ";
        this.inStock = true;

    }

    public double cost() {
        return tool.cost() + this.cost;
    }

    public String getDescription() {
        return tool.getDescription() + this.description;
    }

    public void setInStock(boolean inStock) {
        this.inStock = true;
    }
}
