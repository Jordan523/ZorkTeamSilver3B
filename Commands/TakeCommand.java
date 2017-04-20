package Commands;
import Items.*;
import Dungeon.Room;
import Entities.Player;
import Entities.Player.TooHeavyException;
import Game.*;
 

class TakeCommand extends Command {

    private String itemName;
    private Player player = GameState.instance().getPlayer();

    TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        try {
            Room currentRoom = 
                GameState.instance().getPlayer().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(itemName);
            GameState.instance().getPlayer().addToInventory(theItem);
            currentRoom.remove(theItem);
            return itemName + " taken.\n";
      
        } catch (Item.NoItemException e) {
            // Check and see if we have this already. If no exception is
            // thrown from the line below, then we do.
            try {
                player.getItemFromInventoryNamed(itemName);
                return "You already have the " + itemName + ".\n";
            } catch (Item.NoItemException e2) {
                
            }
        } catch(TooHeavyException e){
            return "You are carrying too much weight.\n";
        }
        /**try{
        	Room currentRoom = 
        			player.getAdventurersCurrentRoom();
        	Weapon weapon = currentRoom.getWeaponNamed(itemName);
        	if(weapon == null){
        		return "There's no " + itemName + " here.\n";
        	}
        	
        }catch(NoWeaponException e){
        	
        }*/
        
        return "There's no " + itemName + " here.\n";
    }
}
