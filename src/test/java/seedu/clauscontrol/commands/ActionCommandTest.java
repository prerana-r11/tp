package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author GShubhan
public class ActionCommandTest {

    private ArrayList<Child> childList;

    @BeforeEach
    public void setUp() throws IllegalValueException {
        childList = new ArrayList<>();
        childList.add(new Child(new Name("Tom")));
    }

    @Test
    public void execute_validIndex_addsAction() {
        ActionCommand cmd = new ActionCommand(1, "helped grandma", 2);
        cmd.setData(childList, new ArrayList<>(), false);
        cmd.execute();
        assertEquals(2, childList.get(0).getTotalScore());
    }

    @Test
    public void execute_invalidIndex_returnsError() {
        ActionCommand cmd = new ActionCommand(99, "helped grandma", 2);
        cmd.setData(childList, new ArrayList<>(), false);
        assertEquals("Enter a valid child index", cmd.execute());
    }

    @Test
    public void execute_blockedAfterFinalize() {
        ActionCommand cmd = new ActionCommand(1, "helped grandma", 2);
        cmd.setData(childList, new ArrayList<>(), true);
        assertEquals("Cannot add actions after the lists have been finalised!", cmd.execute());
    }

    @Test
    public void execute_negativeSeverity_reducesScore() {
        ActionCommand cmd = new ActionCommand(1, "broke window", -3);
        cmd.setData(childList, new ArrayList<>(), false);
        cmd.execute();
        assertEquals(-3, childList.get(0).getTotalScore());
    }
}
//@@author
