package csci.ooad.polymorphia.characters;

public class DoNothingCommand implements Command{

    Character character;

    public DoNothingCommand(Character character){
        this.character = character;
    }
    @Override
    public void execute() {
        return;     //do nothing
    }
}
