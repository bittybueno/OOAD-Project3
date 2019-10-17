package Tools;

public class ProtectiveGearPackage extends ToolDecorator {
    Tool tool;

    public ProtectiveGearPackage(Tool tool) {
        this.tool = tool;
        this.name = "Protective Gear Package";
        this.cost = 11.13;
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
