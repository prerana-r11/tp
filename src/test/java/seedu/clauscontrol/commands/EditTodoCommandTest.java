package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.todo.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author shrabasti-c
public class EditTodoCommandTest {

    private static ArrayList<Todo> todoList;
    private static EditTodoCommand command;

    @BeforeAll
    public static void setup() throws IllegalValueException {
        todoList = new ArrayList<>();
        AddTodoCommand addCommand = new AddTodoCommand("d/buy cake by/2026-12-12", todoList);
        addCommand.execute();
    }

    @Test
    public void editTodoCommand_validIndex_valid() throws Exception {
        command = new EditTodoCommand(todoList, 0, "buy muffins", LocalDate.now().plusDays(3));
        String result = command.execute();
        System.out.println(result);
        assertTrue(result.contains("buy muffins"));
    }
}
//@@author
