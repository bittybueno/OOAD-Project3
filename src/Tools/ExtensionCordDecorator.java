package Tools;

public class ExtensionCordDecorator extends ToolDecorator {
    Tool tool;

    public ExtensionCordDecorator(Tool tool) {
        this.tool = tool;
        this.name = "Extension Cord";
        this.cost = 4.04;
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
