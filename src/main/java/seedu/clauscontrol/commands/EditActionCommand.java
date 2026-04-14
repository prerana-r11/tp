//@@author GShubhan
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;

/**
 * Edits an existing action for a child by index.
 */
public class EditActionCommand extends Command {
    public static final String SUCCESS = "Action updated for %s: \"%s\" (severity: %d)";
    public static final String SUCCESS_ZERO_SEVERITY =
            "Action updated for %s: \"%s\" (severity: 0)\n"
                    + "Note: A severity of 0 has no effect on this child's nice/naughty score.";

    private final int childIndex;
    private final int actionIndex;
    private final String newDescription;
    private final Integer newSeverity;

    public EditActionCommand(int childIndex, int actionIndex,
                             String newDescription, Integer newSeverity) {
        this.childIndex = childIndex;
        this.actionIndex = actionIndex;
        this.newDescription = newDescription;
        this.newSeverity = newSeverity;
    }

    @Override
    public String execute() {
        if (isFinalized) {
            return "Cannot edit actions after the lists have been finalised!";
        }
        if (childList == null || childList.isEmpty()) {
            return "The child list is empty!";
        }
        if (childIndex < 1 || childIndex > childList.size()) {
            return "Enter a valid child index!";
        }
        Child child = childList.get(childIndex - 1);
        if (actionIndex < 1 || actionIndex > child.getActions().size()) {
            return "Enter a valid action index!";
        }

        if (newDescription != null) {
            child.getActions().set(actionIndex - 1, newDescription);
        }
        if (newSeverity != null) {
            if (newSeverity < -5 || newSeverity > 5) {
                return "Severity must be between -5 and 5!";
            }
            child.getSeverities().set(actionIndex - 1, newSeverity);
        }

        String finalDesc = child.getActions().get(actionIndex - 1);
        int finalSev = child.getSeverities().get(actionIndex - 1);

        if (finalSev == 0) {
            return String.format(SUCCESS_ZERO_SEVERITY, child.getName(), finalDesc);
        }
        return String.format(SUCCESS, child.getName(), finalDesc, finalSev);
    }
}
//@@author
