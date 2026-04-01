package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.todo.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author GShubhan
public class TodoCommandTest {

    private ArrayList<Todo> todoList;

    @BeforeEach
    public void setUp() {
        todoList = new ArrayList<>();
    }

    @Test
    public void addTodo_valid_addsTodo() {
        AddTodoCommand cmd = new AddTodoCommand(
                "d/Buy gifts by/" + LocalDate.now().plusDays(3), todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        cmd.execute();
        assertEquals(1, todoList.size());
    }

    @Test
    public void addTodo_missingPrefix_returnsError() {
        AddTodoCommand cmd = new AddTodoCommand("Buy gifts", todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertEquals("Format: todo d/DESCRIPTION by/YYYY-MM-DD", cmd.execute());
    }

    @Test
    public void addTodo_pastDate_returnsError() {
        AddTodoCommand cmd = new AddTodoCommand("d/Old task by/2020-01-01", todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertEquals("Deadline cannot be in the past!", cmd.execute());
    }

    @Test
    public void addTodo_emptyDescription_returnsError() {
        AddTodoCommand cmd = new AddTodoCommand(
                "d/ by/" + LocalDate.now().plusDays(3), todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertEquals("Description cannot be empty!", cmd.execute());
    }

    @Test
    public void addTodo_invalidDate_returnsError() {
        AddTodoCommand cmd = new AddTodoCommand("d/Buy gifts by/not-a-date", todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertTrue(cmd.execute().contains("Invalid date format"));
    }

    @Test
    public void todoList_emptyList_returnsEmptyMessage() {
        TodoListCommand cmd = new TodoListCommand(todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertEquals("No todos added yet!", cmd.execute());
    }

    @Test
    public void todoList_withTodos_returnsList() {
        todoList.add(new Todo("Buy gifts", LocalDate.now().plusDays(3)));
        TodoListCommand cmd = new TodoListCommand(todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertTrue(cmd.execute().contains("Buy gifts"));
    }

    @Test
    public void removeTodo_validIndex_removesTodo() {
        todoList.add(new Todo("Buy gifts", LocalDate.now().plusDays(3)));
        RemoveTodoCommand cmd = new RemoveTodoCommand(1, todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        cmd.execute();
        assertEquals(0, todoList.size());
    }

    @Test
    public void removeTodo_invalidIndex_returnsError() {
        RemoveTodoCommand cmd = new RemoveTodoCommand(99, todoList);
        cmd.setData(new ArrayList<>(), new ArrayList<>(), false);
        assertEquals("Enter a valid todo index!", cmd.execute());
    }

    @Test
    public void todo_deadlineToday_isUpcoming() {
        assertTrue(new Todo("Today task", LocalDate.now()).isUpcoming());
    }

    @Test
    public void todo_deadlineIn7Days_isUpcoming() {
        assertTrue(new Todo("Week task", LocalDate.now().plusDays(7)).isUpcoming());
    }

    @Test
    public void todo_deadlineIn8Days_notUpcoming() {
        assertFalse(new Todo("Far task", LocalDate.now().plusDays(8)).isUpcoming());
    }
}
//@@author
