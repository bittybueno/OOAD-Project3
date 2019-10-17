package Tools;

public class SimpleToolFactory {

        public Tool createTool(String type, String name) {
        Tool tool = null;
        if (type.equals("Painting")) {
            tool = new PaintingTool(name);
        }
        else if (type.equals("Concrete")) {
            tool = new ConcreteTool(name);
        }
        else if (type.equals("Plumbing")) {
            tool = new PlumbingTool(name);
        }
        else if (type.equals("Woodwork")) {
            tool = new WoodworkTool(name);
        }
        else if (type.equals("Yardwork")) {
                tool = new YardworkTool(name);
        }
        else {
            System.out.println("Invalid Input.");
            throw new IllegalArgumentException();
        }
        return tool;
    }
}
