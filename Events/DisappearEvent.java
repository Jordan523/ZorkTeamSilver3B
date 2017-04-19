package Events;
import Items.*;
import Game.GameState;
/**
 *
 * @author Nathan
 */
public class DisappearEvent implements Events {
    private Item orig;
    
    public DisappearEvent(Item orig) {
        this.orig = orig;
    }
    
    @Override
    public void execute() {
        GameState gs = GameState.instance();
        Item newItem;
        boolean found = true;
        
        // Check the player's inventory
        try {
            if (gs.getItemFromInventoryNamed(orig.getPrimaryName()) != null) {
                gs.getPlayer().removeFromInventory(orig);
            }
        } catch (Item.NoItemException ex) { found = false; }
        
        // If it's not in the inventory, then check the room
        if (!found) {
            try {
                if (gs.getAdventurersCurrentRoom().getItemNamed(orig.getPrimaryName()) != null) {
                    gs.getAdventurersCurrentRoom().remove(orig);
                }
            } catch (Item.NoItemException ex) { }
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
