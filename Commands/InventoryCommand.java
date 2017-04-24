package Commands;
import Game.*;
import Dungeon.*;
 

import java.util.ArrayList;

class InventoryCommand extends Command {

    InventoryCommand() {
    }

    public String execute() {
        ArrayList<String> names = GameState.instance().getPlayer().getInventoryNames();

        if (names.isEmpty()) {
            return "You are empty-handed.\n";
        }
        String retval = "You are carrying:\n";
        for (String itemName : names) {
            retval += "   "+Dungeon.aOrAn(itemName) + itemName + "\n";
        }
        return retval;
    }
}
