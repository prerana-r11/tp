package seedu.duke.data.list;

import java.util.ArrayList;

import seedu.duke.data.elf.Elf;

public class ElfList implements List{
    private ArrayList<Elf> elves = new ArrayList<>();
    
    public void addElf(Elf e) {
        elves.add(e);
    }
    
    @Override
    public void listAll() {
        if (elves.isEmpty()) {
            System.out.println("The elf list is empty!");
            return;
        }
        
        for (int i = 0; i < elves.size(); i++) {
            // use Elf.toString
            System.out.println((i + 1) + ". " + elves.get(i));
        }
    }
}
