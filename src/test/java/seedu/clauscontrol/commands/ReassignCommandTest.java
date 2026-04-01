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
public class ReassignCommandTest {

    private ArrayList<Child> childList;

    @BeforeEach
    public void setUp() throws IllegalValueException {
        childList = new ArrayList<>();
        childList.add(new Child(new Name("Tom")));
    }

    @Test
    public void execute_overridesListAssignment() {
        childList.get(0).addAction("broke window", -3);
        ReassignCommand cmd = new ReassignCommand(1, "nice");
        cmd.setData(childList, new ArrayList<>(), false);
        cmd.execute();
        assertTrue(childList.get(0).isNice());
    }

    @Test
    public void execute_invalidIndex_returnsError() {
        ReassignCommand cmd = new ReassignCommand(99, "nice");
        cmd.setData(childList, new ArrayList<>(), false);
        assertEquals("Enter a valid child index!", cmd.execute());
    }

    @Test
    public void execute_invalidList_returnsError() {
        ReassignCommand cmd = new ReassignCommand(1, "invalid");
        cmd.setData(childList, new ArrayList<>(), false);
        assertEquals("Enter either nice or naughty", cmd.execute());
    }

    @Test
    public void execute_blockedAfterFinalize() {
        ReassignCommand cmd = new ReassignCommand(1, "nice");
        cmd.setData(childList, new ArrayList<>(), true);
        assertEquals("Cannot reassign after the lists have been finalised!", cmd.execute());
    }
}
//@@author
