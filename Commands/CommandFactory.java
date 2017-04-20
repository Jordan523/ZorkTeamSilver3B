package Commands;
 

import java.util.List;
import java.util.Arrays;

public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );
    public static List<String> GET_COMMANDS = 
        Arrays.asList("health", "score");
    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }

    public Command parse(String command) {
        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equalsIgnoreCase("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equalsIgnoreCase("take")) {
            return new TakeCommand(noun);
        }
        if (verb.equalsIgnoreCase("equip"))
        	return new EquipCommand(noun);
        
        if (verb.equalsIgnoreCase("drop")) {
            return new DropCommand(noun);
        }
        if(verb.equalsIgnoreCase("get")){
            return new GetCommand(noun);
        }
        if (verb.equalsIgnoreCase("describe") || verb.equalsIgnoreCase("desc")){
            return new DescribeCommand();
        }
        if (verb.equalsIgnoreCase("quit") || verb.equalsIgnoreCase("q"))
        	return new QuitCommand();
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
        if (GET_COMMANDS.contains(verb)){
            return new GetCommand(verb);
        }
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
        if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }
        return new UnknownCommand(command);
    }
}
