package Commands;
/**
 * This class identifies commands that aren't programmed to the game
 * @author Matt
 */

class UnknownCommand extends Command {

    private String bogusCommand;
/**
 * Takes in command that isn't recognized
 * @param bogusCommand 
 */
    UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }
/**
 * Returns to user that he gave bogus command
 * @return 
 */
    public String execute() {
        return "I'm not sure what you mean by \"" + bogusCommand + "\".\n";
    }
}
