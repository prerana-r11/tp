package seedu.duke.commands;

public class ChildListCommand extends Command {
    
    public static final String COMMAND_WORD = "childlist";
    
    @Override
    public String execute() {

        if (childList.isEmpty()) {
            return "The child list is empty!";
        }
        StringBuilder sb = new StringBuilder("Here are all children:\n");
        for (int i = 0; i < childList.size(); i++) {
            sb.append((i + 1)).append(". ").append(childList.get(i)).append("\n");
        }
        return sb.toString();
    }
}