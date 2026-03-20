//@@author Kiri
package seedu.duke.data.elf;
import seedu.duke.data.child.Name;
// reuse child class code

public class Elf implements ReadOnlyElf {
    private Name name;
    private ElfTask task;
    
    public Elf(Name name) {
        this.name = name;
        this.task = null;
    }
    
    @Override
    public Name getName() {
        return name;
    }
    
    // assign task to elf
    public void setTask(ElfTask task) {
        this.task = task;
    }
    
    @Override
    public String toString() {
        String taskStatus = (task == null) ? "no task" : task.toString();
        return name.toString() + " (Task: " + taskStatus + ")";
    }
    
}
//@@author
