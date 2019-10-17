package Tools;

public  class ToolStore {

    SimpleToolFactory factory;

    public ToolStore(SimpleToolFactory factory) {
        this.factory = factory;
    }
    public Tool createTool(String type, String name) {
        Tool tool;
        tool = factory.createTool(type, name);
        return tool;
    }

}
