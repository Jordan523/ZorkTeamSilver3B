package Commands;
import Items.*;
import Items.Weapon.NoWeaponException;
import Dungeon.Room;
import Game.*;
 

class TakeCommand extends Command {

    private String itemName;

    TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        try {
            Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(itemName);
            GameState.instance().getPlayer().addToInventory(theItem);
            currentRoom.remove(theItem);
            return itemName + " taken.\n";
      
        } catch (Item.NoItemException e) {
            // Check and see if we have this already. If no exception is
            // thrown from the line below, then we do.
            try {
                GameState.instance().getItemFromInventoryNamed(itemName);
                return "You already have the " + itemName + ".\n";
            } catch (Item.NoItemException e2) {
                
            }
        }
        try{
        	Room currentRoom = 
        			GameState.instance().getAdventurersCurrentRoom();
        	Weapon weapon = currentRoom.getWeaponNamed(itemName);
        	if(weapon == null){
        		return "There's no " + itemName + " here.\n";
        	}
        	
        }catch(NoWeaponException e){
        	
        }
        
        return null;
    }
}
