package seedu.duke.data.child;

//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/data
// /person/Person.java
public class Child implements ReadOnlyChild{
    private Name name;

    public Child(Name name) {
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    // ChatGPT was used to ideate and generate this mutator method
    public void editName(Name newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
