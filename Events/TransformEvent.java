package Events;
import Entities.Player;
import Game.GameState;
import Items.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nathan
 */
public class TransformEvent implements Events {
    private String itemName;
    private Item orig;
    private Player player = GameState.instance().getPlayer();
    
    public TransformEvent(String itemName, Item orig) {
        this.itemName = itemName;
        this.orig = orig;
    }
    
    @Override
    public void execute() {
        GameState gs = GameState.instance();
        Item newItem;
        boolean found = true;
        
        // Check the player's inventory
        try {
            newItem = GameState.instance().getDungeon().getItem(itemName);
            
            if (player.getItemFromInventoryNamed(orig.getPrimaryName()) != null) {
                player.removeFromInventory(orig);
                player.addToInventory(newItem);
            }
        } catch (Item.NoItemException ex) {
            found = false;
        } catch (Player.TooHeavyException ex) {
        }
        
        // If it's not in the inventory, then check the room
        if (!found) {
            try {
                newItem = GameState.instance().getDungeon().getItem(itemName);
                
                if (player.getAdventurersCurrentRoom().getItemNamed(orig.getPrimaryName()) != null) {
                    player.getAdventurersCurrentRoom().remove(orig);
                    player.getAdventurersCurrentRoom().add(newItem);
                }
            } catch (Item.NoItemException ex) {
                
            }
        }
    }

    @Override
    public boolean hasCalledTimer() { return false; }

    @Override
    public void setHasCalledTimer(boolean x) {}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
