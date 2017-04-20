package Events;
import Entities.Player;
import Items.*;
import Game.GameState;
/**
 *
 * @author Nathan
 */
public class DisappearEvent implements Events {
    private Item orig;
    private Player player = GameState.instance().getPlayer();
    
    public DisappearEvent(Item orig) {
        this.orig = orig;
    }
    
    @Override
    public void execute() {
        Item newItem;
        boolean found = true;
        
        // Check the player's inventory
        try {
            if (player.getItemFromInventoryNamed(orig.getPrimaryName()) != null) {
                player.removeFromInventory(orig);
            }
        } catch (Item.NoItemException ex) { found = false; }
        
        // If it's not in the inventory, then check the room
        if (!found) {
            try {
                if (player.getAdventurersCurrentRoom().getItemNamed(orig.getPrimaryName()) != null) {
                    player.getAdventurersCurrentRoom().remove(GameState.instance().getItemInVicinityNamed(orig.getPrimaryName()));
                }
            } catch (Item.NoItemException ex) { System.out.println("Not Found");}
        }
    }

    @Override
    public boolean hasCalledTimer() { return false; }

    @Override
    public void setHasCalledTimer(boolean x) {}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "DisappearEvent";
	}
}
