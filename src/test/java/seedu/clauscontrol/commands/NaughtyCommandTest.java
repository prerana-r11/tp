package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author GShubhan
public class NaughtyCommandTest {

    private ArrayList<Child> childList;

    @BeforeEach
    public void setUp() throws IllegalValueException {
        childList = new ArrayList<>();
        childList.add(new Child(new Name("Tom")));
        childList.add(new Child(new Name("Lucy")));
    }

    @Test
    public void execute_returnsNaughtyChildren() {
        childList.get(1).addAction("broke window", -3);
        NaughtyCommand cmd = new NaughtyCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        assertTrue(cmd.execute().contains("Lucy"));
    }

    @Test
    public void execute_emptyList_returnsEmptyMessage() {
        NaughtyCommand cmd = new NaughtyCommand();
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertEquals("The child list is empty!", cmd.execute());
    }

    @Test
    public void execute_noNaughtyChildren_returnsMessage() {
        childList.get(0).addAction("helped grandma", 2);
        childList.get(1).addAction("helped grandma", 2);
        NaughtyCommand cmd = new NaughtyCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        assertEquals("No children on the naughty list!", cmd.execute());
    }
}
//@@author
