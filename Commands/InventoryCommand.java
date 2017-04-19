package Commands;
import Game.GameState;
 

import java.util.ArrayList;

class InventoryCommand extends Command {

    InventoryCommand() {
    }

    public String execute() {
        ArrayList<String> names = GameState.instance().getInventoryNames();
        if (names.isEmpty()) {
            return "You are empty-handed.\n";
        }
        String retval = "You are carrying:\n";
        for (String itemName : names) {
            retval += "   A " + itemName + "\n";
        }
        return retval;
    }
}
