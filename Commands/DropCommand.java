package Commands;
import Entities.Player;
import Game.GameState;
import Items.*;
 

public class DropCommand extends Command {

    private String itemName;
    private Player player = GameState.instance().getPlayer();

    public DropCommand(String itemName) {
        this.itemName = itemName;
    }

    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Drop what?\n";
        }
        try {
            Item theItem = player.getItemFromInventoryNamed(
                itemName);
            player.removeFromInventory(theItem);
            Item weilding = player.getWielding();
            if(weilding != null){
                if(weilding.equals(theItem))
                    player.equip(null);
            }
                
            player.getAdventurersCurrentRoom().add(theItem);
            return itemName + " dropped.\n";
        } catch (Item.NoItemException e) {
            return "You don't have a " + itemName + ".\n";
        }
    }
}
