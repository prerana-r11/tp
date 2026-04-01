package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author GShubhan
public class NiceCommandTest {

    private ArrayList<Child> childList;

    @BeforeEach
    public void setUp() throws IllegalValueException {
        childList = new ArrayList<>();
        childList.add(new Child(new Name("Tom")));
        childList.add(new Child(new Name("Lucy")));
    }

    @Test
    public void execute_returnsNiceChildren() {
        childList.get(0).addAction("helped grandma", 2);
        childList.get(1).addAction("broke window", -3);
        NiceCommand cmd = new NiceCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        String result = cmd.execute();
        assertTrue(result.contains("Tom"));
        assertFalse(result.contains("Lucy"));
    }

    @Test
    public void execute_emptyList_returnsEmptyMessage() {
        NiceCommand cmd = new NiceCommand();
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertEquals("The child list is empty!", cmd.execute());
    }

    @Test
    public void execute_noNiceChildren_returnsNoNiceMessage() {
        childList.get(0).addAction("broke window", -3);
        childList.get(1).addAction("broke window", -3);
        NiceCommand cmd = new NiceCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        assertEquals("No children on the nice list!", cmd.execute());
    }
}
//@@author
