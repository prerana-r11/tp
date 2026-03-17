//@@author prerana-r11
package seedu.duke.commands;
import seedu.duke.data.child.Child;

public class DeGiftCommand extends Command{
    private int childIndex;
    private int giftIndex;

    public DeGiftCommand(int childIndex,int giftIndex){
        this.childIndex=childIndex;
        this.giftIndex=giftIndex;
    }

    @Override
    public String execute(){
        if(childIndex<1 || childIndex>childList.size()){
            return "Please enter valid child index";
        }
        Child child= childList.get(childIndex-1);

        if(giftIndex<1 || giftIndex>child.getGifts().size()){
            return "Please enter valid gift index";
        }
        child.deGift(giftIndex-1);

        return "Removed gift " + giftIndex + " from child " + child.getName();

    }
}
//@@author
