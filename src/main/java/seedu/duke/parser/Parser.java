// ChatGPT was used to generate the parseCommand and prepareAdd functions with reference from https://github.com/
// se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java and supervision from the author
package seedu.duke.parser;

import seedu.duke.commands.ChildCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.exception.IllegalValueException;

public class Parser {
    public Command parseCommand(String userInput) throws IllegalValueException {
        String[] parts = userInput.trim().split(" ", 2);

        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        if (commandWord.equals("child")) {
            return prepareAdd(arguments);
        }

        throw new IllegalValueException("Unknown command.");
    }

    private Command prepareAdd(String args) throws IllegalValueException {
        String name = null;

        String[] tokens = args.split(" ");

        for (String token : tokens) {
            if (token.startsWith("n/")) {
                name = token.substring(2);
            }
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalValueException("Format: child n/NAME");
        }

        return new ChildCommand(name);
    }
}
